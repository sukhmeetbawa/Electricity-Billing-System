package billing_system;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class calculateBill implements showWindow {
    private JPanel main;
    private JComboBox<String> meterMenu;
    private JComboBox<String> monthMenu;
    private JTextField unitsConsumed;
    private JButton calculateButton;
    private ResultSet resultSet;

    calculateBill(JFrame frame) {
        connectToMySQL getData = new connectToMySQL();
        try {
            ResultSet storeData = getData.statement.executeQuery("select * from customer_info");
            while (storeData.next()) {
                meterMenu.addItem(storeData.getString("meter"));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        calculateButton.addActionListener(e -> {
            String meterNumber = (String) meterMenu.getSelectedItem();
            String units = unitsConsumed.getText();
            String month = (String) monthMenu.getSelectedItem();
            int unitsInInteger = Integer.parseInt(units);
            int amount = 0;
            try {
                connectToMySQL setData = new connectToMySQL();
                ResultSet tax = setData.statement.executeQuery("select * from customer_info where meter='" + meterNumber + "'");
                if (tax.next()) {
                    tax = setData.statement.executeQuery("select * from tax where place='" + tax.getString("state") + "'");
                    if (tax.next()) {
                        amount = unitsInInteger * tax.getInt("unit_rate") + tax.getInt("meter_rate") + tax.getInt("service_rent") + tax.getInt("mcb_rent");
                        int cgst = amount * tax.getInt("cgst") / 100;
                        int sgst = amount * tax.getInt("sgst") / 100;
                        amount = amount + cgst + sgst;
                    }
                }
                resultSet = setData.statement.executeQuery("select * from bill where meter_number='" + meterNumber + "' and month='" + month + "'");
                if (resultSet.next()) {
                    PreparedStatement preparedStatement = getData.connection.prepareStatement("update bill set units = ?,amount = ? where meter_number = ? and month = ?");
                    preparedStatement.setString(1,units);
                    preparedStatement.setInt(2,amount);
                    preparedStatement.setString(3,meterNumber);
                    preparedStatement.setString(4,month);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(main, "ENTRY UPDATED");
                    frame.setVisible(false);
                } else {
                    setData.statement.executeUpdate("insert into bill(meter_number,month,units,amount)values('" + meterNumber + "','" + month + "','" + units + "','" + amount + "')");
                    JOptionPane.showMessageDialog(main, "ENTRY ADDED");
                    frame.setVisible(false);
                }

            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }

        });
    }

    public void drawWindow(JFrame frame) {
        frame.setSize(470, 350);
        frame.setContentPane(new calculateBill(frame).main);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("./icons/lightning.png");
        frame.setIconImage(imageIcon);
        frame.setVisible(true);
    }
}

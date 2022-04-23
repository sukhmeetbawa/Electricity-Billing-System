package billing_system;

import javax.swing.*;
import java.awt.*;
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
                resultSet = setData.statement.executeQuery("select * from bill where meter_number='" + meterNumber + "' and month='" + month + "'");
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(main, "ENTRY EXISTS");
                    meterMenu.setSelectedIndex(0);
                    monthMenu.setSelectedIndex(0);
                    unitsConsumed.setText("");
                } else {
                    resultSet = setData.statement.executeQuery("select * from customer_info where meter='" + meterNumber + "'");
                    if (resultSet.next()) {
                        resultSet = setData.statement.executeQuery("select * from tax where place='" + resultSet.getString("state") + "'");
                    }
                    if (resultSet.next()) {
                        amount = unitsInInteger * resultSet.getInt("unit_rate") + resultSet.getInt("meter_rent") + resultSet.getInt("service_rent") + resultSet.getInt("mcb_rent");
                        int cgst = amount * resultSet.getInt("cgst") / 100;
                        int sgst = amount * resultSet.getInt("sgst") / 100;
                        amount = amount + cgst + sgst;
                    }
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

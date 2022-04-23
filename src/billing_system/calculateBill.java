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

    calculateBill() {
        connectToMySQL getData = new connectToMySQL();
        try {
            ResultSet storeData = getData.statement.executeQuery("select * from customer_info");
            while (storeData.next()) {
                meterMenu.addItem(storeData.getString("meter"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        calculateButton.addActionListener(e -> {
            String meterNumber = (String) meterMenu.getSelectedItem();
            String units = unitsConsumed.getText();
            String month = (String) monthMenu.getSelectedItem();
            int unitsInInteger = Integer.parseInt(units);
            int charges = unitsInInteger * 7;
            int amount = 0;
            try {
                connectToMySQL setData = new connectToMySQL();
                ResultSet resultSet = setData.statement.executeQuery("select * from tax");
                if (resultSet.next()) {
                    amount = charges + resultSet.getInt("meter_rent") + resultSet.getInt("service_rent") + resultSet.getInt("gst") + resultSet.getInt("mcb_rent");
                }
                setData.statement.executeUpdate("insert into bill(meter_number,month,units,amount)values('" + meterNumber + "','" + month + "','" + units + "','" + amount + "')");
                JOptionPane.showMessageDialog(null, "Bill Updated");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

    public void drawWindow(JFrame frame) {
        frame.setSize(470, 350);
        frame.setContentPane(new calculateBill().main);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("./icons/lightning.png");
        frame.setIconImage(imageIcon);
        frame.setVisible(true);
    }
}

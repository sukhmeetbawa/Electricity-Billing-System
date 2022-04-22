package billing_system;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class calculateBill {
    private JPanel Main;
    private JComboBox meterMenu;
    private JComboBox monthMenu;
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
            int multiplier = unitsInInteger * 7;
            int amount = multiplier + 50 + 12 + 102 + 20 + 50;
            try {
                connectToMySQL setData = new connectToMySQL();
                setData.statement.executeUpdate("insert into bill(meter_number,month,units,amount)values('" + meterNumber + "','" + month + "','" + units + "','" + amount + "')");
                JOptionPane.showMessageDialog(null, "Bill Updated");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

    public static void drawWindow() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Electricity Billing System");
        frame.setContentPane(new calculateBill().Main);
        frame.pack();
        frame.setSize(470, 350);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

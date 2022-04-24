package billing_system;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class taxEditor implements showWindow {
    private JPanel main;
    private JComboBox<String> placeSelector;
    private JTextField unitRateInput;
    private JTextField meterRateInput;
    private JTextField serviceRentInput;
    private JTextField cGSTInput;
    private JTextField sGSTInput;
    private JTextField mcbRentInput;
    private JButton submitButton;

    taxEditor(JFrame frame) {
        placeSelector.requestFocus();
        connectToMySQL getData = new connectToMySQL();
        // Populate the Drop Menu for States
        placeSelector.addItem("SELECT STATE");
        try {
            ResultSet storeData = getData.statement.executeQuery("SELECT distinct state FROM customer_info");
            while (storeData.next()) placeSelector.addItem(storeData.getString("state"));
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        submitButton.addActionListener(e -> {
            if (placeSelector.getSelectedIndex() != 0) {
                try {

                    ResultSet tax = getData.statement.executeQuery("select * from tax where place='" + placeSelector.getSelectedItem() + "'");
                    if (tax.next()) {
                        PreparedStatement preparedStatement = getData.connection.prepareStatement("update tax set unit_rate = ?, meter_rent = ?, service_rent = ?, cgst = ?, sgst = ?, mcb_rent = ? where place = ?");
                        preparedStatement.setInt(1, Integer.parseInt(unitRateInput.getText()));
                        preparedStatement.setInt(2, Integer.parseInt(meterRateInput.getText()));
                        preparedStatement.setInt(3, Integer.parseInt(serviceRentInput.getText()));
                        preparedStatement.setInt(4, Integer.parseInt(cGSTInput.getText()));
                        preparedStatement.setInt(5, Integer.parseInt(sGSTInput.getText()));
                        preparedStatement.setInt(6, Integer.parseInt(mcbRentInput.getText()));
                        preparedStatement.setString(7, String.valueOf(placeSelector.getSelectedItem()));
                        preparedStatement.executeUpdate();
                    } else {
                        PreparedStatement preparedStatement = getData.connection.prepareStatement("insert into tax(unit_rate, meter_rent, service_rent, cgst, sgst, mcb_rent, place)values(?,?,?,?,?,?,?)");
                        preparedStatement.setInt(1, Integer.parseInt(unitRateInput.getText()));
                        preparedStatement.setInt(2, Integer.parseInt(meterRateInput.getText()));
                        preparedStatement.setInt(3, Integer.parseInt(serviceRentInput.getText()));
                        preparedStatement.setInt(4, Integer.parseInt(cGSTInput.getText()));
                        preparedStatement.setInt(5, Integer.parseInt(sGSTInput.getText()));
                        preparedStatement.setInt(6, Integer.parseInt(mcbRentInput.getText()));
                        preparedStatement.setString(7, String.valueOf(placeSelector.getSelectedItem()));
                        preparedStatement.executeUpdate();
                    }
                    frame.setVisible(false);

                } catch (SQLException exception) {
                    throw new RuntimeException(exception);
                }
            }else {
                JOptionPane.showMessageDialog(main,"Select State First");
            }
        });
        placeSelector.addActionListener(e -> {
            try {
                ResultSet tax = getData.statement.executeQuery("select * from tax where place='" + placeSelector.getSelectedItem() + "'");
                if (tax.next()) {
                    unitRateInput.setText(tax.getString("unit_rate"));
                    meterRateInput.setText(tax.getString("meter_rent"));
                    serviceRentInput.setText(tax.getString("service_rent"));
                    cGSTInput.setText(tax.getString("cgst"));
                    sGSTInput.setText(tax.getString("sgst"));
                    mcbRentInput.setText(tax.getString("mcb_rent"));
                    submitButton.setText("Update");
                } else {
                    unitRateInput.setText("");
                    meterRateInput.setText("");
                    serviceRentInput.setText("");
                    cGSTInput.setText("");
                    sGSTInput.setText("");
                    mcbRentInput.setText("");
                    submitButton.setText("Submit");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

    @Override
    public void drawWindow(JFrame frame) {
        frame.setContentPane(new taxEditor(frame).main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("./icons/lightning.png");
        frame.setIconImage(imageIcon);
        frame.setVisible(true);
    }
}

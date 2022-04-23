package billing_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private ResultSet storeData;

    taxEditor(JFrame frame) {
        connectToMySQL getData = new connectToMySQL();
        // Populate the Drop Menu for States
        try {
            storeData = getData.statement.executeQuery("SELECT distinct state FROM customer_info");
            while (storeData.next()) placeSelector.addItem(storeData.getString("state"));
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        // Change Button Based on Place Selected
        Boolean update = false;
        try {
            storeData = getData.statement.executeQuery("SELECT * from tax where place='" + placeSelector.getSelectedItem() + "'");
            if (storeData.next()) {
//                submitButton.setText("Update");
                unitRateInput.setText(storeData.getString("unit_rate"));
                meterRateInput.setText(storeData.getString("meter_rent"));
                serviceRentInput.setText(storeData.getString("service_rent"));
                cGSTInput.setText(storeData.getString("cgst"));
                sGSTInput.setText(storeData.getString("sgst"));
                mcbRentInput.setText(storeData.getString("mcb_rent"));
                placeSelector.setSelectedItem(storeData.getString("place"));
                update = true;
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        int unitRate = Integer.parseInt(unitRateInput.getText());
        int meterRate = Integer.parseInt(meterRateInput.getText());
        int serviceRent = Integer.parseInt(serviceRentInput.getText());
        int cGST = Integer.parseInt(cGSTInput.getText());
        int sGST = Integer.parseInt(sGSTInput.getText());
        int mcbRent = Integer.parseInt(mcbRentInput.getText());
        String place = String.valueOf(placeSelector.getSelectedItem());
        String statement;
        if (update)
            statement = "update tax set unit_rate = ?, meter_rent = ?, service_rent = ?, cgst = ?, sgst = ?, mcb_rent = ?, place = ?";
        else
            statement = "insert into tax(unit_rate, meter_rent, service_rent, cgst, sgst, mcb_rent, place)values(?,?,?,?,?,?,?)";
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement preparedStatement = getData.connection.prepareStatement(statement);
                    preparedStatement.setInt(1,Integer.parseInt(unitRateInput.getText()));
                    preparedStatement.setInt(2,Integer.parseInt(meterRateInput.getText()));
                    preparedStatement.setInt(3,Integer.parseInt(serviceRentInput.getText()));
                    preparedStatement.setInt(4,Integer.parseInt(cGSTInput.getText()));
                    preparedStatement.setInt(5,Integer.parseInt(sGSTInput.getText()));
                    preparedStatement.setInt(6,Integer.parseInt(mcbRentInput.getText()));
                    preparedStatement.setString(7,String.valueOf(placeSelector.getSelectedItem()));
                    preparedStatement.executeUpdate();
                    frame.setVisible(false);
                } catch (SQLException exception) {
                    throw new RuntimeException(exception);
                }
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

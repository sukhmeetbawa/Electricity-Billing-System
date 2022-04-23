package billing_system;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;

public class newCustomer implements showWindow {
    private JTextField nameInput;
    private JPanel main;
    private JButton submitButton;
    private JTextField meterInput;
    private JTextField addressInput;
    private JTextField cityInput;
    private JTextField stateInput;
    private JTextField emailInput;
    private JTextField phoneInput;

    public newCustomer(JFrame frame) {
        submitButton.addActionListener(e -> {
            String name = nameInput.getText();
            String meter = meterInput.getText();
            String address = addressInput.getText();
            String city = cityInput.getText();
            String state = stateInput.getText();
            String email = emailInput.getText();
            String phone = phoneInput.getText();
            try {
                connectToMySQL connection = new connectToMySQL();
                PreparedStatement preparedStatement = connection.connection.prepareStatement("insert into customer_info(name,meter,address,city,state,email,phone)values( ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, meter);
                preparedStatement.setString(3, address);
                preparedStatement.setString(4, city);
                preparedStatement.setString(5, state);
                preparedStatement.setString(6, email);
                preparedStatement.setString(7, phone);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(main, "NEW CUSTOMER REGISTERED");
                frame.setVisible(false);

            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });
    }

    public void drawWindow(JFrame frame) {
        frame.setContentPane(new newCustomer(frame).main);
        frame.pack();
        frame.setSize(470, 350);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("./icons/lightning.png");
        frame.setIconImage(imageIcon);
        frame.setVisible(true);
    }
}
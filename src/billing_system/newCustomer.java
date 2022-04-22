package billing_system;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;

public class newCustomer {
    private JTextField nameInput;
    private JPanel Main;
    private JButton submitButton;
    private JTextField meternoInput;
    private JTextField addressInput;
    private JTextField cityInput;
    private JTextField stateInput;
    private JTextField emailInput;
    private JTextField phonenoInput;

    public newCustomer(Frame frame) {
        submitButton.addActionListener(e -> {
            String name = nameInput.getText();
            String meter = meternoInput.getText();
            String address = addressInput.getText();
            String city = stateInput.getText();
            String state = cityInput.getText();
            String email = emailInput.getText();
            String phone = phonenoInput.getText();
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
                JOptionPane.showMessageDialog(Main, "NEW CUSTOMER REGISTERED");
                frame.setVisible(false);

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void drawWindow() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Electricity Billing System");
        frame.setContentPane(new newCustomer(frame).Main);
        frame.pack();
        frame.setSize(470, 350);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

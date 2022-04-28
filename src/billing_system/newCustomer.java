package billing_system;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class newCustomer implements showWindow {
    private JTextField nameInput;
    private JPanel main;
    private JButton submitButton;
    private JTextField meterInput;
    private JTextField addressInput;
    private JTextField cityInput;
    private JTextField emailInput;
    private JTextField phoneInput;
    private JComboBox<String> stateInput;

    public newCustomer(JFrame frame) {
        submitButton.addActionListener(e -> {
            String email = emailInput.getText();
            String phone = phoneInput.getText();
            String emailRegex = "^[a-zA-Z\\d_+&*-]+(?:\\.[a-zA-Z\\d_+&*-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,7}$";
            if (stateInput.getSelectedIndex() == 0 | !(Pattern.compile(emailRegex).matcher(email).matches()) | phone.length() != 10 | !meterInput.getText().matches("\\d+") | !phoneInput.getText().matches("\\d+")) {
                if (stateInput.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(main, "Select A State");
                }
                if (!(Pattern.compile(emailRegex).matcher(email).matches())) {
                    JOptionPane.showMessageDialog(main, "Enter A Valid Email Address");
                    emailInput.setText("");
                    emailInput.requestFocus();
                }
                if (phone.length() != 10 | !phoneInput.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(main, "Enter A Valid Phone Number");
                    phoneInput.setText("");
                    phoneInput.requestFocus();
                }
                if (!meterInput.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(main, "Enter A Valid Meter Number");
                    phoneInput.setText("");
                    phoneInput.requestFocus();
                }
            } else {
                try {
                    connectToMySQL connection = new connectToMySQL();
                    PreparedStatement preparedStatement = connection.connection.prepareStatement("insert into customer_info(name,meter,address,city,state,email,phone)values( ?, ?, ?, ?, ?, ?, ?)");
                    preparedStatement.setString(1, nameInput.getText());
                    preparedStatement.setString(2, meterInput.getText());
                    preparedStatement.setString(3, addressInput.getText());
                    preparedStatement.setString(4, cityInput.getText());
                    preparedStatement.setString(5, (String) stateInput.getSelectedItem());
                    preparedStatement.setString(6, email);
                    preparedStatement.setString(7, phone);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(main, "NEW CUSTOMER REGISTERED");
                    frame.setVisible(false);

                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(main, exception.getMessage());
                    throw new RuntimeException(exception);
                }
            }
        });
    }

    public void drawWindow(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(new newCustomer(frame).main);
        frame.pack();
        frame.setSize(screenSize.width / 4, screenSize.height / 2);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("./icons/lightning.png");
        frame.setIconImage(imageIcon);
        frame.setVisible(true);
    }
}
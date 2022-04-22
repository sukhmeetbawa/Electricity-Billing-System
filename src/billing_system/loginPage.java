package billing_system;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginPage {
    private JPanel Main;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JButton loginButton;

    public loginPage(JFrame frame) {
        loginButton.addActionListener(actionEvent -> {
            try {
                connectToMySQL connection = new connectToMySQL();
                String uname = usernameInput.getText();
                String passwd = String.valueOf(passwordInput.getPassword());
                PreparedStatement preparedStatement = connection.connection.prepareStatement("select * from admin_credentials where username = ? and password = ?");
                preparedStatement.setString(1, uname);
                preparedStatement.setString(2, passwd);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Login Successful");
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(Main, "LOGIN SUCCESSFUL");
                    mainPage.drawWindow();
                } else {
                    System.out.println("Login Failed");
                    JOptionPane.showMessageDialog(Main, "INVALID LOGIN");
                }
            } catch (SQLException | UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Electricity Billing System");
        frame.setContentPane(new loginPage(frame).Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}

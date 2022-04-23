package billing_system;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginPage implements showWindow {
    private JPanel main;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JButton loginButton;
    private ResultSet resultSet;

    public loginPage(JFrame frame) {
        connectToMySQL connection = new connectToMySQL();
        try {
            resultSet = connection.statement.executeQuery("select * from admin_credentials");
            if (resultSet.next())
                loginButton.setText("Sign-in");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loginButton.addActionListener(actionEvent -> {
            try {
                resultSet = connection.statement.executeQuery("select * from admin_credentials");
                if (!(resultSet.next())) {
                    JOptionPane.showMessageDialog(main, "CREATING NEW ADMIN CREDENTIALS");
                    loginButton.setText("Sign-up");
                    String uname = usernameInput.getText();
                    PreparedStatement preparedStatement = connection.connection.prepareStatement("insert into admin_credentials(username,password) values(?,?)");
                    String passwd = String.valueOf(passwordInput.getPassword());
                    preparedStatement.setString(1, uname);
                    preparedStatement.setString(2, passwd);
                    preparedStatement.executeUpdate();
                    usernameInput.setText("");
                    passwordInput.setText("");
                    loginButton.setText("Sign-in");
                } else {
                    loginButton.setText("Sign-in");
                    String uname = usernameInput.getText();
                    String passwd = String.valueOf(passwordInput.getPassword());
                    PreparedStatement preparedStatement = connection.connection.prepareStatement("select * from admin_credentials where username = ? and password = ?");
                    preparedStatement.setString(1, uname);
                    preparedStatement.setString(2, passwd);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        System.out.println("Login Successful");
                        JOptionPane.showMessageDialog(main, "LOGIN SUCCESSFUL");
                        frame.setVisible(false);
                        mainPage mainPage = new mainPage();
                        mainPage.drawWindow(new JFrame("Electricity Billing System"));
                    } else {
                        System.out.println("Login Failed");
                        JOptionPane.showMessageDialog(main, "INVALID LOGIN");
                        usernameInput.setText("");
                        passwordInput.setText("");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public void drawWindow(JFrame frame) {
        frame.setContentPane(new loginPage(frame).main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("./icons/lock.png");
        frame.setIconImage(imageIcon);
        frame.setVisible(true);
    }
}

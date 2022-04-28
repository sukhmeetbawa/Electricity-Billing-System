package billing_system.user_management;

import billing_system.connectToMySQL;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginPage {

    protected JPanel main;
    protected JTextField usernameInput;
    protected JPasswordField passwordInput;
    protected JButton loginButton;
    protected ResultSet resultSet;
    protected connectToMySQL connection = new connectToMySQL();
    private String uname, passwd;

    public loginPage(JFrame frame, boolean newUser) {

        try {
            resultSet = connection.statement.executeQuery("select * from admin_credentials");
            if (resultSet.next())
                loginButton.setText("Sign-in");
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        if (newUser)
            loginButton.setText("Add User");
        loginButton.addActionListener(actionEvent -> {
            try {
                resultSet = connection.statement.executeQuery("select * from admin_credentials");
                uname = usernameInput.getText();
                passwd = String.valueOf(passwordInput.getPassword());
                if (newUser | !(resultSet.next())) {
                    new createAdmin(frame, true, uname, passwd);
                    if (newUser)
                        frame.setVisible(false);
                    else {
                        usernameInput.setText("");
                        passwordInput.setText("");
                        loginButton.setText("Sign-in");
                        usernameInput.requestFocus();
                    }
                } else {
                    new loginUser(frame, false, uname, passwd);
                }
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(main, exception.getMessage());
                usernameInput.setText("");
                passwordInput.setText("");
                frame.setVisible(false);
            }
        });
    }

    public void drawWindow(JFrame frame, boolean newUser) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setContentPane(new loginPage(frame, newUser).main);
        if (!newUser)
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(screenSize.width / 4, screenSize.height / 4);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("./icons/lock.png");
        frame.setIconImage(imageIcon);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

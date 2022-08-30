package billing_system.user_management;

import billing_system.mainPage;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static billing_system.shaGeneration.getSHA;
import static billing_system.shaGeneration.toHexString;

public class loginUser extends loginPage {
    public loginUser(JFrame frame, boolean newUser, String uname, String passwd) throws SQLException {
        super(frame, newUser);
        loginButton.setText("Sign-in");
        PreparedStatement preparedStatement = connection.connection
                .prepareStatement("select * from admin_credentials where username = ? and password = ?");
        preparedStatement.setString(1, uname);
        try {
            preparedStatement.setString(2, toHexString(getSHA(passwd)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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
        }
    }
}

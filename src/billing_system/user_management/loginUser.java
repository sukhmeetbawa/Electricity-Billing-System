package billing_system.user_management;

import billing_system.mainPage;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class loginUser extends loginPage {
    public loginUser(JFrame frame, boolean newUser, String uname, String passwd) throws SQLException {
        super(frame, newUser);
        loginButton.setText("Sign-in");
        PreparedStatement preparedStatement = connection.connection
                .prepareStatement("select * from admin_credentials where username = ? and password = ?");
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
        }
    }
}

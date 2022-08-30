package billing_system.user_management;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static billing_system.shaGeneration.getSHA;
import static billing_system.shaGeneration.toHexString;

public class createAdmin extends loginPage {

    public createAdmin(JFrame frame, boolean newUser, String uname, String passwd) throws SQLException {
        super(frame, newUser);
        JOptionPane.showMessageDialog(main, "CREATING NEW ADMIN CREDENTIALS");
        loginButton.setText("Sign-up");

        PreparedStatement preparedStatement = connection.connection
                .prepareStatement("insert into admin_credentials(username,password) values(?,?)");
        preparedStatement.setString(1, uname);
        try {
            preparedStatement.setString(2, toHexString(getSHA(passwd)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        preparedStatement.executeUpdate();
    }
}

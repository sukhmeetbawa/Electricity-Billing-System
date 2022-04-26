package billing_system.user_management;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class createAdmin extends loginPage {

    public createAdmin(JFrame frame, boolean newUser, String uname, String passwd) throws SQLException {
        super(frame, newUser);
        JOptionPane.showMessageDialog(main, "CREATING NEW ADMIN CREDENTIALS");
        loginButton.setText("Sign-up");

        PreparedStatement preparedStatement = connection.connection
                .prepareStatement("insert into admin_credentials(username,password) values(?,?)");
        preparedStatement.setString(1, uname);
        preparedStatement.setString(2, passwd);
        preparedStatement.executeUpdate();
    }
}

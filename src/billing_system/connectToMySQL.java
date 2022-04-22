package billing_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*

This is the most important class as it is responsible for establishing the connection with the MySQL database

 */

public class connectToMySQL {
    Connection connection;
    Statement statement;

    public connectToMySQL() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_project", "root", "");
            statement = connection.createStatement();
            System.out.println("CONNECTION ESTABLISHED");
        } catch (ClassNotFoundException | SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

}


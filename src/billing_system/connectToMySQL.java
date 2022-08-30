package billing_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static billing_system.setupDatabase.schema;

public class connectToMySQL {
    public Connection connection;
    public Statement statement;

    public connectToMySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:/" + schema, setupDatabase.USER, setupDatabase.PASS);
            statement = connection.createStatement();
            System.out.println("CONNECTION ESTABLISHED");
        } catch (ClassNotFoundException | SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}

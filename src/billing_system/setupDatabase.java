package billing_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class setupDatabase {
    static final String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "root";
    static final String PASS = "";
    public static  String schema = "electricity_billing_system";

    public static void main(String[] args) {
        // Open a connection
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement()
        ) {
            // Create a new database java_project
            String sql = "CREATE DATABASE `" + schema + "`";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE `"+schema+"`.`admin_credentials` (" +
                    "  `username` VARCHAR(45) NOT NULL," +
                    "  `password` VARCHAR(45) NOT NULL," +
                    "  PRIMARY KEY (`username`)," +
                    "  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE `"+schema+"`.`bill` (" +
                    "  `meter_number` INT NULL," +
                    "  `month` VARCHAR(45) NULL," +
                    "  `units` INT NULL," +
                    "  `amount` INT NULL);";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE `"+schema+"`.`customer_info` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(45) NOT NULL," +
                    "  `meter` INT NOT NULL," +
                    "  `address` VARCHAR(255) NOT NULL," +
                    "  `city` VARCHAR(45) NOT NULL," +
                    "  `state` VARCHAR(45) NOT NULL," +
                    "  `email` VARCHAR(45) NOT NULL," +
                    "  `phone` VARCHAR(45) NOT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  UNIQUE INDEX `meter_UNIQUE` (`meter` ASC) VISIBLE);";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE `"+schema+"`.`tax` (" +
                    "  `meter_location` VARCHAR(45) NOT NULL DEFAULT 'INSIDE'," +
                    "  `meter_type` VARCHAR(45) NOT NULL DEFAULT 'ELECTRONIC'," +
                    "  `phase_code` VARCHAR(45) NOT NULL DEFAULT '1'," +
                    "  `bill_type` VARCHAR(255) NOT NULL DEFAULT 'NORMAL'," +
                    "  `days` INT NOT NULL DEFAULT '30'," +
                    "  `unit_rate` INT NULL," +
                    "  `meter_rent` INT NULL," +
                    "  `service_rent` INT NULL," +
                    "  `cgst` INT NULL," +
                    "  `sgst` INT NULL," +
                    "  `mcb_rent` INT NULL," +
                    "  `place` VARCHAR(45) NULL);";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

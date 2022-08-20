import billing_system.connectToMySQL;
import billing_system.setupDatabase;
import billing_system.user_management.loginPage;

import javax.swing.*;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;


public class mainProgram {
    public static String path;
    public static Properties properties = new Properties();

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, IOException {
        path = System.getProperty("user.home") + File.separator + ".billingSystem";
        boolean runningForFirstTime = new File(path).mkdir();
        File configFile = new File(path + File.separator + "config.properties");
        if (runningForFirstTime) {
            configFile.createNewFile();
            Scanner input = new Scanner(System.in);
            System.out.println("Enter MySQL Username: ");
            String username = input.nextLine();
            System.out.println("Enter MySQL Password: ");
            String password = input.nextLine();
            properties.setProperty("USERNAME", username);
            properties.setProperty("PASSWORD", password);
            FileWriter writer = new FileWriter(configFile);
            properties.store(writer, "MySQL Credentials");
            writer.close();
            setupDatabase.USER = properties.getProperty("USERNAME");
            setupDatabase.PASS = properties.getProperty("PASSWORD");
            setupDatabase.init();
        }
        FileReader reader = new FileReader(configFile);
        properties.load(reader);
        connectToMySQL.USER = properties.getProperty("USERNAME");
        connectToMySQL.PASS = properties.getProperty("PASSWORD");

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        loginPage loginPage = new loginPage(null, false);
        loginPage.drawWindow(new JFrame("Electricity Billing System"), false);
    }
}

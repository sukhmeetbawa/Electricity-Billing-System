import billing_system.loginPage;
import billing_system.setupDatabase;

import javax.swing.*;

import static billing_system.setupDatabase.schema;

public class mainProgram {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Electricity Billing System");
        loginPage loginPage = new loginPage(frame, false);
        loginPage.drawWindow(frame, false);
    }
}

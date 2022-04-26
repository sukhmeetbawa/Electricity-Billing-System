import billing_system.user_management.loginPage;
import billing_system.setupDatabase;

import javax.swing.*;

public class mainProgram {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        if (args.length > 0)
            setupDatabase.main(null);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        loginPage loginPage = new loginPage(null, false);
        loginPage.drawWindow(new JFrame("Electricity Billing System"), false);
    }
}

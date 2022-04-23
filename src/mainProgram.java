import billing_system.loginPage;

import javax.swing.*;

public class mainProgram {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Electricity Billing System");
        loginPage loginPage = new loginPage(frame);
        loginPage.drawWindow(frame);
    }
}

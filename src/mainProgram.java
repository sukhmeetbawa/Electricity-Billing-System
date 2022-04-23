import billing_system.loginPage;

import javax.swing.*;

public class mainProgram {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        loginPage.drawWindow();
    }
}

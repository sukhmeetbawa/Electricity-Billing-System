package billing_system;

import javax.swing.*;

public class mainPage {

    private JButton newCustomerButton;
    private JButton customerDetailsButton;
    private JPanel Main;
    private JButton calculateButton;
    private JButton generateButton;

    public mainPage() {
        newCustomerButton.addActionListener(e -> {
            System.out.println("ADDING NEW CUSTOMER");
            JOptionPane.showMessageDialog(Main, "ADDING NEW CUSTOMER");
            try {
                newCustomer.drawWindow();
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                     IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
        customerDetailsButton.addActionListener(e -> {
            System.out.println("SHOWING CUSTOMER DETAILS");
            try {
                customerDetails.drawWindow();
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                     IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }

        });
        calculateButton.addActionListener(e -> {
            System.out.println("CALCULATING BILL");
            try {
                calculateBill.drawWindow();
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                     IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
        generateButton.addActionListener(e -> {
            System.out.println("GENERATING BILL");
            try {
                generateBill.drawWindow();
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                     IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void drawWindow() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Electricity Billing System");
        frame.setContentPane(new mainPage().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

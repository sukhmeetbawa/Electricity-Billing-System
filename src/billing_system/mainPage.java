package billing_system;

import javax.swing.*;
import java.awt.*;

public class mainPage {

    private JButton newCustomerButton;
    private JButton customerDetailsButton;
    private JPanel main;
    private JButton calculateButton;
    private JButton generateButton;

    public mainPage() {
        newCustomerButton.addActionListener(e -> {
            System.out.println("ADDING NEW CUSTOMER");
            JOptionPane.showMessageDialog(main, "ADDING NEW CUSTOMER");
            newCustomer.drawWindow();
        });
        customerDetailsButton.addActionListener(e -> {
            System.out.println("SHOWING CUSTOMER DETAILS");
            customerDetails.drawWindow();

        });
        calculateButton.addActionListener(e -> {
            System.out.println("CALCULATING BILL");
            calculateBill.drawWindow();
        });
        generateButton.addActionListener(e -> {
            System.out.println("GENERATING BILL");
            generateBill.drawWindow();
        });
    }

    public static void drawWindow() {
        JFrame frame = new JFrame("Electricity Billing System");
        frame.setContentPane(new mainPage().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("./icons/lightning.png");
        frame.setIconImage(imageIcon);
    }
}

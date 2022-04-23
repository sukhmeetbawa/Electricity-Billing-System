package billing_system;

import javax.swing.*;
import java.awt.*;

public class mainPage implements showWindow {

    private JButton newCustomerButton;
    private JButton customerDetailsButton;
    private JPanel main;
    private JButton calculateButton;
    private JButton generateButton;

    public mainPage() {
        newCustomerButton.addActionListener(e -> {
            System.out.println("ADDING NEW CUSTOMER");
            JOptionPane.showMessageDialog(main, "ADDING NEW CUSTOMER");
            JFrame customerFrame = new JFrame("Add Customer");
            newCustomer newCustomer = new newCustomer(customerFrame);
            newCustomer.drawWindow(customerFrame);
        });
        customerDetailsButton.addActionListener(e -> {
            System.out.println("SHOWING CUSTOMER DETAILS");
            customerDetails customer = new customerDetails();
            customer.drawWindow(new JFrame("Customer Details"));

        });
        calculateButton.addActionListener(e -> {
            System.out.println("CALCULATING BILL");
            calculateBill calculateBill = new calculateBill();
            calculateBill.drawWindow(new JFrame("Calculate Bill"));
        });
        generateButton.addActionListener(e -> {
            System.out.println("GENERATING BILL");
            generateBill generateBill = new generateBill();
            generateBill.drawWindow(new JFrame("Generate Bill"));
        });
    }

    public void drawWindow(JFrame frame) {
        frame.setContentPane(new mainPage().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("./icons/lightning.png");
        frame.setIconImage(imageIcon);
        frame.setVisible(true);
    }
}

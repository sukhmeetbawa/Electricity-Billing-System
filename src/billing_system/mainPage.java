package billing_system;

import billing_system.user_management.loginPage;

import javax.swing.*;
import java.awt.*;

public class mainPage implements showWindow {

    private JButton newCustomerButton;
    private JButton customerDetailsButton;
    private JPanel main;
    private JButton calculateButton;
    private JButton generateButton;
    private JButton addAdminButton;
    private JButton changeTaxesButton;

    public mainPage() {
        newCustomerButton.addActionListener(e -> {
            System.out.println("ADDING NEW CUSTOMER");
            newCustomer newCustomer = new newCustomer(null);
            newCustomer.drawWindow(new JFrame("Add Customer"));
        });
        customerDetailsButton.addActionListener(e -> {
            System.out.println("SHOWING CUSTOMER DETAILS");
            customerDetails customer = new customerDetails();
            customer.drawWindow(new JFrame("Customer Details"));

        });
        calculateButton.addActionListener(e -> {
            System.out.println("CALCULATING BILL");
            calculateBill calculateBill = new calculateBill(null);
            calculateBill.drawWindow(new JFrame("Calculate Bill"));
        });
        generateButton.addActionListener(e -> {
            System.out.println("GENERATING BILL");
            generateBill generateBill = new generateBill();
            generateBill.drawWindow(new JFrame("Generate Bill"));
        });
        addAdminButton.addActionListener(e -> {
            loginPage loginPage = new loginPage(null, true);
            loginPage.drawWindow(new JFrame("New Admin"), true);
        });
        changeTaxesButton.addActionListener(e -> {
            taxEditor taxEditor = new taxEditor(null);
            taxEditor.drawWindow(new JFrame("Tax Editor"));
        });
    }

    public void drawWindow(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setContentPane(new mainPage().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(screenSize.width / 4, screenSize.height / 3);
        frame.setLocationRelativeTo(null);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("./icons/lightning.png");
        frame.setIconImage(imageIcon);
        frame.setVisible(true);
    }
}

package billing_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            JOptionPane.showMessageDialog(main, "ADDING NEW CUSTOMER");
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
            loginPage loginPage = new loginPage(null,true);
            loginPage.drawWindow(new JFrame("New Admin"),true);
        });
        changeTaxesButton.addActionListener(e -> {
            taxEditor taxEditor = new taxEditor(null);
            taxEditor.drawWindow(new JFrame("Tax Editor"));
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

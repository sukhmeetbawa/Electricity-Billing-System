package billing_system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class customerDetails extends JFrame implements ActionListener {

    private final JButton printButton;
    private final String[] headings = {"Customer Name", "Meter Number", "Address", "City", "State", "E-Mail", "Phone Number"};
    private final String[][] data = new String[20][8];
    int i = 0, j = 0;
    private JTable infoTable;

    customerDetails() {
        super("Customer Details");
        pack();
        setSize(1200, 650);
        setLocationRelativeTo(null);

        try {
            connectToMySQL connection = new connectToMySQL();
            String s1 = "select * from customer_info";
            ResultSet resultSet = connection.statement.executeQuery(s1);
            while (resultSet.next()) {
                data[i][j++] = resultSet.getString("name");
                data[i][j++] = resultSet.getString("meter");
                data[i][j++] = resultSet.getString("address");
                data[i][j++] = resultSet.getString("city");
                data[i][j++] = resultSet.getString("state");
                data[i][j++] = resultSet.getString("email");
                data[i][j++] = resultSet.getString("phone");
                i++;
                j = 0;
            }
            infoTable = new JTable(data, headings);

        } catch (Exception e) {
            e.printStackTrace();
        }


        printButton = new JButton("Print");
        add(printButton, "South");
        JScrollPane sp = new JScrollPane(infoTable);
        add(sp);
        printButton.addActionListener(this);
    }

    public static void drawWindow() {
        new customerDetails().setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            infoTable.print();
        } catch (Exception e) {
        }
    }

}
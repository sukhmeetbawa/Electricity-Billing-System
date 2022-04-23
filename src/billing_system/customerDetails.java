package billing_system;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class customerDetails {

    private final JPanel Main;
    private JButton printButton;
    int i = 0, j = 0;
    private JTable infoTable;

    customerDetails() {
        try {
            connectToMySQL connection = new connectToMySQL();
            String s1 = "select * from customer_info";
            ResultSet resultSet = connection.statement.executeQuery(s1);
            String[][] data = new String[20][8];
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
            String[] headings = {"Customer Name", "Meter Number", "Address", "City", "State", "E-Mail", "Phone Number"};
            infoTable = new JTable(data, headings);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main = new JPanel();
        Main.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
        JButton printButton = new JButton("Print");
//        JScrollPane scrollPane = ;
        Main.setLayout(new BorderLayout());
        Main.add(new JScrollPane(infoTable));
        Main.add(printButton, BorderLayout.SOUTH);
        printButton.requestFocus();
        printButton.addActionListener(e -> {
            try {
                infoTable.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void drawWindow() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Customer Details");
        frame.setContentPane(new customerDetails().Main);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
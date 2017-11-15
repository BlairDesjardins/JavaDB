import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Temporary sql test */

import java.sql.*;
import java.util.Vector;

/**
 * Created by Nariman on 2017-11-11.
 */
public class MainComponent extends JPanel {

    EmployeeButton empButton;
    AdminButton adminButton;
    CustomerButton customerButton;

    JPanel buttons;
    JPanel tablePanel;

    //JDBC Driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    static final String USER = "username";
    static final String PASS = "password";


    public MainComponent() {

        GridLayout UILayout = new GridLayout(2,0);
        this.setLayout(UILayout);
        buttons = new JPanel();
        tablePanel = new JPanel();
        this.add(buttons, BorderLayout.NORTH);

        empButton = new EmployeeButton("Employee");
        adminButton = new AdminButton("Administrator");
        customerButton = new CustomerButton("Customer");

        addEmpListener();
        addAdmListener();
        addCusListener();

        buttons.add(empButton);
        buttons.add(adminButton);
        buttons.add(customerButton);

    }

    public void addEmpListener() {
        empButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Run Employee Login Code Here");
                System.out.println("Logged in!");
            }
        });
    }

    public void addAdmListener() {
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Run Admin Login Code Here");
                System.out.println("Logged in!");

            }
        });
    }

    public void addCusListener() {
        class CusListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Run Customer Login Code Here");
                System.out.println("Logged in!");
                customerButton.loginCustomer();
                JTable table = customerButton.getTable();
                //String[][] data = {{"dog", "blue"}, {"cat", "red"}};
                //String[] cols = {"animal", "colour"};
                //JTable table = new JTable(data, cols);

                JFrame frame = new JFrame();
                frame.setLayout(new BorderLayout());
                frame.add(new JScrollPane(table));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                System.out.println("Table added");
            }
        }
        CusListener cusListener = new CusListener();
        customerButton.addActionListener(cusListener);
    }

}

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
    JTable table;

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
        this.add(tablePanel, BorderLayout.SOUTH);

        empButton = new EmployeeButton("Employee");
        adminButton = new AdminButton("Administrator");
        customerButton = new CustomerButton("Customer");

        addEmpListener();
        addAdmListener();
        addCusListener();

        buttons.add(empButton);
        buttons.add(adminButton);
        buttons.add(customerButton);

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.SOUTH);

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
                //JTable table = customerButton.getTable();
                String[][] data = {{"dog", "blue"}, {"cat", "red"}};
                String[] cols = {"animal", "colour"};
                JTable newTable = new JTable(data, cols);
                table.setModel( newTable.getModel() );
                System.out.println("Table added");
            }
        }
        CusListener cusListener = new CusListener();
        customerButton.addActionListener(cusListener);
    }

}

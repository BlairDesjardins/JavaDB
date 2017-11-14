import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Temporary sql test */

import java.sql.*;

/**
 * Created by Nariman on 2017-11-11.
 */
public class MainComponent extends JComponent {

    //JDBC Driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";
    static final String USER = "username";
    static final String PASS = "password";

    //Initial buttons
    EmployeeButton empButton;
    AdminButton adminButton;

    //Text fields
    JTextField userfield;
    JPasswordField passfield;

    DBSession sess;



    public MainComponent() {
        empButton = new EmployeeButton("Employee");
        adminButton = new AdminButton("Administrator");


        addEmpListener();
        addAdmListener();
        this.add(empButton);
        this.add(adminButton);
    }

    public void addEmpListener() {
        empButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sess = new EmployeeSession("abcde","efghi");
            }
        });
        this.add(empButton);
    }

    public void addAdmListener() {
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Run Admin Login Code Here");
                System.out.println("Logged in!");
            }
        });
        this.add(adminButton);
    }


}

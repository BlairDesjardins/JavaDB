import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Nariman on 2017-11-12.
 */
public class EmployeeButton extends JButton {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1234:orcl";
    static final String USER = "nsaftarl";
    static final String PASS = "04165448";

    EmployeeSession sess;

    Connection conn;
    Statement stmt;
    public EmployeeButton(String name) {
        super(name);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logging in as an Employee");
                loginEmployee();
            }
        });
    }

    public void loginEmployee() {
        JTextField user;
        JPasswordField pass;

//        try {
//            //Register JDBC Driver
//            Class.forName(JDBC_DRIVER);
//            //Open connection
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//
//            //Execute query
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT EMPNAME FROM EMPLOYEE";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                String id = rs.getString("EMPNAME");
//                System.out.println("ID: " + id + "\n");
//            }
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(stmt != null) {
//                    stmt.close();
//                }
//            } catch(SQLException se2) {}
//            try {
//                if(conn != null) {
//                    conn.close();
//                }
//            } catch(SQLException se) {
//                se.printStackTrace();
//            }
//
//        }

        try {
            sess = new EmployeeSession("Nariman","abcdefg");



        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Nariman on 2017-11-12.
 */
public class CustomerButton extends JButton {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:2345:orcl";
    static final String USER = "nsaftarl";
    static final String PASS = "04165448";

    Connection conn;
    Statement stmt;
    JTable merchTable;

    public CustomerButton(String name) {
        super(name);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logging in as Customer...");
                loginCustomer();
            }
        });

    }

    public void loginCustomer() {
        try {
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);

            //Open connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Execute query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM MERCHANDISE";
            ResultSet rs = stmt.executeQuery(sql);

            String[] columnNames = {"Name", "Price", "# in Stock"};
            String[][] data = new String[100][3];
            int i = 0;

            while (rs.next()) {
                String name = rs.getString("PRODUCTNAME");
                String price = rs.getString("PRICE");
                String stock = rs.getString("STOCKNUM");

                data[i][0] = name;
                data[i][1] = price;
                data[i][2] = stock;
                i++;
            }

            merchTable = new JTable(data, columnNames);

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
            } catch(SQLException se2) {}
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException se) {
                se.printStackTrace();
            }

        }
    }

}

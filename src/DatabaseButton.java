import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

/**
 * Created by Nariman on 2017-11-12.
 */
public class DatabaseButton extends JButton {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1234:orcl";
    static final String USER = "nsaftarl";
    static final String PASS = "04165448";

    Connection conn;
    Statement stmt;
    JTable table;

    public DatabaseButton(String name) {
        super(name);
    }

    public void makeTableFromQuery(String query) {
        try {
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);

            //Open connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Execute query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            table = new JTable(buildTableModel(rs));

            JFrame frame = new JFrame();
            frame.setLayout(new BorderLayout());
            frame.add(new JScrollPane(table));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            System.out.println("Table displayed");

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

    public void executeCommand(String command) {
        try {
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);

            //Open connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Execute query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            stmt.execute(command);

            System.out.println("Command executed!");

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

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

}

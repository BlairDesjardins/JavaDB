import java.io.IOError;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Nariman on 2017-11-13.
 */
public abstract class DBSession {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1234:orcl";
    static final String USER = "nsaftarl";
    static final String PASS = "04165448";


    boolean manager;
    String id;
    String password;

    Connection conn;
    Statement stmt;
    String sql;
    ResultSet rs;

    ArrayList<String> resultList;

    public DBSession(String user, String pass) {
        id = user;
        password = pass;

        try {
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);
            //Open connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection getConn() {
        try {
            throw new Exception("Connection is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}

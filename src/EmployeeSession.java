import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Nariman on 2017-11-13.
 */
public class EmployeeSession extends DBSession {
    boolean manager;
    String id;
    String password;
//    Connection conn;

    public EmployeeSession(String user, String pass) {
        super(user, pass);
        conn = getConn();
    }

    String executeQuery(String q) {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT EMPNAME FROM EMPLOYEE");
            while(rs.next()) {
                //Draw result in window/table
                String id = rs.getString("EMPNAME");
                System.out.println(id);
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }

        return null;
    }




}

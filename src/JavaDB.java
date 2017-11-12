/**
 * Created by Nariman on 2017-11-11.
 */
import oracle.jdbc.datasource.OracleDataSource;

import java.sql.*;
public class JavaDB {


    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1234:orcl";

    //  Database credentials
    static final String USER = "nsaftarl";
    static final String PASS = "04165448";

    public static void main(String[] args) {
        WindowFrame frame = new WindowFrame();
        frame.setSize(500,500);
        frame.setVisible(true);


        Connection conn = null;
        Statement stmt = null;

    }
}

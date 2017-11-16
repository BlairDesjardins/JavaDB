import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Temporary sql test */

/**
 * Created by Nariman on 2017-11-11.
 */
public class EmployeeTab extends JPanel {

    ViewMerchButton viewMerchButton;
    JButton anotherButton;

    JPanel panel1;
    JPanel panel2;

    //JDBC Driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    static final String USER = "username";
    static final String PASS = "password";


    public EmployeeTab() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        viewMerchButton = new ViewMerchButton("View Merchandise");
        anotherButton = new JButton("Another Button");

        panel1 = new JPanel();
        panel2 = new JPanel();

        panel1.add(viewMerchButton);
        panel2.add(anotherButton);

        this.add(panel1);
        this.add(panel2);

    }


}

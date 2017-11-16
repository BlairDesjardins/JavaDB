import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Temporary sql test */

/**
 * Created by Nariman on 2017-11-11.
 */
public class CustomerTab extends JPanel {

    ViewMerchButton viewMerchButton;
    JButton searchMerchButton;
    JTextField searchField;
    String searchText;

    JPanel viewMerchPanel;
    JPanel searchMerchPanel;

    //JDBC Driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    static final String USER = "username";
    static final String PASS = "password";


    public CustomerTab() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        viewMerchButton = new ViewMerchButton("View Merchandise");
        searchMerchButton = new JButton("Search Merchandise");
        searchMerchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchText = searchField.getText();
                System.out.println(searchText);
            }
        });

        JLabel search = new JLabel("Search:");
        searchField = new JTextField(20);
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchText = searchField.getText();
                System.out.println(searchText);
            }
        });


        viewMerchPanel = new JPanel();
        searchMerchPanel = new JPanel();

        viewMerchPanel.add(viewMerchButton);
        searchMerchPanel.add(search);
        searchMerchPanel.add(searchField);
        searchMerchPanel.add(searchMerchButton);

        this.add(viewMerchPanel);
        this.add(searchMerchPanel);

    }


}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Temporary sql test */

/**
 * Created by Nariman on 2017-11-11.
 */
public class CustomerTab extends JPanel {

    DatabaseButton viewMerchButton;
    DatabaseButton searchMerchButton;
    JTextField searchField;
    String searchText;

    JPanel viewMerchPanel;
    JPanel searchMerchPanel;

    //JDBC Driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    static final String USER = "username";
    static final String PASS = "password";

    static final String viewQuery = "SELECT * FROM MERCHANDISE";
    static final String searchQuery = "SELECT * FROM MERCHANDISE WHERE lower(PRODUCTNAME) LIKE ";

    public CustomerTab() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        viewMerchButton = new DatabaseButton("View Merchandise");
        searchMerchButton = new DatabaseButton("Search Merchandise");

        JLabel search = new JLabel("Search:");
        searchField = new JTextField(20);

        viewMerchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMerchButton.makeTableFromQuery(viewQuery);
            }
        });

        searchMerchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchText = searchField.getText();
                String query = searchQuery + "lower('%" + searchText + "%')";
                searchMerchButton.makeTableFromQuery(query);
            }
        });
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchText = searchField.getText();
                String query = searchQuery + "lower('%" + searchText + "%')";
                searchMerchButton.makeTableFromQuery(query);
            }
        });

        viewMerchPanel = new JPanel();
        searchMerchPanel = new JPanel();
        viewMerchPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        searchMerchPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        viewMerchPanel.add(viewMerchButton);
        searchMerchPanel.add(search);
        searchMerchPanel.add(searchField);
        searchMerchPanel.add(searchMerchButton);

        this.add(viewMerchPanel);
        this.add(searchMerchPanel);

    }


}

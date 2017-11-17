import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Nariman on 2017-11-11.
 */
public class CustomerTab extends JPanel {

    private static final String viewQuery = "SELECT * FROM MERCHANDISE";
    private static final String searchQuery = "SELECT * FROM MERCHANDISE WHERE lower(PRODUCTNAME) LIKE ";

    public CustomerTab() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        DatabaseButton viewMerchButton = new DatabaseButton("View Merchandise");
        DatabaseButton searchMerchButton = new DatabaseButton("Search Merchandise");

        JLabel search = new JLabel("Search:");
        JTextField searchField = new JTextField(20);

        viewMerchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMerchButton.makeTableFromQuery(viewQuery);
            }
        });

        searchMerchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                String query = searchQuery + "lower('%" + searchText + "%')";
                searchMerchButton.makeTableFromQuery(query);
            }
        });
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                String query = searchQuery + "lower('%" + searchText + "%')";
                searchMerchButton.makeTableFromQuery(query);
            }
        });

        JPanel viewMerchPanel = new JPanel();
        JPanel searchMerchPanel = new JPanel();
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

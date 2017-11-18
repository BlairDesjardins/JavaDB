import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Temporary sql test */

/**
 * Created by Nariman on 2017-11-11.
 */
public class EmployeeTab extends CustomerTab {

    private int currentEmployee = 2;

    JLabel customerEmailLabel, productNoLabel;
    JTextField emailField, productNoField;
    DatabaseButton issueSaleButton;

    private static final String issueSaleQuery = "INSERT INTO BUYS (PRODUCTNO, FROMSTAFF, CUSTOMER) VALUES (";

    public EmployeeTab() {
        super();

        issueSaleButton = new DatabaseButton("Issue Sale");
        customerEmailLabel = new JLabel("Customer Email:");
        productNoLabel = new JLabel("Product Number:");
        emailField = new JTextField(15);
        productNoField = new JTextField(2);

        issueSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().toUpperCase();
                String productNo = productNoField.getText();
                String command = issueSaleQuery + productNo + ", " + currentEmployee + ", '" + email + "')";
                issueSaleButton.executeCommand(command);
            }
        });


        JPanel issueSalePanel = new JPanel();
        issueSalePanel.setBorder(BorderFactory.createRaisedBevelBorder());
        issueSalePanel.setLayout(new FlowLayout());

        issueSalePanel.add(customerEmailLabel);
        issueSalePanel.add(emailField);
        issueSalePanel.add(productNoLabel);
        issueSalePanel.add(productNoField);
        issueSalePanel.add(issueSaleButton);

        this.add(issueSalePanel);
    }


}

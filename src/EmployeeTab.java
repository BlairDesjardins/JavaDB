import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Temporary sql test */

/**
 * Created by Nariman on 2017-11-11.
 */
public class EmployeeTab extends CustomerTab {

    private static final String findMgrQuery = "SELECT E.EMPNAME, M.DEPNUM FROM EMPLOYEE E, MANAGES M WHERE E.EMPNUM=M.EMPNUM";
    private static final String findEmpsQuery = "SELECT E.EMPNAME FROM EMPLOYEE E";
    private static final String issueSaleQuery = "INSERT INTO BUYS (PRODUCTNO, FROMSTAFF, CUSTOMER) VALUES (";
    private static final String checkCustomerQuery = "SELECT EMAIL FROM CUSTOMER WHERE EMAIL = '";
    private static final String addCustomerQuery = "INSERT INTO CUSTOMER (EMAIL) VALUES ('";
    private static final String updateStockNum = "UPDATE MERCHANDISE SET STOCKNUM = STOCKNUM - 1 WHERE STOCKNUM > 0 AND PRODUCTNO = ";



    private int currentEmployee = 2;
    JPanel staffSearchPanel;
    JLabel customerEmailLabel, productNoLabel;
    JTextField emailField, productNoField;
    DatabaseButton issueSaleButton, findManagersButton, findEmpsButton;


    public EmployeeTab() {
        super();

        issueSaleButton = new DatabaseButton("Issue Sale");
        findManagersButton = new DatabaseButton("Find Managers");
        findEmpsButton = new DatabaseButton("Employee List");
        customerEmailLabel = new JLabel("Customer Email:");
        productNoLabel = new JLabel("Product Number:");
        emailField = new JTextField(15);
        productNoField = new JTextField(2);

        issueSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().toUpperCase();
                if (!issueSaleButton.checkQuery(checkCustomerQuery + email + "'"))
                    issueSaleButton.executeCommand(addCustomerQuery + email + "')");
                String productNo = productNoField.getText();
                String command = issueSaleQuery + productNo + ", " + currentEmployee + ", '" + email + "')";
                issueSaleButton.executeCommand(command);
                issueSaleButton.executeCommand(updateStockNum + productNo);
            }
        });

        findManagersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findManagersButton.makeTableFromQuery(findMgrQuery);
            }
        });

        findEmpsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findEmpsButton.makeTableFromQuery(findEmpsQuery);
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

        staffSearchPanel = new JPanel();
        staffSearchPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        staffSearchPanel.setLayout(new FlowLayout());
        staffSearchPanel.add(findEmpsButton);
        staffSearchPanel.add(findManagersButton);

        this.add(issueSalePanel);
        this.add(staffSearchPanel);
    }


}

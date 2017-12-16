import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminTab extends EmployeeTab{

    private static final String viewSupps = "SELECT * FROM SUPPLIER";
    private static final String placeOrder = "INSERT INTO ORDEREDFROM(BUYCOST,QUANTITY,PRODUCTNO,SUPPLIERNAME) VALUES(";
    private static final String addEmployee = "INSERT INTO EMPLOYEE (EMPNUM, EMPNAME, SIN) VALUES (";
    private static final String addEmployeeToDept = "INSERT INTO WORKSIN (EMPNUM, DEPNUM) VALUES (";
    private static final String removeEmployee = "DELETE FROM EMPLOYEE WHERE EMPNUM = ";
    private static final String removeEmployeeFromDept = "DELETE FROM WORKSIN WHERE EMPNUM = ";
    private static final String addOrderToStock = "UPDATE MERCHANDISE SET STOCKNUM = STOCKNUM +";
    private static final String stockReturn = "UPDATE MERCHANDISE SET STOCKNUM = STOCKNUM + 1 WHERE PRODUCTNO=";
    private static final String issueRefund = "DELETE FROM BUYS WHERE ID =";
    private static final String getProduct = "SELECT PRODUCTNO FROM BUYS WHERE ID =";


    DatabaseButton supplierButton, mailingListButton, refundButton, empDetailsButton, merchButton, orderButton;
    JPanel supplierPanel, mailingListPanel, refundPanel, empDetailsPanel,merchPanel,orderPanel;
    JTextField id, quantity, refundField;

    String typeOrdered, prodOrdered, amountOrdered, refundString, prodString;

    //Employee Window Stuff
    JLabel empNum, empName, pstn, SIN, salary, address, dept, refund;
    JTextField empNumField, empNameField, pstnField, SINField, salaryField, addressField, deptField, emailField, usernameField, passwordField;

    public AdminTab() {
        super();

        supplierPanel = new JPanel();
        empDetailsPanel = new JPanel();
        mailingListPanel = new JPanel();
        refundPanel = new JPanel();
        merchPanel = new JPanel();
        orderPanel = new JPanel();

        supplierPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        empDetailsPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        mailingListPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        refundPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        merchPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        orderPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        setSupplierPanel();
        setEmpDetailsPanel();
        setRefundPanel();

        this.add(supplierPanel);
        this.add(refundPanel);
    }

    public void setSupplierPanel(){
        final DatabaseButton getSupps = new DatabaseButton("View All Suppliers");
        final DatabaseButton orderStuff = new DatabaseButton("Place an Order");

        //To see all of the suppliers
        getSupps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSupps.makeTableFromQuery(viewSupps);
            }
        });

        //Creates a popup window to deal with ordering
        orderStuff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOrderWindow();
            }
        });

        supplierPanel.add(getSupps);
        supplierPanel.add(orderStuff);

    }

    public void createOrderWindow(){
        String[] choices = {"Music", "Movies", "Apparel", "Other"};

        final DatabaseButton submitButton = new DatabaseButton("Submit Order");
        final JFrame orderWindow = new JFrame("Order Manager");
        final JComboBox<String> dropDown = new JComboBox<String>(choices);
            dropDown.setVisible(true);

        JLabel prodType = new JLabel("Type:");
        JLabel prodNum = new JLabel("Product ID:");
        JLabel prodQ = new JLabel("Quantity:");

        id = new JTextField(8);
        quantity = new JTextField(5);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeOrdered = dropDown.getSelectedItem().toString();
                prodOrdered = id.getText();
                amountOrdered = quantity.getText();

                String cName = submitButton.executeCommand("SELECT COMPANYNAME FROM SUPPLIER S WHERE S.MERCHTYPE='" + typeOrdered + "'","COMPANYNAME");
                if(cName != null) {
                    submitButton.executeCommand(placeOrder + amountOrdered + "," + amountOrdered + "," + prodOrdered + ",'" + cName + "')");
                    updateStockedItems(prodOrdered, amountOrdered);
                }
            }
        });

        orderWindow.setLayout(new FlowLayout());
        orderWindow.add(Box.createVerticalStrut(100));
        orderWindow.add(prodType);
        orderWindow.add(dropDown);
        orderWindow.add(Box.createHorizontalStrut(400));
        orderWindow.add(Box.createVerticalStrut(100));
        orderWindow.add(prodNum);
        orderWindow.add(id);
        orderWindow.add(Box.createHorizontalStrut(400));
        orderWindow.add(Box.createVerticalStrut(100));
        orderWindow.add(prodQ);
        orderWindow.add(quantity);
        orderWindow.add(Box.createHorizontalStrut(400));
        orderWindow.add(Box.createVerticalStrut(100));
        orderWindow.add(submitButton);

        orderWindow.setSize(400,450);
        orderWindow.setVisible(true);

    }

    public void setEmpDetailsPanel() {
        final DatabaseButton manEmps = new DatabaseButton("Manage Employees");

        manEmps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEmployeeWindow();
            }
        });
        super.staffSearchPanel.add(manEmps);
//        empDetailsPanel.add(manEmps);

    }

    public void createEmployeeWindow() {

        final JFrame employeeWindow = new JFrame("Employee Manager");

        final DatabaseButton addEmpButton = new DatabaseButton("Add Employee");
        final DatabaseButton removeEmpButton = new DatabaseButton("Remove Employee");

        empNum = new JLabel("Employee Number:");
        empName = new JLabel("Employee Name:");
        pstn = new JLabel("Position");
        SIN = new JLabel("SIN:");
        salary = new JLabel("Salary:");
        address = new JLabel("Address");
        dept = new JLabel("Department:");

        empNumField = new JTextField(2);
        empNameField = new JTextField(15);
        pstnField = new JTextField(10);
        SINField = new JTextField(9);
        salaryField = new JTextField(9);
        addressField = new JTextField(15);
        emailField = new JTextField(15);
        deptField = new JTextField(2);

        addEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empNumString = empNumField.getText();
                String empNameString = empNameField.getText();
                String SINString = SINField.getText();
                String deptString = deptField.getText();
                addEmpButton.executeCommand(addEmployee + empNumString + ", '" + empNameString + "', " + SINString + ")");
                addEmpButton.executeCommand(addEmployeeToDept + empNumString + ", " + deptString + ")");
            }
        });

        removeEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empNumString = empNumField.getText();
                addEmpButton.executeCommand(removeEmployeeFromDept + empNumString);
                addEmpButton.executeCommand(removeEmployee + empNumString);
            }
        });

        employeeWindow.setLayout(new FlowLayout());
        employeeWindow.add(Box.createVerticalStrut(100));
        employeeWindow.add(empNum);
        employeeWindow.add(empNumField);
        employeeWindow.add(Box.createHorizontalStrut(400));
        employeeWindow.add(Box.createVerticalStrut(100));
        employeeWindow.add(empName);
        employeeWindow.add(empNameField);
        employeeWindow.add(Box.createHorizontalStrut(400));
        employeeWindow.add(Box.createVerticalStrut(100));
        employeeWindow.add(SIN);
        employeeWindow.add(SINField);
        employeeWindow.add(Box.createHorizontalStrut(400));
        employeeWindow.add(Box.createVerticalStrut(100));
        employeeWindow.add(dept);
        employeeWindow.add(deptField);
        employeeWindow.add(Box.createHorizontalStrut(400));
        employeeWindow.add(Box.createVerticalStrut(100));
        employeeWindow.add(addEmpButton);
        employeeWindow.add(removeEmpButton);

        employeeWindow.setSize(400,550);
        employeeWindow.setVisible(true);
    }

    public void updateStockedItems(String id, String quantity){
        DatabaseButton tempButton = new DatabaseButton("temp");
        System.out.println(addOrderToStock + quantity + " WHERE PRODUCTNO=" + id);
        tempButton.executeCommand(addOrderToStock + quantity + "WHERE PRODUCTNO=" + id);

    }
    public void setRefundPanel(){
        refund = new JLabel("Refund ID:");
        refundField = new JTextField(5);
        refundButton = new DatabaseButton("Issue Refund");

        refundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refundString = refundField.getText();
                prodString = refundButton.executeCommand(getProduct + refundString,"PRODUCTNO");
                System.out.println(prodString);
                refundButton.executeCommand(issueRefund + refundString);
                refundButton.executeCommand(stockReturn + prodString);

            }
        });
        refundPanel.add(refund);
        refundPanel.add(refundField);
        refundPanel.add(refundButton);

    }
}

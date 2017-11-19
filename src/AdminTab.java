import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminTab extends EmployeeTab{

    private static final String viewSupps = "SELECT * FROM SUPPLIER";
    private static final String placeOrder = "INSERT INTO ORDEREDFROM(BUYCOST,QUANTITY,PRODUCTNO,SUPPLIERNAME) VALUES(";
//    private static final String getBuyCost = "SELECT BUYCOST FROM SUPPLIER WHERE EMP"

    JPanel supplierPanel, mailingListPanel, refundPanel, empDetailsPanel,merchPanel,orderPanel;
    DatabaseButton supplierButton, mailingListButton, refundButton, empDetailsButton, merchButton, orderButton;

    String typeOrdered, prodOrdered, amountOrdered;
    JTextField id, quantity;



    public AdminTab() {
        super();

        supplierPanel = new JPanel();
        mailingListPanel = new JPanel();
        refundPanel = new JPanel();
        empDetailsPanel = new JPanel();
        merchPanel = new JPanel();
        orderPanel = new JPanel();

        supplierPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        mailingListPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        refundPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        empDetailsPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        merchPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        orderPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        setSupplierPanel();

        this.add(supplierPanel);
        this.add(mailingListPanel);
        this.add(refundPanel);
        this.add(empDetailsPanel);
        this.add(merchPanel);
        this.add(orderPanel);
    }

    public void setSupplierPanel(){
        final DatabaseButton getSupps = new DatabaseButton("View All Suppliers");
        final DatabaseButton orderStuff = new DatabaseButton("Place an Order");


        getSupps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSupps.makeTableFromQuery(viewSupps);
            }
        });

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
        final JFrame orderWindow = new JFrame("Order Manager");
        String[] choices = {"Music", "Movies", "Apparel", "Other"};


        JLabel prodType, prodNum, prodQ;
        final DatabaseButton submitButton = new DatabaseButton("Submit Order");


        prodType = new JLabel("Type:");
        prodNum = new JLabel("Product ID:");
        prodQ = new JLabel("Quantity:");

        id = new JTextField(8);
        quantity = new JTextField(5);

        final JComboBox<String> dropDown = new JComboBox<String>(choices);
            dropDown.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeOrdered = dropDown.getSelectedItem().toString();
                prodOrdered = id.getText();
                amountOrdered = quantity.getText();

                System.out.println(typeOrdered);
                System.out.println(prodOrdered);
                System.out.println(amountOrdered);

                String cName = submitButton.executeCommand("SELECT COMPANYNAME FROM SUPPLIER S WHERE S.MERCHTYPE='" + typeOrdered + "'","COMPANYNAME");
                if(cName != null) {
                    submitButton.executeCommand(placeOrder + amountOrdered + "," + amountOrdered + "," + prodOrdered + ",'" + cName + "')");
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

    public void setMailingListPanel(){}
    public void setRefundPanel(){}
    public void setEmpDetailsPanel(){}
    public void setMerchPanel(){}
    public void setOrderPanel(){}
}

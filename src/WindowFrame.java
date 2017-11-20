/**
 * Created by Nariman on 2017-11-11.
 */


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class WindowFrame extends JFrame {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1234:orcl";
    private static final String USER = "nsaftarl";
    private static final String PASS = "04165448";

    Connection conn;
    Statement stmt;

    private JMenuBar bar;
    private JMenu file;
    private JMenuItem close;

    private JMenu ops;
    private JMenuItem runQuery;

    private JTabbedPane tabbedPane;
    private JPanel custTab, empTab, admTab;

    boolean loggedIn;

    public WindowFrame() {

        instantiate();

        class ExitListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        ActionListener exitListener = new ExitListener();
        close.addActionListener(exitListener);

        class WindowClosingListener extends WindowAdapter {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        WindowListener windowClosingListener = new WindowClosingListener();
        this.addWindowListener(windowClosingListener);
    }

    private void instantiate(){
        bar = new JMenuBar();
        file = new JMenu("File");
        close = new JMenuItem("Exit");
        file.add(close);

        ops = new JMenu("Operations");
        runQuery = new JMenuItem("Run Query");
        ops.add(runQuery);
        bar.add(file);
        bar.add(ops);

        tabbedPane = new JTabbedPane();

        custTab = new CustomerTab();
        empTab = new EmployeeTab();
        admTab = new AdminTab();
        tabbedPane.addTab("Customer Tab", custTab);
        tabbedPane.addTab("Employee Tab", empTab);
        tabbedPane.addTab("Admin Tab", admTab);

        addTabListeners();
        this.add(tabbedPane);

    }

    public void addTabListeners() {
        final JTextField userField = new JTextField(20);
        final JPasswordField passwordField = new JPasswordField(20);
        final JPanel loginPanel = new JPanel();
        final JOptionPane optionPane = new JOptionPane();

        JButton[] buttonList = new JButton[2];
            buttonList[0] = new JButton("Submit");
            buttonList[1] = new JButton("Cancel");

        loginPanel.add(new JLabel("Username: "));
        loginPanel.add(userField);
        loginPanel.add(Box.createHorizontalStrut(15));
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(passwordField);
        loginPanel.setSize(300,300);


        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if(tabbedPane.getSelectedIndex() == 1 || tabbedPane.getSelectedIndex() == 2) {
                    optionPane.setSize(300,300);
                    optionPane.setVisible(true);
                    optionPane.createDialog(loginPanel,"sakdfljasd");
                    int choice = optionPane.showConfirmDialog(
                            tabbedPane,
                            loginPanel,
                            "Log In",
                            JOptionPane.OK_CANCEL_OPTION
                    );
                    System.out.println(choice);

                    if(choice == 0) {
                        loggedIn = tryToLogIn(userField.getText(), new String(passwordField.getPassword()));
                        if(!loggedIn)
                            tabbedPane.setSelectedIndex(0);
                    }
                    else {
                        tabbedPane.setSelectedIndex(0);
                    }
                }
            }
        });

    }

    public boolean tryToLogIn(String username, String password) {
        String loginQuery = "SELECT * FROM EMPLOYEE WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "'";


        try {
            //Register JDBC Driver
            Class.forName(JDBC_DRIVER);

            //Open connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Execute query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(loginQuery);

            if(isResultSetEmpty(rs)) {
                System.out.println("Command executed!");
                System.out.println("USERNAME AND/OR PASSWORD ARE WRONG");
                stmt.close();
                conn.close();
                return false;
            }
            else {
                System.out.println("Command executed!");
                System.out.println("LOGGED IN");
                stmt.close();
                conn.close();
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
            } catch(SQLException se2) {}
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch(SQLException se) {
                se.printStackTrace();
            }

        }
        return false;

    }

    public boolean isResultSetEmpty(ResultSet rs) {
        try {
            if (!rs.next()) {
                return true;
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }
        return false;
    }

}
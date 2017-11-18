/**
 * Created by Nariman on 2017-11-11.
 */

import javax.swing.*;
import java.awt.event.*;

public class WindowFrame extends JFrame {

    private JMenuBar bar;
    private JMenu file;
    private JMenuItem close;

    private JMenu ops;
    private JMenuItem runQuery;

    private JTabbedPane tabbedPane;
    private JPanel custTab, empTab, admTab;

    public WindowFrame() {

        instantiate();

//        Object[] options = {"Guest", "Employee", "Administrator"};
//
//        int n = JOptionPane.showOptionDialog(
//                this,
//                "Which type of account would you like to log in as?\n",
//                "Log In",
//                JOptionPane.YES_NO_CANCEL_OPTION,
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                options,
//                options[2]
//        );


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
        //this.add(bar, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();

        custTab = new CustomerTab();
        empTab = new EmployeeTab();
        admTab = new AdminTab();
        tabbedPane.addTab("Customer Tab", custTab);
        tabbedPane.addTab("Employee Tab", empTab);
        tabbedPane.addTab("Admin Tab", admTab);

        this.add(tabbedPane);

    }

    public void addTabListeners() {

    }

}
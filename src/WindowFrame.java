/**
 * Created by Nariman on 2017-11-11.
 */

import javax.swing.*;
import java.awt.event.*;

public class WindowFrame extends JFrame {

    private JMenuItem close;

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
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        close = new JMenuItem("Exit");
        file.add(close);

        JMenu ops = new JMenu("Operations");
        JMenuItem runQuery = new JMenuItem("Run Query");
        ops.add(runQuery);
        bar.add(file);
        bar.add(ops);
        //this.add(bar, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel custTab = new CustomerTab();
        JPanel empTab = new EmployeeTab();
        JPanel admTab = new AdminTab();
        tabbedPane.addTab("Customer Tab", custTab);
        tabbedPane.addTab("Employee Tab", empTab);
        tabbedPane.addTab("Admin Tab", admTab);

        this.add(tabbedPane);

    }

}
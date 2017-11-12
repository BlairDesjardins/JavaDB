/**
 * Created by Nariman on 2017-11-11.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowFrame extends JFrame {
    JMenuBar bar;
    JMenu file;
    JMenuItem close;
    JMenu ops;
    JMenuItem runQuery;
    JMenuBar bar2;

    JPanel mainComp;


    JButton empMode, adminMode;

    JPanel upperBar;
    JTextField txtUser;
    JPasswordField txtpass;



    public WindowFrame() {

        instantiate();


        class ExitListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        ActionListener exitListener = new ExitListener();
        close.addActionListener(exitListener);

    }

    public void instantiate(){
        bar = new JMenuBar();
        file = new JMenu("File");
        close = new JMenuItem("Exit");
        file.add(close);

        upperBar = new JPanel();
        JPanel lowerBar = new JPanel();
        lowerBar.setLayout(new GridLayout(0,2));

        GridLayout topArea = new GridLayout(2,0);
        upperBar.setLayout(topArea);

        ops = new JMenu("Operations");
        runQuery = new JMenuItem("Run Query");
        ops.add(runQuery);
        bar.add(file);
        bar.add(ops);
//        this.add(bar, BorderLayout.NORTH);
        upperBar.add(bar);
        upperBar.add(lowerBar);

        lowerBar.add(new JTextField("Enter Username"));
        lowerBar.add(new JPasswordField("Enter Password"));

        this.add(upperBar,BorderLayout.NORTH);
        mainComp = new MainComponent();
        this.add(mainComp);
    }

}

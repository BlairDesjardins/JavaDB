/**
 * Created by Nariman on 2017-11-11.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
public class WindowFrame extends JFrame {

    public WindowFrame() {
        JMenuBar bar;
            JMenu file;
                JMenuItem close;
            JMenu ops;
                JMenuItem runQuery;

        JPanel mainComp;

        JButton empMode, adminMode;


        bar = new JMenuBar();
            file = new JMenu("File");
                close = new JMenuItem("Exit");
            file.add(close);

            ops = new JMenu("Operations");
                runQuery = new JMenuItem("Run Query");
            ops.add(runQuery);
        bar.add(file);
        bar.add(ops);
        this.add(bar, BorderLayout.NORTH);

        mainComp = new MainComponent();
        this.add(mainComp);


        class ExitListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        ActionListener exitListener = new ExitListener();
        close.addActionListener(exitListener);
        
    }

}

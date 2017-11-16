/**
 * Created by Nariman on 2017-11-11.
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class WindowFrame extends JFrame {
    JMenuBar bar;
        JMenu file;
            JMenuItem close;
        JMenu ops;
            JMenuItem runQuery;

    JComponent mainComp;
    JPanel upperBar;
    JTextField txtUser;
    JPasswordField txtPass;

    ButtonPanel buttonPanel;
    JPanel textFieldPanel;
        JButton loginButton;
    SessionManager mgr;

    JPanel windowBody;


    public WindowFrame() {
        instantiate();
    }

    public void instantiate(){

        //Top Bar
        bar = new JMenuBar();
            file = new JMenu("File");
                close = new JMenuItem("Exit");
            file.add(close);

            ops = new JMenu("Operations");
                runQuery = new JMenuItem("Run Query");
            ops.add(runQuery);
        bar.add(file);
        bar.add(ops);
        setBarListeners();
        this.add(bar, BorderLayout.NORTH);

        //Body of window
        buttonPanel = new ButtonPanel();
        setButtonListeners();
        this.add(buttonPanel);
//
        windowBody = new JPanel();
        this.add(windowBody,BorderLayout.SOUTH);


//
//        mainComp = new MainComponent();
//        this.add(mainComp);

    }
    public void setBarListeners(){
        //Exit Program Listener
        class ExitListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        ActionListener exitListener = new ExitListener();
        close.addActionListener(exitListener);
    }

    public void setButtonListeners(){
        //Employee Button Listener
        buttonPanel.getButton("Employee").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                buttonPanel.hideLoginButtons();
                buttonPanel.showEmpButtons();
                textFieldPanel = new JPanel(new GridLayout(1,3));
                    txtUser = new JTextField("Username");
                    txtPass = new JPasswordField("Password");
                    loginButton = new JButton("Submit");
                textFieldPanel.add(txtUser);
                textFieldPanel.add(txtPass);
                textFieldPanel.add(loginButton);
                addloginListener();
                windowBody.add(textFieldPanel);
                windowBody.setVisible(true);

                repaint();
                revalidate();

            }
        });
    }

    public void hideButtons(){
        this.remove(buttonPanel);
        repaint();
    }

    public void addloginListener(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUser.getText();
                String pass = txtPass.getPassword().toString();
                System.out.println(user);
                System.out.println(pass);
                mgr = new SessionManager("Employee",user,pass);
            }
        });
    }


}

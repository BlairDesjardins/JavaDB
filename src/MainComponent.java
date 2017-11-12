import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nariman on 2017-11-11.
 */
public class MainComponent extends JPanel {

    JButton empButton, adminButton;

    public MainComponent() {


        empButton = new JButton("Employee");
        adminButton = new JButton("Administrator");

        addEmpListener();
        addAdmListener();


        this.add(empButton);
        this.add(adminButton);


    }

    public void addEmpListener() {
        empButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Run Employee Login Code Here");
                System.out.println("Logged in!");
            }
        });
    }

    public void addAdmListener() {
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Run Admin Login Code Here");
                System.out.println("Logged in!");

            }
        });
    }


}

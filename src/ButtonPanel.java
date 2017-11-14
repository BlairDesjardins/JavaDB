import javax.swing.*;

/**
 * Created by Nariman on 2017-11-14.
 */
public class ButtonPanel extends JPanel {
    JButton empButton, adminButton;

    public ButtonPanel() {
        super();
        empButton = new JButton("Employee");
        adminButton = new JButton("Administrator");

        this.add(empButton);
        this.add(adminButton);
    }

    public JButton getButton(String buttonName) {
        switch(buttonName){
            case "Employee" : return empButton;
            case "Administrator" : return adminButton;
        }
        return null;
    }

}

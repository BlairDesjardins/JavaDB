import javax.swing.*;

/**
 * Created by Nariman on 2017-11-17.
 */
public class LoginWindow extends JFrame {

    JRadioButton cusButton, empButton, adminButton;
    ButtonGroup accessSelect;
    JPanel panel;


    public LoginWindow() {
        cusButton = new JRadioButton("Customer");
        empButton = new JRadioButton("Employee");
        adminButton = new JRadioButton("Administrator");
        accessSelect = new ButtonGroup();
            accessSelect.add(cusButton);
            accessSelect.add(empButton);
            accessSelect.add(adminButton);
        panel = new JPanel();
        panel.add(cusButton);
        panel.add(empButton);
        panel.add(adminButton);

        this.add(panel);

    }
}

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nariman on 2017-11-14.
 */
public class ButtonPanel extends JPanel {
    JButton empButton, adminButton, saleButton, merchTableButton, submitButton;

    public ButtonPanel() {
        super();
        empButton = new JButton("Employee");
        adminButton = new JButton("Administrator");
        saleButton = new JButton("New Sale");

        this.add(empButton);
        this.add(adminButton);
    }

    public JButton getButton(String buttonName) {
        switch(buttonName){
            case "Employee" : return empButton;
            case "Administrator" : return adminButton;
            case "Sale" : return saleButton;
        }
        return null;
    }

    public void hideLoginButtons(){
        this.remove(empButton);
        this.remove(adminButton);
        repaint();
        revalidate();
    }
    public void showEmpButtons() {
        this.add(saleButton, BorderLayout.SOUTH);
        saleButton.setVisible(true);

        repaint();
        revalidate();

    }

}

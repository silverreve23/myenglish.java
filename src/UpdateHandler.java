import java.awt.event.*;
import javax.swing.*;

class UpdateHandler implements ActionListener {

    private JDialog dialogModalUpdate;

    public UpdateHandler(JDialog dModal) {
        dialogModalUpdate = dModal;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            dialogModalUpdate.setVisible(false);
            Runtime.getRuntime().exec("ls -l");
            System.out.println("updated!");
            System.exit(0);
        } catch (Exception exc) {
        }
    }
}

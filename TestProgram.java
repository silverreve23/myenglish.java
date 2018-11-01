import javax.swing.*;
import java.awt.*;
import java.awt.Window.*;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.ImageIcon;

public class TestProgram {
	public static void main(String args[]){
		JPanel panel = new JPanel();
		JButton button = new JButton();
		JTextField textField = new JTextField(10);
		Dimension labelTextSize = new Dimension(200, 100);
		Dimension ImageSize = new Dimension(600, 350);
		JDialog window = new JDialog(new Frame(), true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Integer xSize = (int)toolkit.getScreenSize().getWidth();
		Integer ySize = (int)toolkit.getScreenSize().getHeight();
		String textButton = "Answer!";
		String imageIconPath = "./icon.jpg";
		String imageBodyPath = "./sbk.png";
		String fontName = "SansSerif";
		Font fontTextField = new Font(fontName, Font.BOLD, 29);
		Image ImageIcon = toolkit.getImage(imageIconPath);
		Image ImageBody = toolkit.getImage(imageBodyPath);
		JLabel labelIcon = new JLabel(new ImageIcon(ImageBody));
		JLabel labelText = new JLabel();

		labelIcon.setPreferredSize(ImageSize);
        labelText.setText("Apple:");
        labelText.setForeground(Color.WHITE);
        labelText.setFont(fontTextField);
        labelText.setPreferredSize(labelTextSize);
		textField.setFont(fontTextField);
		textField.setBackground(Color.BLACK);
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		textField.addActionListener(new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        panel.setBackground(Color.BLACK);
		panel.add(labelText);
		panel.add(textField);
		panel.add(labelIcon);
		window.add(panel);
		window.setResizable(false);
		window.setAlwaysOnTop(true);
		window.setUndecorated(true);
		window.setIconImage(ImageIcon);
		window.setSize(xSize / 2, ySize / 2);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}

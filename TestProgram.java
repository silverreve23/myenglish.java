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
		Dimension buttonSize = new Dimension(100, 40);
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

		labelIcon.setPreferredSize(ImageSize);
		textField.setFont(fontTextField);
		textField.setBackground(Color.GRAY);
		textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		button.setPreferredSize(buttonSize);
		button.setBackground(Color.GRAY);
		button.setText(textButton);
		panel.setBackground(Color.black);
		panel.add(textField);
		panel.add(button);
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

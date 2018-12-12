import javax.swing.*;
import java.awt.*;
import java.awt.Window.*;
import java.awt.event.*;
import java.awt.Image;

import java.util.*;

class ModalApp {
	private JPanel panel;
	private JButton button;
	private JTextField textField;
	private Dimension labelTextSize;
	private Dimension ImageSize;
	private JDialog window;
	private Toolkit toolkit;
	private Integer xScreenSize;
	private Integer yScreenSize;
	private String imageIconPath;
	private String imageBodyPath;
	private String windowTitle;
	private String fontName;
	private Font fontTextField;
	private Image ImageIcon;
	private Image ImageBody;
	private JLabel labelIcon;
	private JLabel labelText;
	private Integer maxAttempt;
	private TranslateHandler transHandler;
	private WordsModel wordModel;
	
	public ModalApp(WordsModel wModel){
		wordModel = wModel;
	}
	
	public void init(){
		panel = new JPanel();
		button = new JButton();
		textField = new JTextField(10);
		labelTextSize = new Dimension(200, 100);
		ImageSize = new Dimension(600, 350);
		window = new JDialog(new Frame(), true);
		toolkit = Toolkit.getDefaultToolkit();
		xScreenSize = (int)toolkit.getScreenSize().getWidth();
		yScreenSize = (int)toolkit.getScreenSize().getHeight();
		imageIconPath = "img/icon.jpg";
		imageBodyPath = "img/background.png";
		windowTitle = "MyEnglish";
		fontName = "SansSerif";
		fontTextField = new Font(fontName, Font.BOLD, 29);
		ImageIcon = toolkit.getImage(MyEnglish.class.getResource(imageIconPath));
		ImageBody = toolkit.getImage(MyEnglish.class.getResource(imageBodyPath));
		labelIcon = new JLabel(new ImageIcon(ImageBody));
		labelText = new JLabel();
		transHandler = new TranslateHandler(
			window,
			wordModel,
			labelText,
			textField
		);
	}
	
	public void run(){
		maxAttempt = 2;
		labelIcon.setPreferredSize(ImageSize);
	    labelText.setForeground(Color.WHITE);
	    labelText.setFont(fontTextField);
	    labelText.setPreferredSize(labelTextSize);
		textField.setFont(fontTextField);
		textField.setBackground(Color.BLACK);
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		textField.addActionListener(transHandler);
    	panel.setBackground(Color.BLACK);
		panel.add(labelText);
		panel.add(textField);
		panel.add(labelIcon);
		window.add(panel);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setResizable(false);
		window.setAlwaysOnTop(true);
		window.setUndecorated(true);
		window.setIconImage(ImageIcon);
		window.setTitle(windowTitle);
		window.setSize(xScreenSize / 2, yScreenSize / 2);
		window.setLocationRelativeTo(null);
	}
	
	public void render(){
		wordModel.update();
		labelText.setText(wordModel.word + ":");
	}
	
	public void show(){
		window.setVisible(true);
	}
	
	public void hide(){
		window.setVisible(false);
	}
}

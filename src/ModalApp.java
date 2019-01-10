import javax.swing.*;
import java.awt.*;
import java.awt.Window.*;
import java.awt.event.*;
import java.awt.Image;

import java.util.*;

class ModalApp {
	private JPanel panel;
	private JPanel panelModalUpdate;
	private JButton buttonUpdateModal;
	private JTextField textField;
	private Dimension labelTextSize;
	private Dimension ImageSize;
	private Dimension ImageIconSize;
	private JDialog window;
	private Toolkit toolkit;
	private Integer xScreenSize;
	private Integer yScreenSize;
	private String imageIconPath;
	private String imageBodyPath;
	private String imagePlayPath;
	private String imageHomePath;
	private String windowTitle;
	private String fontName;
	private Font fontTextField;
	private Image ImageIcon;
	private Image ImageBody;
	private Image ImagePlay;
	private Image ImageHome;
	private JLabel labelIcon;
	private JLabel labelText;
	private JLabel labelHint;
	private JLabel labelLang;
	private JLabel labelSong;
	private JLabel labelHome;
	private JLabel labelUpdateModal;
	private JDialog dialogModalUpdate;
	private Integer maxAttempt;
	private TranslateHandler transHandler;
	private PlayHandler playHandler;
	private HomeHandler homeHandler;
	private UpdateHandler updateHandler;
	private WordsModel wordModel;

	public ModalApp(WordsModel wModel) {
		wordModel = wModel;
	}

	public void init() {
		buttonUpdateModal = new JButton("update now!");
		panel = new JPanel();
		panelModalUpdate = new JPanel();
		textField = new JTextField(25);
		ImageSize = new Dimension(600, 350);
		ImageIconSize = new Dimension(30, 30);
		window = new JDialog(new Frame(), true);
		toolkit = Toolkit.getDefaultToolkit();
		xScreenSize = (int) toolkit.getScreenSize().getWidth();
		yScreenSize = (int) toolkit.getScreenSize().getHeight();
		labelTextSize = new Dimension((xScreenSize / 2) - 20, 50);
		imageIconPath = "img/icon.png";
		imageBodyPath = "img/background.png";
		imagePlayPath = "img/play.png";
		imageHomePath = "img/home.png";
		windowTitle = "MyEnglish";
		fontName = "SansSerif";
		fontTextField = new Font(fontName, Font.BOLD, 29);
		ImageIcon = toolkit.getImage(MyEnglish.class.getResource(imageIconPath));
		ImageBody = toolkit.getImage(MyEnglish.class.getResource(imageBodyPath));
		ImagePlay = toolkit.getImage(MyEnglish.class.getResource(imagePlayPath));
		ImageHome = toolkit.getImage(MyEnglish.class.getResource(imageHomePath));
		labelIcon = new JLabel(new ImageIcon(ImageBody));
		labelText = new JLabel();
		labelHint = new JLabel();
		labelLang = new JLabel();
		labelSong = new JLabel(new ImageIcon(ImagePlay));
		labelHome = new JLabel(new ImageIcon(ImageHome));
		labelUpdateModal = new JLabel();
		dialogModalUpdate = new JDialog(new JFrame(), "New version update!", true);
		playHandler = new PlayHandler();
		homeHandler = new HomeHandler();
		updateHandler = new UpdateHandler(dialogModalUpdate);
		transHandler = new TranslateHandler(window, wordModel, labelText, textField);
	}

	public void run() {
		maxAttempt = 2;
		labelIcon.setPreferredSize(ImageSize);
		labelText.setForeground(Color.WHITE);
		labelText.setFont(fontTextField);
		labelText.setPreferredSize(labelTextSize);
		labelLang.setForeground(new Color(179, 210, 54));
		labelHint.setForeground(new Color(252, 211, 43));
		labelSong.setPreferredSize(ImageIconSize);
		labelSong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelSong.addMouseListener(playHandler);
		labelHome.setPreferredSize(ImageIconSize);
		labelHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		labelHome.addMouseListener(homeHandler);
		textField.setFont(fontTextField);
		textField.setBackground(Color.BLACK);
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		textField.addActionListener(transHandler);

		buttonUpdateModal.addActionListener(updateHandler);
		panelModalUpdate.add(labelUpdateModal);
		panelModalUpdate.add(buttonUpdateModal);
		dialogModalUpdate.add(panelModalUpdate);
		dialogModalUpdate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialogModalUpdate.setSize(xScreenSize / 6, yScreenSize / 8);
		dialogModalUpdate.setLocationRelativeTo(null);

		panel.setBackground(Color.BLACK);
		panel.add(labelText);
		panel.add(labelHint);
		panel.add(labelLang);
		panel.add(textField);
		panel.add(labelSong);
		panel.add(labelHome);
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

	public void render() throws Exception {
		wordModel.update();
		labelText.setText(" " + wordModel.word + "? - ");
		labelHint.setText(" hint: " + wordModel.hint + " ");
		labelLang.setText(" lang: " + wordModel.translang + " ");
		labelUpdateModal.setText(wordModel.updatetext);
		if (wordModel.updatetext != "")
			dialogModalUpdate.setVisible(true);
	}

	public void show() {
		window.setVisible(true);
	}

	public void hide() {
		window.setVisible(false);
	}
}

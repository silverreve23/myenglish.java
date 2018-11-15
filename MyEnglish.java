import javax.swing.*;
import java.awt.*;
import java.awt.Window.*;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.*;

import java.util.*;
import java.io.*;
import java.net.URL;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyEnglish{
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
		Integer maxAttempt = 2;
		String imageIconPath = "img/icon.jpg";
		String imageBodyPath = "img/background.png";
		String windowTitle = "MyEnglish";
		String fontName = "SansSerif";
		Font fontTextField = new Font(fontName, Font.BOLD, 29);
		Image ImageIcon = toolkit.getImage(MyEnglish.class.getResource(imageIconPath));
		Image ImageBody = toolkit.getImage(MyEnglish.class.getResource(imageBodyPath));
		JLabel labelIcon = new JLabel(new ImageIcon(ImageBody));
		JLabel labelText = new JLabel();
    	WordsModel wordModel = new WordsModel();

		labelIcon.setPreferredSize(ImageSize);
	    labelText.setText(wordModel.word + ":");
	    labelText.setForeground(Color.WHITE);
	    labelText.setFont(fontTextField);
	    labelText.setPreferredSize(labelTextSize);
		textField.setFont(fontTextField);
		textField.setBackground(Color.BLACK);
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		textField.addActionListener(new AbstractAction(){
	        private Integer attempt = 0;
	        public void actionPerformed(ActionEvent e){
	            attempt = wordModel.checkTranslate(textField);
	            if(attempt > maxAttempt) labelText.setText(wordModel.trans);
	        }
	    });
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
		window.setSize(xSize / 2, ySize / 2);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}

class WordsModel {
    public String word;
    public String trans;
    public Integer attempt = 0;
	private Statement stmt;
    public WordsModel(){
        try{
            String url = "jdbc:sqlite:db/words.db";
            Connection conn = DriverManager.getConnection(url);
            String sqlSelectWord = "SELECT * FROM words ORDER BY status DESC LIMIT 1";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelectWord);
            word = rs.getString("word");
            trans = rs.getString("trans");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int checkTranslate(JTextField translate){
		try{
			if(trans.equals(translate.getText())){
				String sqlUpdateStatus = "UPDATE words SET status=status-1 WHERE word='" + word + "'";
				stmt.executeUpdate(sqlUpdateStatus);
				System.exit(0);
			}
			String sqlUpdateStatus = "UPDATE words SET status=status+1 WHERE word='" + word + "'";
			stmt.executeUpdate(sqlUpdateStatus);
		}catch(Exception e){
            System.out.println(e.getMessage());
			System.exit(0);
        }

		translate.setText(null);
        return ++attempt;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.Window.*;
import java.awt.event.*;
import java.awt.Image;
import javax.swing.*;

import java.util.*;
import java.io.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String imageIconPath = "../img/icon.jpg";
		String imageBodyPath = "../img/sbk.png";
		String fontName = "SansSerif";
		String labelTextWord = "Word:";
		String labelTextTrans = "";
		Font fontTextField = new Font(fontName, Font.BOLD, 29);
		Image ImageIcon = toolkit.getImage(imageIconPath);
		Image ImageBody = toolkit.getImage(imageBodyPath);
		JLabel labelIcon = new JLabel(new ImageIcon(ImageBody));
		JLabel labelText = new JLabel();

        try{
            String url = "jdbc:sqlite:../db/words.db";
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT * FROM words LIMIT 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                labelTextWord = rs.getString("word") + ":";
                labelTextTrans = rs.getString("trans");
                System.out.println(rs.getInt("id"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

		labelIcon.setPreferredSize(ImageSize);
        labelText.setText(labelTextWord);
        labelText.setForeground(Color.WHITE);
        labelText.setFont(fontTextField);
        labelText.setPreferredSize(labelTextSize);
		textField.setFont(fontTextField);
		textField.setBackground(Color.BLACK);
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		textField.addActionListener(new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                // if(this.labelTextTrans == "");
                System.out.println(labelTextTrans);
                // System.exit(0);
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
		window.setSize(xSize / 2, ySize / 2);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}

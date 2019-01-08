import javax.swing.*;
import java.net.URL;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*;

class WordsModel {
	public String word;
	public String trans;
	public String hint;
	public String translang;
	public String updatetext;
	public Integer attempt;
	public Integer period;
	public Boolean chkeyboard;
	private String response;
	private String userEmail;
	private String wordLang;
	private String transLang;
	private String host;
	private String url;
	private JSONParser parser;
	private BufferedReader reader;
	private InputStream stream;
	private JSONObject json;
	private BufferedWriter writer;

	public WordsModel() throws Exception {
		parser = new JSONParser();
		host = "http://35.182.114.21";
		host = "http://myenglish.io";
		Properties props = new Properties();
		props.load(new FileInputStream("./config/config.ini"));
		userEmail = props.getProperty("useremail");
		wordLang = props.getProperty("wordlang");
		transLang = props.getProperty("translang");
		period = getPeriod();
		translang = getTransLang();
		chkeyboard = getAutoChangeKeyLang();
		updatetext = getUpdate();
	}

	public void update() throws Exception {
		attempt = 0;
		url = host + "/api/get-word/" + userEmail + "/" + wordLang + "/" + transLang;
		response = Fetch.get(url);
		json = (JSONObject) parser.parse(response);
		word = (String) json.get("word");
		trans = (String) json.get("trans");
		hint = (String) json.get("hint");
		if (chkeyboard)
			Runtime.getRuntime().exec("setxkbmap ua");
	}

	public int checkTranslate(JTextField translate, JDialog window) throws Exception {
		if (trans.equals(translate.getText())) {
			url = host + "/api/update-status/success/" + word + "/" + userEmail;
			Fetch.get(url);
			window.setVisible(false);
			if (chkeyboard)
				Runtime.getRuntime().exec("setxkbmap us");
		} else {
			url = host + "/api/update-status/fails/" + word + "/" + userEmail;
			Fetch.get(url);
		}
		translate.setText(null);
		return ++attempt;
	}

	private int getPeriod() throws Exception {
		url = host + "/api/get-period/" + userEmail;
		response = Fetch.get(url);
		return Integer.valueOf(response) * 60000;
	}

	private String getUpdate() throws Exception {
		url = host + "/api/get-update";
		response = Fetch.get(url);
		return response;
	}

	private String getTransLang() throws Exception {
		url = host + "/api/get-translang/" + userEmail;
		response = Fetch.get(url);
		return response;
	}

	private boolean getAutoChangeKeyLang() throws Exception {
		url = host + "/api/get-autochangekeylang/" + userEmail;
		response = Fetch.get(url);
		return Integer.valueOf(response) == 1;
	}
}

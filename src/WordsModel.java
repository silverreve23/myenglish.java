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
    public Integer attempt;
    public Integer period;
    public Boolean autoChangeKeyLang;
    private String responseWord;
    private String responseAutoChangeKeyLang;
    private String responsePeriod;
    private String responseTransLang;
    private String host;
    private String userEmail;
    private String wordLang;
    private String transLang;
    private String logfile;
    private URL url;
    private URL urlWord;
    private URL urlPeriod;
    private URL urlTransLang;
    private URL urlAutoChangeKeyLang;
    private JSONParser parser;
    private BufferedReader reader;
    private InputStream stream;
    private JSONObject json;
    private BufferedWriter writer;
    private Logger logger; 
    
    public WordsModel(){
    	try{
			userEmail = "";
			wordLang = "";
			transLang = "";
			responseWord = "";
			responsePeriod = "";
			responseTransLang = "";
			responseAutoChangeKeyLang = "";
			host = "http://35.182.114.21";
			host = "http://myenglish.io";
			logfile = "/var/log/myenglish.log";
    		Properties props = new Properties();
    		props.load(new FileInputStream("./config/config.ini"));
    		userEmail = props.getProperty("useremail");
    		wordLang = props.getProperty("wordlang");
    		transLang = props.getProperty("translang");
    		urlWord = new URL(host+"/api/get-word/"+userEmail+"/"+wordLang+"/"+transLang);
    		urlPeriod = new URL(host+"/api/get-period/"+userEmail);
    		urlTransLang = new URL(host+"/api/get-translang/"+userEmail);
    		urlAutoChangeKeyLang = new URL(host+"/api/get-autochangekeylang/"+userEmail);
    		period = getPeriod();
    		translang = getTransLang();
    		autoChangeKeyLang = getAutoChangeKeyLang();
    		parser = new JSONParser();
    		logger = new Logger(logfile);
    	}catch(Exception e){
			logger.log(
				"exception in WordsModel constructor(logging)",
				e.getMessage()
			);
    	}
    }
    
    public void update(){
		try{
			attempt = 0;
			responseWord = "";
			stream = urlWord.openStream();
			reader = new BufferedReader(
    			new InputStreamReader(stream, "UTF-8")
    		);
    	    for(String line; (line = reader.readLine()) != null;){
    	    	responseWord += line;
    	    }
    	    stream.close();
    	    json = (JSONObject) parser.parse(responseWord);
    	    word = (String) json.get("word");
    	    trans = (String) json.get("trans");
    	    hint = (String) json.get("hint");
    	    if(autoChangeKeyLang) Runtime.getRuntime().exec("setxkbmap ua");
		}catch(Exception e){
			logger.log(
				"exception in WordsModel update method(logging)",
				e.getMessage()
			);
    	}
    	System.out.println("Data get-word: " + responseWord);
    	System.out.println("update method runed");
	}

    public int checkTranslate(JTextField translate, JDialog window){
		try{
			if(trans.equals(translate.getText())){
				url = new URL(host+"/api/update-status/success/"+word+"/"+userEmail);
		    	stream = url.openStream();
		    	stream.close();
				window.setVisible(false);
				if(autoChangeKeyLang) Runtime.getRuntime().exec("setxkbmap us");
			}else{
				url = new URL(host+"/api/update-status/fails/"+word+"/"+userEmail);
				stream = url.openStream();
				stream.close();
			}
		}catch(Exception e){
			logger.log(
				"exception in WordsModel checkTranslate method",
				e.getMessage()
			);
			System.exit(0);
        }
		translate.setText(null);
        return ++attempt;
    }
    
    private int getPeriod(){
		try{
			stream = urlPeriod.openStream();
			reader = new BufferedReader(
    			new InputStreamReader(stream, "UTF-8")
    		);
    	    for(String line; (line = reader.readLine()) != null;){
    	    	responsePeriod += line;
    	    }
    	    stream.close();
		}catch(Exception e){
			logger.log(
				"exception in WordsModel getPeriod method(logging)",
				e.getMessage()
			);
    	}
    	System.out.println("getPeriod: " + responsePeriod);
    	return Integer.valueOf(responsePeriod) * 60000;
	}
    
    private String getTransLang(){
		try{
			stream = urlTransLang.openStream();
			reader = new BufferedReader(
    			new InputStreamReader(stream, "UTF-8")
    		);
    	    for(String line; (line = reader.readLine()) != null;){
    	    	responseTransLang += line;
    	    }
    	    stream.close();
		}catch(Exception e){
			logger.log(
				"exception in WordsModel getTransLang method(logging)",
				e.getMessage()
			);
    	}
    	System.out.println("getTransLang: " + responseTransLang);
    	return responseTransLang;
	}
    
    private boolean getAutoChangeKeyLang(){
		try{
			stream = urlAutoChangeKeyLang.openStream();
			reader = new BufferedReader(
    			new InputStreamReader(stream, "UTF-8")
    		);
    	    for(String line; (line = reader.readLine()) != null;){
    	    	responseAutoChangeKeyLang += line;
    	    }
    	    stream.close();
		}catch(Exception e){
			logger.log(
				"exception in WordsModel getAutoChangeKeyLang method(logging)",
				e.getMessage()
			);
    	}
    	System.out.println("getAutoChangeKeyLang: " + responseAutoChangeKeyLang);
    	return Integer.valueOf(responseAutoChangeKeyLang) == 1;
	}
}

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
    public Integer attempt;
    public Integer period;
    private String responseWord;
    private String responsePeriod;
    private String host;
    private String user;
    private String logfile;
    private URL urlWord;
    private URL urlPeriod;
    private JSONParser parser;
    private BufferedReader reader;
    private InputStream stream;
    private JSONObject json;
    
    public WordsModel(){
    	try{
			user = "";
			responseWord = "";
			responsePeriod = "";
			attempt = 0;
			host = "http://35.182.114.21";
			logfile = "/var/log/myenglish.log";
    		Properties props = new Properties();
    		props.load(new FileInputStream("./config/config.ini"));
    		user = props.getProperty("user");
    		urlWord = new URL(host+"/api/get-word/"+user);
    		urlPeriod = new URL(host+"/api/get-period/"+user);
    		period = getPeriod();
    		parser = new JSONParser();
    	}catch(Exception e){
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(logfile, true));
				writer.append("\n");
				writer.append(e.getMessage());
				writer.close();
			}catch(IOException ef){
				System.out.println("exception in WordsModel constructor(logging)");
				System.out.println(ef.getMessage());
			}
    		System.out.println("exception in WordsModel constructor");
    		System.out.println(e.getMessage());
    	}
    }
    
    public void update(){
		try{
			responseWord = "";
			stream = urlWord.openStream();
			reader = new BufferedReader(
    			new InputStreamReader(urlWord.openStream(), "UTF-8")
    		);
    		stream.close();
    	    for(String line; (line = reader.readLine()) != null;){
    	    	responseWord += line;
    	    }
    	    json = (JSONObject) parser.parse(responseWord);
    	    word = (String) json.get("word");
    	    trans = (String) json.get("trans");
		}catch(Exception e){
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(logfile, true));
				writer.append("\n");
				writer.append(e.getMessage());
				writer.close();
			}catch(IOException ef){
				System.out.println("exception in WordsModel update method(logging)");
				System.out.println(ef.getMessage());
			}
			System.out.println("exception in WordsModel update method");
    		System.out.println(e.getMessage());
    	}
	}

    public int checkTranslate(JTextField translate, JDialog window){
		try{
			if(trans.equals(translate.getText())){
				URL url = new URL(host+"/api/update-status/success/"+word+"/"+user);
		    	stream = url.openStream();
		    	stream.close();
				window.setVisible(false);
			}
			URL url = new URL(host+"/api/update-status/fails/"+word+"/"+user);
	    	stream = url.openStream();
		    stream.close();
		}catch(Exception e){
			System.out.println("exception in WordsModel checkTranslate method");
            System.out.println(e.getMessage());
			System.exit(0);
        }

		translate.setText(null);
        return ++attempt;
    }
    
    private int getPeriod(){
		try{
			BufferedReader reader = new BufferedReader(
    			new InputStreamReader(urlPeriod.openStream(), "UTF-8")
    		);
    	    for(String line; (line = reader.readLine()) != null;){
    	    	responsePeriod += line;
    	    }
		}catch(Exception e){
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(logfile, true));
				writer.append("\n");
				writer.append(e.getMessage());
				writer.close();
			}catch(IOException ef){
				System.out.println("exception in WordsModel getPeriod method(logging)");
				System.out.println(ef.getMessage());
			}
			System.out.println("exception in WordsModel getPeriod method");
    		System.out.println(e.getMessage());
    	}
    	return Integer.valueOf(responsePeriod) * 60000;
	}
}

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
    public String response;
    private String host;
    private String user;
    private String logfile;
    private URL url;
    
    public WordsModel(){
    	try{
			user = "";
			response = "";
			attempt = 0;
			host = "http://35.182.114.21";
			logfile = "/var/log/myenglish.log";
    		Properties props = new Properties();
    		props.load(new FileInputStream("./config/config.ini"));
    		user = props.getProperty("user");
    		url = new URL(host+"/api/get-word/"+user);
    	}catch(Exception e){
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(logfile, true));
				writer.append("\n");
				writer.append(e.getMessage());
				writer.close();
			}catch(IOException ef){
				System.out.println(ef.getMessage());
			}
    		System.out.println(e.getMessage());
    	}
    }
    
    public void update(){
		try{
			BufferedReader reader = new BufferedReader(
    			new InputStreamReader(url.openStream(), "UTF-8")
    		);
    	    for(String line; (line = reader.readLine()) != null;){
    	    	response += line;
    	    }
    	    JSONParser parser = new JSONParser();
    	    JSONObject json = (JSONObject) parser.parse(response);
    	    word = (String) json.get("word");
    	    trans = (String) json.get("trans");
		}catch(Exception e){
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(logfile, true));
				writer.append("\n");
				writer.append(e.getMessage());
				writer.close();
			}catch(IOException ef){
				System.out.println(ef.getMessage());
			}
    		System.out.println(e.getMessage());
    	}
	}

    public int checkTranslate(JTextField translate){
		try{
			if(trans.equals(translate.getText())){
				URL url = new URL(host+"/api/update-status/success/"+word+"/"+user);
		    	url.openStream();
				System.exit(0);
			}
			URL url = new URL(host+"/api/update-status/fails/"+word+"/"+user);
	    	url.openStream();
		}catch(Exception e){
            System.out.println(e.getMessage());
			System.exit(0);
        }

		translate.setText(null);
        return ++attempt;
    }
}

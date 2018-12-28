import java.util.*;
import java.io.*;

class Logger {
	public String logFile;
	private BufferedWriter writer;
	public Logger(String lFile){
		logFile = lFile;
	}
	public void log(String text, String data){
		try {
			writer = new BufferedWriter(new FileWriter(logFile, true));
			writer.append("\n");
			writer.append(data);
			writer.close();
		}catch(IOException e){
			System.out.println(text);
			System.out.println(e.getMessage());
		}
	}
}

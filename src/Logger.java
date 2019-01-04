import java.util.*;
import java.io.*;

class Logger {
	private static BufferedWriter writer;
	public static void log(String data){
		try{
			writer = new BufferedWriter(new FileWriter(
				"/var/log/myenglish.log", 
				true
			));
			writer.append("\n" + data);
			writer.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}

import java.net.URL;
import java.io.*;

class Fetch {
	private static URL url;
	private static InputStream stream;
	private static BufferedReader reader;
	private static String response;
	public static String get(String host) throws Exception{
		response = ""; 
		url = new URL(host);
		stream = url.openStream();
		reader = new BufferedReader(
			new InputStreamReader(stream, "UTF-8")
		);
		for(String line; (line = reader.readLine()) != null;){
			response += line;
		}
		stream.close();
		return response;
	}
}

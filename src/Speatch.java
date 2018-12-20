import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

class Speatch {
	public void play(){
		try {
			URL url = new URL("http://35.182.114.21/sounds/mouseclick.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			 e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		} catch (LineUnavailableException e) {
			 e.printStackTrace();
		}
	}
}

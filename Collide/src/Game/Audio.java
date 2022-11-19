package Game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;


public class Audio {
	private Clip clip;
	public Audio (String musicLocation) {
	try {
		File musicPath= new File(musicLocation);
		if(musicPath.exists()) {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			 clip = AudioSystem.getClip();
			 clip.open(audioInput);	 
		}
	}
	catch(Exception ex) {
		ex.printStackTrace();
		}
	}
	public void startMusic() {
		clip.start();
	}
	public void loopMusic() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stopMusic() {
		clip.stop();
	}
	
	
}
	


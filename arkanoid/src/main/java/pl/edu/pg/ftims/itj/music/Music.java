package pl.edu.pg.ftims.itj.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {

	// implementacja muzyki w grze
	public void music() throws Exception {

		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(this.getClass().getResource("Nocna_wedrowka_drzew.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(2);
		} catch (Exception ex) {
		}

	}

}

package game;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Une classe qui permet de gerer la musique dans le programme.
 * 
 * @author roman_tyzio
 * @version 1.0.0
 */
public class Music {
	/**
	 * Une instance de la classe Clip.
	 */
	static Clip clip;
	/**
	 * Le volume de musique actuel.
	 */
	static float currentVolume;
	/**
	 * L'instance de la classe FloatControl.
	 */
	static FloatControl control;
	/**
	 * Le moment ou le joueur arrete la musique.
	 */
	static long pauseTime;

	/**
	 * Le nom du fichier musique.
	 */

	private String songName;

	/**
	 * Constructeur de la classe Music.
	 * 
	 * @param s Le nom du fichier musique.
	 */
	public Music(String s) {
		this.songName = s;
	}

	/**
	 * Une methode qui permet de lancer la musique au chargement de la JFrame.
	 */
	public void playMusic() {
		URL musicUrl = getClass().getResource("sounds/" + this.songName + ".wav");
		// Initialisation de Audio Input
		try {
			Music.clip = AudioSystem.getClip();
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicUrl);
			Music.clip.open(audioInput);
			Music.clip.loop(Clip.LOOP_CONTINUOUSLY);
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			if (this.songName == "IntroMusic" || this.songName == "EndMusic" || this.songName == "FailMusic") {
				control.setValue(0); // Pour que le volume d'introduction soit toujous le meme;
			} else {
				control.setValue(SoundMenu.loadMusicOptions());
			}

		} catch (LineUnavailableException e) {

			// Lien indisponible
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// Le format non reconnu
			e.printStackTrace();
		} catch (IOException e) {
			// Exception
			e.printStackTrace();
		}
	}

	/**
	 * Une methode qui permet d'arreter la musique a la fermeture de la JFrame;
	 */
	public void stopMusic() {
		Music.clip.close();

	}

	/**
	 * Une methode qui permet de reprendre la musique au moment quand elle etait .
	 */
	public static void continueMusic() {
		clip.setMicrosecondPosition(pauseTime);
		clip.start();
	}

	/**
	 * Une methode qui permet de mettre la musique en pause.
	 */
	public static void pauseMusic() {
		pauseTime = clip.getMicrosecondPosition();
		clip.stop();
	}

	/**
	 * Une methode qui permet de reprendre la musique a partir de moment de la
	 * pause.( pour le volume 0% );
	 */
	public static void specialContinueMusic() {
		Music.clip.setMicrosecondPosition(Music.clip.getMicrosecondPosition());
		Music.clip.start();
	}

	/**
	 * Une methode qui donne le chemin vers le fichier musique.
	 */
	public String toString() {
		return "sounds/" + this.songName + ".sav";

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Music))
			return false;

		Music other = (Music) obj;
		return this.songName == other.songName;

	}

}
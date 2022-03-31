package game;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	private static Clip clip; // Pour le son
	static float currentVolume; // Volume de la musique actuel.
	static FloatControl control;
	static long pauseTime; // Le moment quand l'utilisateur a arreté la musique.
	private String songName; // Nom du fichier.

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
			if (this.songName == "IntroMusic") {
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
	 * Une methode qui permet d'arreter la musique à la fermeture de la JFrame;
	 */
	public void stopMusic() {
		Music.clip.close();

	}

	/**
	 * Une methode qui permet de reprendre la musique au meme moment.
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
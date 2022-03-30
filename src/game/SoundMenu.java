package game;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.OverlayLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SoundMenu implements ActionListener, ChangeListener, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	static JFrame soundFrame = new JFrame("Music Options");
	private JButton btnPlay = new JButton("Play");
	private JButton btnPause = new JButton("Pause");
	static int savedVolume = 0; // Le volume defini par l'utilisateur.
	private JSlider volumeSlider = new JSlider(-20, 6, savedVolume);
	private Hashtable<Integer, JLabel> soundLabels = new Hashtable<>(); // Hashtable pour les labels de JSlider.

	public SoundMenu(int sV) {
		SoundMenu.savedVolume = sV;

		// Image du fond du menu
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("images/secondary_menu.png")));
		soundFrame.add(background);
		background.setLayout(new OverlayLayout(background));

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		soundFrame.setIconImage(icon.getImage());

		// Les etiquettes pour le slider.
		soundLabels.put(-20, new JLabel("0%"));
		soundLabels.put(6, new JLabel("100%"));
		volumeSlider.setLabelTable(soundLabels);

		// Parametrage de Layout
		background.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;

		// Change la taille des elements
		c.ipadx = 30;
		c.ipady = 1;

		c.gridy = 1; // Change la position dans le layout
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 1;
		background.add(volumeSlider, c);

		c.gridy = 2;
		c.weighty = 0.5;
		background.add(btnPlay, c);

		c.gridy = 3;
		c.weighty = 1;
		background.add(btnPause, c);

		// Ajout des listeners sur les boutons.
		btnPlay.addActionListener(this);
		btnPause.addActionListener(this);
		volumeSlider.addChangeListener(this);

		// Ajout de listener qui ecoute à la fermeture.
		soundFrame.addWindowListener(exitListener);

		// Parametrage de la JFrame
		volumeSlider.setOpaque(false);
		volumeSlider.setPaintLabels(true);
		soundFrame.setSize(new Dimension(300, 150));
		soundFrame.setResizable(false);
		soundFrame.setLocationRelativeTo(null);
		soundFrame.setVisible(true);

	}

	/**
	 * Une methode qui permet de sauvegarder le volume de musique choisi par
	 * l'utilisateur.
	 */
	static void saveMusicOptions() {
		try {
			File savedFile = new File("saves/useroptions/soundoptions.sav");
			FileOutputStream fos = new FileOutputStream(savedFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(savedVolume);
			oos.flush();
			oos.close();
			System.out.println("User Options Saved");

		} catch (Exception e) {
			System.out.println("Serialisation error!\n" + e.getClass() + ": " + e.getMessage() + "\n");

		}

	}

	/**
	 * Une methode qui permet de reutiliser le volume de musique difini par
	 * l'utilisateur.
	 * 
	 * @return Le volume defini par l'utilisateur.
	 */
	static int loadMusicOptions() {
		try {
			File loadedFile = new File("saves/useroptions/soundoptions.sav");
			FileInputStream fis = new FileInputStream(loadedFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			savedVolume = (int) ois.readObject();
			ois.close();
			System.out.println("User Options Loaded");
		} catch (Exception e) {
			Music.control.setValue(0);// En cas de l'absence de la sauvegarde on met le volume par defaut.
			System.out.println("Serialization error! Cant load data. \n ");
			System.out.println(e.getClass() + e.getMessage() + "\n");
		}
		return savedVolume;
	}

	WindowListener exitListener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			soundFrame.dispose();
			SoundMenu.saveMusicOptions();
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPlay) {
			Music.continueMusic();
		}
		if (e.getSource() == btnPause) {
			Music.pauseMusic();
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Music.currentVolume = volumeSlider.getValue();
		System.out.println(Music.currentVolume);
		if (Music.currentVolume == -20.0f) {
			Music.currentVolume = -80.0f;
			Music.control.setValue(Music.currentVolume);
			savedVolume = -20;
		} else {
			Music.control.setValue(Music.currentVolume);
			savedVolume = (int) Music.currentVolume;
		}
	}
}

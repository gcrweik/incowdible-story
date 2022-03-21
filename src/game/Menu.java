package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import utilclass.RoundedBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

public class Menu implements ActionListener {

	static JFrame menuFrames = new JFrame("Menu");
	JButton btnStart = new JButton("New Game");
	JButton btnLoad = new JButton("Load");
	JButton btnControls = new JButton("Controls");
	JButton btnExit = new JButton("Exit");
	static Clip clip; // Pour le son

	public Menu() {

		URL musicUrl = getClass().getResource("sounds/MenuMusic.wav");

		// Initialisation de Audio Input
		try {
			clip = AudioSystem.getClip();
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicUrl);
			clip.open(audioInput);
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

		// Image du fond du menu
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("images/menu_background.gif")));
		menuFrames.add(background);
		background.setLayout(new OverlayLayout(background));

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		menuFrames.setIconImage(icon.getImage());

		// Parametrage de Layout
		background.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Titre du menu
		background.add(new JLabel("<html><h1><strong><i>The Incowdible Story</i></strong></h1><hr></html>"), c);

		// Parametrage des boutons ronds
		btnStart.setBorder(new RoundedBorder(10));
		btnLoad.setBorder(new RoundedBorder(10));
		btnControls.setBorder(new RoundedBorder(10));
		btnExit.setBorder(new RoundedBorder(10));

		c.gridx = 0;
		c.gridy = 0;

		// Change la taille des elements
		c.ipadx = 30;
		c.ipady = 1;

		c.gridy = 1; // Change la position dans le layout
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 1;
		background.add(btnStart, c);

		c.gridy = 2;
		c.weighty = 0.5;
		background.add(btnLoad, c);

		c.gridy = 3;
		c.weighty = 1;
		background.add(btnControls, c);

		c.gridy = 4;
		c.weighty = 0.5;
		background.add(btnExit, c);

		// Listener pour les boutons
		btnStart.addActionListener(this);
		btnLoad.addActionListener(this);
		btnControls.addActionListener(this);
		btnExit.addActionListener(this);

		// Desactive le focus sur les buttons
		btnStart.setFocusable(false);
		btnLoad.setFocusable(false);
		btnControls.setFocusable(false);
		btnExit.setFocusable(false);

		// Parametrage de la JFrame
		clip.loop(Clip.LOOP_CONTINUOUSLY); // Commence de jouer le son en boucle
		menuFrames.setSize(new Dimension(750, 410));
		menuFrames.setResizable(false);
		menuFrames.setLocationRelativeTo(null);
		menuFrames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrames.setVisible(true);
	}

	public static void disposeMenuFrame() {
		menuFrames.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			menuFrames.dispose();
			clip.close();// Arrete le son
			Game jeu = new Game();
			GUI gui = new GUI(jeu);
			jeu.setGUI(gui);
		}
		if (e.getSource() == btnLoad) {
			@SuppressWarnings("unused")
			LoadMenu load = new LoadMenu();

		}
		if (e.getSource() == btnControls) {
			// A développer
		}
		if (e.getSource() == btnExit) {
			System.exit(0);
		}

	}

}

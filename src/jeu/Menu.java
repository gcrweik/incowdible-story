package jeu;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import util_class.RoundedBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

public class Menu implements ActionListener {

	JFrame menu_frame = new JFrame("Menu");
	JButton btn_start = new JButton("New Game");
	JButton btn_load = new JButton("Load");
	JButton btn_controls = new JButton("Controls");
	JButton btn_exit = new JButton("Exit");
	Clip clip; // Pour le son

	public Menu() {

		URL music_url = getClass().getResource("sounds/MenuMusic.wav");

		// Initialisation de Audio Input
		try {
			clip = AudioSystem.getClip();
			AudioInputStream audio_input = AudioSystem.getAudioInputStream(music_url);
			clip.open(audio_input);
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
		menu_frame.add(background);
		background.setLayout(new OverlayLayout(background));

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		menu_frame.setIconImage(icon.getImage());

		// Parametrage de Layout
		background.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Titre du menu
		background.add(new JLabel("<html><h1><strong><i>The Incowdible Story</i></strong></h1><hr></html>"), c);

		// Parametrage des boutons ronds
		btn_start.setBorder(new RoundedBorder(10));
		btn_load.setBorder(new RoundedBorder(10));
		btn_controls.setBorder(new RoundedBorder(10));
		btn_exit.setBorder(new RoundedBorder(10));

		c.gridx = 0;
		c.gridy = 0;

		// Change la taille des elements
		c.ipadx = 30;
		c.ipady = 1;

		c.gridy = 1; // Change la position dans le layout
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 1;
		background.add(btn_start, c);

		c.gridy = 2;
		c.weighty = 0.5;
		background.add(btn_load, c);

		c.gridy = 3;
		c.weighty = 1;
		background.add(btn_controls, c);

		c.gridy = 4;
		c.weighty = 0.5;
		background.add(btn_exit, c);

		// Listener pour les boutons
		btn_start.addActionListener(this);
		btn_load.addActionListener(this);
		btn_controls.addActionListener(this);
		btn_exit.addActionListener(this);

		// Desactive le focus sur les buttons
		btn_start.setFocusable(false);
		btn_load.setFocusable(false);
		btn_controls.setFocusable(false);
		btn_exit.setFocusable(false);

		// Parametrage de la JFrame
		clip.loop(Clip.LOOP_CONTINUOUSLY); // Commence de jouer le son en boucle
		menu_frame.setSize(new Dimension(750, 410));
		menu_frame.setResizable(false);
		menu_frame.setLocationRelativeTo(null);
		menu_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu_frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_start) {
			menu_frame.dispose();
			clip.close();// Arrete le son
			Jeu jeu = new Jeu();
			GUI gui = new GUI(jeu);
			jeu.setGUI(gui);
		}
		if (e.getSource() == btn_load) {
			menu_frame.dispose();
			clip.close(); // Arrete le son
			Jeu jeu_loaded = GUI.loadGame(); // Charge une partie à partir de la sauvegarde
			GUI gui = new GUI(jeu_loaded);
			jeu_loaded.setGUI(gui);

		}
		if (e.getSource() == btn_controls) {
			// A développer
		}
		if (e.getSource() == btn_exit) {
			System.exit(0);
		}

	}

}

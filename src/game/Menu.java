package game;

import javax.swing.*;

import utilclass.RoundedBorder;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * Une classe qui permet de creer le menu principal du jeu.
 * 
 * @author roman_tyzio
 * @version 1.0.0
 *
 */
public class Menu implements ActionListener {

	/**
	 * JFrame de la classe Menu.
	 */
	static JFrame menuFrame = new JFrame("Menu");
	/**
	 * Un bouton qui permet de lancer une nouvelle partie.
	 */
	private JButton btnStart = new JButton("New Game");
	/**
	 * Un bouton qui permet de lancer une sauvegarde.
	 */
	private JButton btnLoad = new JButton("Load");
	/**
	 * Un bouton qui permet de lancer le menu de reglage sonore.
	 */
	private JButton btnSound = new JButton("Sound");
	/**
	 * Un bouton qui permet de lancer une fenetre d'affichage des commandes.
	 */
	private JButton btnControls = new JButton("Controls");
	/**
	 * Un bouton qui permet fermer le jeu.
	 */
	private JButton btnExit = new JButton("Exit");
	/**
	 * La musique du menu principale.
	 */
	static Music musicMenu = new Music("MenuMusic");

	/**
	 * Un constructeur de la classe Menu.
	 */
	public Menu() {

		// Image du fond du menu
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("images/menu_background.gif")));
		menuFrame.add(background);
		background.setLayout(new OverlayLayout(background));

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		menuFrame.setIconImage(icon.getImage());

		// Parametrage de Layout
		background.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Titre du menu
		background.add(new JLabel("<html><h1><strong><i>The Incowdible Story</i></strong></h1><hr></html>"), c);

		// Parametrage des boutons ronds
		btnStart.setBorder(new RoundedBorder(10));
		btnLoad.setBorder(new RoundedBorder(10));
		btnSound.setBorder(new RoundedBorder(10));
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
		background.add(btnSound, c);

		c.gridy = 4;
		c.weighty = 0.5;
		background.add(btnControls, c);

		c.gridy = 5;
		c.weighty = 1;
		background.add(btnExit, c);

		// Listener pour les boutons
		btnStart.addActionListener(this);
		btnLoad.addActionListener(this);
		btnSound.addActionListener(this);
		btnControls.addActionListener(this);
		btnExit.addActionListener(this);

		// Desactive le focus sur les buttons
		btnStart.setFocusable(false);
		btnLoad.setFocusable(false);
		btnSound.setFocusable(false);
		btnControls.setFocusable(false);
		btnExit.setFocusable(false);

		// Parametrage de la JFrame
		musicMenu.playMusic();// Commence de jouer le son en boucle
		menuFrame.setSize(new Dimension(750, 410));
		menuFrame.setResizable(false);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setVisible(true);
	}

	/**
	 * Une methode qui permet de fermer la fenetre du menu principal.
	 */
	public static void disposeMenuFrame() {
		menuFrame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			menuFrame.dispose();
			musicMenu.stopMusic();// Arrete le son
			Game jeu = new Game();
			GUI gui = new GUI(jeu);
			jeu.setGUI(gui);
		}
		if (e.getSource() == btnLoad) {
			@SuppressWarnings("unused")
			LoadMenu load = new LoadMenu();

		}
		if (e.getSource() == btnSound) {
			if (SoundMenu.soundFrame == null || SoundMenu.soundFrame.getParent() == null) {
				int loadedoptions = SoundMenu.loadMusicOptions(); // Charge une partie a partir de la sauvegarde
				@SuppressWarnings("unused")
				SoundMenu sound = new SoundMenu(loadedoptions);
			} else {
				SoundMenu.soundFrame.toFront();
				SoundMenu.soundFrame.repaint();
			}

		}
		if (e.getSource() == btnControls) {
			if (ControlsMenu.controlsFrame == null || ControlsMenu.controlsFrame.getParent() == null) {
				@SuppressWarnings("unused")
				ControlsMenu controls = new ControlsMenu();
			} else {
				ControlsMenu.controlsFrame.toFront();
				ControlsMenu.controlsFrame.repaint();

			}
		}
		if (e.getSource() == btnExit) {
			System.exit(0);
		}

	}

	/**
	 * Un getter pour la musique du menu principal.
	 * 
	 * @return La musique du menu principal.
	 */
	public Music getMusic() {
		return Menu.musicMenu;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Menu))
			return false;

		Menu other = (Menu) obj;

		return Menu.musicMenu.equals(other.getMusic());
	}

}

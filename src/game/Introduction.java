package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Une classe qui permet d'afficher la "cinematique" au debut du jeu.
 * 
 * @author roman_tyzio
 * @version 1.0.0
 */
public class Introduction implements KeyListener {
	/**
	 * JFrame de la classe Introduction.
	 */
	private JFrame introFrame = new JFrame("The Incowdible Story");
	/**
	 * JPanel de la classe Game.
	 */
	private JPanel introPanel = new JPanel();
	/**
	 * Un Layout qui permet de passer d'une image a l'autre, on les defini dans le
	 * constructeur.
	 */
	private CardLayout card = new CardLayout();
	/**
	 * La musique d'introduction.
	 */
	private Music musicIntro = new Music("IntroMusic");
	/**
	 * Un compteur qui permet de passer d'une scene a l'autre.
	 */
	private int counter = 0;

	/**
	 * Un constructeur de la classe Introduction qui permet d'afficher les images,
	 * ainsi que de passer d'une image a l'autre.
	 */
	public Introduction() {

		// Creation de label des scenes
		JLabel scene1 = new JLabel(new ImageIcon(getClass().getResource("images/Scene1_Old.gif")));
		JLabel scene2 = new JLabel(new ImageIcon(getClass().getResource("images/Scene2_Old.gif")));
		JLabel scene3 = new JLabel(new ImageIcon(getClass().getResource("images/Scene3_Old.gif")));
		JLabel scene4 = new JLabel(new ImageIcon(getClass().getResource("images/Scene4_Old.gif")));
		JLabel scene5 = new JLabel(new ImageIcon(getClass().getResource("images/Scene5_NonInf_Old.gif")));

		// Layout qui permet de mettre les labels un sur l'autre
		introPanel.setLayout(card);

		// Ajout de scenes dans le JPanel
		introPanel.add(scene1);
		introPanel.add(scene2);
		introPanel.add(scene3);
		introPanel.add(scene4);
		introPanel.add(scene5);

		// Listener su JPanel pour
		introPanel.addKeyListener(this);
		introPanel.setFocusable(true);

		// Ajout de JPanel dans la JFrame
		introFrame.getContentPane().add(introPanel, BorderLayout.CENTER);

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		introFrame.setIconImage(icon.getImage());

		// Reglage de la JFrame
		musicIntro.playMusic();// Commence de jouer le son en boucle
		introFrame.setSize(new Dimension(960, 540));
		introFrame.setResizable(false);
		introFrame.setLocationRelativeTo(null);
		introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		introFrame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// La methode pas utilisee mais n'est pas a supprimer !

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (counter < 4) {
				card.next(introPanel);
				counter += 1;
			} else {
				introFrame.dispose(); // Fermer la JFrame
				musicIntro.stopMusic(); // Arrete le son
				@SuppressWarnings("unused")
				Menu menu = new Menu();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// La methode pas utilisee mais n'est pas a supprimer !

	}

	/**
	 * Un getter de musique de l'introduction.
	 * 
	 * @return La musique d'introduction.
	 */
	public Music getMusic() {
		return this.musicIntro;

	}

	/**
	 * Un getter du compteur de scenes.
	 * 
	 * @return Le "numero" de la scene.
	 */
	public int getCounter() {
		return this.counter;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Introduction))
			return false;

		Introduction other = (Introduction) obj;

		return this.counter == other.counter && this.musicIntro.equals(other.musicIntro);
	}

}

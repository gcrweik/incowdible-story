package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Introduction implements KeyListener {

	JFrame introFrame = new JFrame("The Incowdible Story");
	JPanel introPanel = new JPanel();
	CardLayout card = new CardLayout();
	Clip clip; // Pour le son
	int counter = 0; // Counter de scenes

	public Introduction() {

		URL musicUrl = getClass().getResource("sounds/IntroMusic.wav");

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
		clip.loop(Clip.LOOP_CONTINUOUSLY); // Commence de jouer le son en boucle
		introFrame.setSize(new Dimension(960, 540));
		introFrame.setResizable(false);
		introFrame.setLocationRelativeTo(null);
		introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		introFrame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (counter < 4) {
				card.next(introPanel);
				counter += 1;
			} else {
				introFrame.dispose(); // Fermer la JFrame
				clip.close(); // Arrete le son
				@SuppressWarnings("unused")
				Menu menu = new Menu();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

}

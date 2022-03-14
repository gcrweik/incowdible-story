package jeu;

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

public class Intro implements KeyListener {

	JFrame intro_frame = new JFrame("The Incowdible Story");
	JPanel intro_panel = new JPanel();
	CardLayout card = new CardLayout();
	Clip clip; // Pour le son
	int counter = 0; // Counter de scenes

	public Intro() {

		URL music_url = getClass().getResource("sounds/IntroMusic.wav");

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

		// Creation de label des scenes
		JLabel scene1 = new JLabel(new ImageIcon(getClass().getResource("images/Scene1_Old.gif")));
		JLabel scene2 = new JLabel(new ImageIcon(getClass().getResource("images/Scene2_Old.gif")));
		JLabel scene3 = new JLabel(new ImageIcon(getClass().getResource("images/Scene3_Old.gif")));
		JLabel scene4 = new JLabel(new ImageIcon(getClass().getResource("images/Scene4_Old.gif")));
		JLabel scene5 = new JLabel(new ImageIcon(getClass().getResource("images/Scene5_NonInf_Old.gif")));

		// Layout qui permet de mettre les labels un sur l'autre
		intro_panel.setLayout(card);

		// Ajout de scenes dans le JPanel
		intro_panel.add(scene1);
		intro_panel.add(scene2);
		intro_panel.add(scene3);
		intro_panel.add(scene4);
		intro_panel.add(scene5);

		// Listener su JPanel pour
		intro_panel.addKeyListener(this);
		intro_panel.setFocusable(true);

		// Ajout de JPanel dans la JFrame
		intro_frame.getContentPane().add(intro_panel, BorderLayout.CENTER);

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		intro_frame.setIconImage(icon.getImage());

		// Reglage de la JFrame
		clip.loop(Clip.LOOP_CONTINUOUSLY); // Commence de jouer le son en boucle
		intro_frame.setSize(new Dimension(960, 540));
		intro_frame.setResizable(false);
		intro_frame.setLocationRelativeTo(null);
		intro_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		intro_frame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (counter < 4) {
				card.next(intro_panel);
				counter += 1;
			} else {
				intro_frame.dispose(); // Fermer la JFrame
				clip.close(); // Arrete le son
				Menu menu = new Menu();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

}

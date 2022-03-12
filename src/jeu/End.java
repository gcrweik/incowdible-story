package jeu;

import java.awt.BorderLayout;
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

public class End implements KeyListener {

	JFrame end_frame = new JFrame("The Incowdible Story");
	JPanel end_panel = new JPanel();
	Clip clip; // Pour le son

	public End() {

		URL music_url = getClass().getResource("sounds/EndMusic.wav");

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

		// Creation de la scene
		JLabel end_scene = new JLabel(new ImageIcon(getClass().getResource("images/Scene_End.gif")));

		// Ajout de scenes dans le JPanel
		end_panel.add(end_scene);

		// Listener su JPanel pour
		end_panel.addKeyListener(this);
		end_panel.setFocusable(true);

		// Ajout de JPanel dans la JFrame
		end_frame.getContentPane().add(end_panel, BorderLayout.CENTER);

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		end_frame.setIconImage(icon.getImage());

		// Reglage de la JFrame
		clip.loop(Clip.LOOP_CONTINUOUSLY); // Commence de jouer le son en boucle
		end_frame.setSize(new Dimension(750, 600));
		end_frame.setResizable(false);
		end_frame.setLocationRelativeTo(null);
		end_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		end_frame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			end_frame.dispose(); // Fermer la JFrame
			clip.close(); // Arrete le son
			Menu menu = new Menu();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

}

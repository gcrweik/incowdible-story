package game;

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

	JFrame endFrame = new JFrame("The Incowdible Story");
	JPanel endPanel = new JPanel();
	Clip clip; // Pour le son

	public End() {

		URL musicURL = getClass().getResource("sounds/EndMusic.wav");

		// Initialisation de Audio Input
		try {
			clip = AudioSystem.getClip();
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicURL);
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

		// Creation de la scene
		JLabel endScene = new JLabel(new ImageIcon(getClass().getResource("images/Scene_End.gif")));

		// Ajout de scenes dans le JPanel
		endPanel.add(endScene);

		// Listener su JPanel pour
		endPanel.addKeyListener(this);
		endPanel.setFocusable(true);

		// Ajout de JPanel dans la JFrame
		endFrame.getContentPane().add(endPanel, BorderLayout.CENTER);

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		endFrame.setIconImage(icon.getImage());

		// Reglage de la JFrame
		clip.loop(Clip.LOOP_CONTINUOUSLY); // Commence de jouer le son en boucle
		endFrame.setSize(new Dimension(750, 600));
		endFrame.setResizable(false);
		endFrame.setLocationRelativeTo(null);
		endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endFrame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			endFrame.dispose(); // Fermer la JFrame
			clip.close(); // Arrete le son
			Menu menu = new Menu();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

}

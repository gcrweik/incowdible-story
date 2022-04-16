package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Une classe qui permet l'affichage de la fenetre Controls depuis le Menu du
 * jeu.
 * 
 * @author roman_tyzio
 * @version 1.0.0
 *
 */
public class ControlsMenu implements KeyListener {

	/**
	 * JFrame de la classe Controls.
	 */
	static JFrame controlsFrame = new JFrame("Controls");
	/**
	 * JPanel de la classe Controls.
	 */
	private JPanel controlsPanel = new JPanel();
	/**
	 * L'image avec les controls de la classe Controls.
	 */
	private JLabel controlsScene;

	/**
	 * Un constructeur qui permet d'initiliaser le menu Controls.
	 */
	public ControlsMenu() {

		controlsScene = new JLabel(new ImageIcon(getClass().getResource("images/ControlsScene.png")));

		// Ajout de scenes dans le JPanel
		controlsPanel.add(controlsScene);

		// Listener su JPanel pour
		controlsPanel.addKeyListener(this);
		controlsPanel.setFocusable(true);

		// Ajout de JPanel dans la JFrame
		controlsFrame.getContentPane().add(controlsPanel, BorderLayout.CENTER);

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		controlsFrame.setIconImage(icon.getImage());

		// Reglage de la JFrame

		controlsFrame.setSize(new Dimension(800, 600));
		controlsFrame.setResizable(false);
		controlsFrame.setLocationRelativeTo(null);
		controlsFrame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// La methode pas utilisee mais n'est pas a supprimer !

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			controlsFrame.dispose(); // Fermer la JFrame
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// La methode pas utilisee mais n'est pas a supprimer !

	}

}

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

public class ControlsMenu implements KeyListener {

	static JFrame controlsFrame = new JFrame("Controls");
	private JPanel controlsPanel = new JPanel();
	private JLabel controlsScene;

	public ControlsMenu() {

		controlsScene = new JLabel(new ImageIcon(getClass().getResource("images/GoodEnding.gif")));

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
		// La methode pas utilisée mais n'est pas à supprimer !

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			controlsFrame.dispose(); // Fermer la JFrame
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

}

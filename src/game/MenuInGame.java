package game;

import javax.swing.*;

import util_class.RoundedBorder;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MenuInGame implements ActionListener, KeyListener {

	static JFrame miniMenu = new JFrame("Game Options");
	JButton btnSave = new JButton("Save");
	JButton btnOptions = new JButton("Options");
	JButton btnMenu = new JButton("Menu");
	JButton btnExit = new JButton("Exit");

	public MenuInGame() {

		// Image du fond du menu
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("images/menu_background.gif")));
		miniMenu.add(background);
		background.setLayout(new OverlayLayout(background));

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		miniMenu.setIconImage(icon.getImage());

		// Parametrage de Layout
		background.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Parametrage des boutons ronds
		btnSave.setBorder(new RoundedBorder(10));
		btnOptions.setBorder(new RoundedBorder(10));
		btnMenu.setBorder(new RoundedBorder(10));
		btnExit.setBorder(new RoundedBorder(10));

		c.gridx = 0;
		c.gridy = 0;

		// Change la taille des elements
		c.ipadx = 30;
		c.ipady = 1;

		c.gridy = 1; // Change la position dans le layout
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 1;
		background.add(btnSave, c);

		c.gridy = 2;
		c.weighty = 0.5;
		background.add(btnOptions, c);

		c.gridy = 3;
		c.weighty = 1;
		background.add(btnMenu, c);

		c.gridy = 4;
		c.weighty = 0.5;
		background.add(btnExit, c);

		// Listener pour les boutons
		btnSave.addActionListener(this);
		btnOptions.addActionListener(this);
		btnMenu.addActionListener(this);
		btnExit.addActionListener(this);

		// Desactive le focus sur les buttons
		btnSave.setFocusable(false);
		btnOptions.setFocusable(false);
		btnMenu.setFocusable(false);
		btnExit.setFocusable(false);

		// Listener pour revenir au jeu
		miniMenu.addKeyListener(this);
		miniMenu.setFocusable(true);

		// Parametrage de la JFrame
		miniMenu.setSize(new Dimension(750, 410));
		miniMenu.setResizable(false);
		miniMenu.setLocationRelativeTo(null);
		miniMenu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Permet de recuperer le nom de la sauvegarde(sans espaces et caracteres
		// speciaux) et de la sauvegarder.
		if (e.getSource() == btnSave) {
			String saveName = JOptionPane.showInputDialog(miniMenu, "Name your save slot (12 characters max):", null);
			if (!saveName.isEmpty() && saveName.matches("[a-zA-Z0-9]*") && saveName.length()<=12) {
				GUI.savegame(saveName);
			} else {
				JOptionPane.showMessageDialog(miniMenu,
						"The name of the save can't contain any special characters,spaces, be too long or empty!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == btnOptions) {
			// A developper
		}
		if (e.getSource() == btnMenu) {
			miniMenu.dispose(); // Ferme la JFrame.
			GUI.disposeGUIFrame();
			Menu menu = new Menu(); // Revient dans le menu.
		}
		if (e.getSource() == btnExit) {
			System.exit(0);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// La methode pas utilis�e mais n'est pas � supprimer !

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			miniMenu.dispose();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// La methode pas utilis�e mais n'est pas � supprimer !

	}

}
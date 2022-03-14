package jeu;

import javax.swing.*;

import util_class.RoundedBorder;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MenuInGame implements ActionListener, KeyListener {

	static JFrame mini_menu = new JFrame("Game Options");
	JButton btn_save = new JButton("Save");
	JButton btn_options = new JButton("Options");
	JButton btn_menu = new JButton("Menu");
	JButton btn_exit = new JButton("Exit");

	public MenuInGame() {

		// Image du fond du menu
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("images/menu_background.gif")));
		mini_menu.add(background);
		background.setLayout(new OverlayLayout(background));

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		mini_menu.setIconImage(icon.getImage());

		// Parametrage de Layout
		background.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Parametrage des boutons ronds
		btn_save.setBorder(new RoundedBorder(10));
		btn_options.setBorder(new RoundedBorder(10));
		btn_menu.setBorder(new RoundedBorder(10));
		btn_exit.setBorder(new RoundedBorder(10));

		c.gridx = 0;
		c.gridy = 0;

		// Change la taille des elements
		c.ipadx = 30;
		c.ipady = 1;

		c.gridy = 1; // Change la position dans le layout
		c.anchor = GridBagConstraints.CENTER;
		c.weighty = 1;
		background.add(btn_save, c);

		c.gridy = 2;
		c.weighty = 0.5;
		background.add(btn_options, c);

		c.gridy = 3;
		c.weighty = 1;
		background.add(btn_menu, c);

		c.gridy = 4;
		c.weighty = 0.5;
		background.add(btn_exit, c);

		// Listener pour les boutons
		btn_save.addActionListener(this);
		btn_options.addActionListener(this);
		btn_menu.addActionListener(this);
		btn_exit.addActionListener(this);

		// Desactive le focus sur les buttons
		btn_save.setFocusable(false);
		btn_options.setFocusable(false);
		btn_menu.setFocusable(false);
		btn_exit.setFocusable(false);

		// Listener pour revenir au jeu
		mini_menu.addKeyListener(this);
		mini_menu.setFocusable(true);

		// Parametrage de la JFrame
		mini_menu.setSize(new Dimension(750, 410));
		mini_menu.setResizable(false);
		mini_menu.setLocationRelativeTo(null);
		mini_menu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Permet de recuperer le nom de la sauvegarde(sans espaces et caracteres
		// speciaux) et de la sauvegarder.
		if (e.getSource() == btn_save) {
			String save_name = JOptionPane.showInputDialog(mini_menu, "Name your save slot:", null);
			if (!save_name.isEmpty() && save_name.matches("[a-zA-Z0-9]*")) {
				GUI.savegame(save_name);
			} else {
				JOptionPane.showMessageDialog(mini_menu,
						"The name of the save can't contain any special characters,spaces or be empty!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == btn_options) {
			// A developper
		}
		if (e.getSource() == btn_menu) {
			mini_menu.dispose(); // Ferme la JFrame.
			GUI.disposeMenuFrame();
			Menu menu = new Menu(); // Revient dans le menu.
		}
		if (e.getSource() == btn_exit) {
			System.exit(0);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			mini_menu.dispose();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

}
package jeu;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.net.URL;

public class Load {

	static JFrame load_menu = new JFrame("Choose a Save");
	private JList<File> file_list;
	private JPanel load_panel;
	private JTextArea jTextArea1;

	public Load() {

		// Image du fond du menu
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("images/menu_background.gif")));
		load_menu.add(background);
		background.setLayout(new OverlayLayout(background));

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		load_menu.setIconImage(icon.getImage());

		// Parametrage de la JFrame
		load_menu.setSize(new Dimension(750, 410));
		load_menu.setResizable(false);
		load_menu.setLocationRelativeTo(null);
		load_menu.setVisible(true);
	}

}
package jeu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Intro implements KeyListener {

	JFrame intro_frame = new JFrame("Incowdible Story");
	JPanel intro_panel = new JPanel();
	JButton btn_next = new JButton("Next");
	CardLayout card = new CardLayout();
	BufferedImage[] images = new BufferedImage[3];
	int counter = 0; // Counter de scenes

	public Intro() {

		// Ajout d'images dans une liste de BufferedImage
		try {
			images[0] = ImageIO.read(getClass().getResource("images/Scene1.png"));
			images[1] = ImageIO.read(getClass().getResource("images/CoursInterieur.png"));
			images[2] = ImageIO.read(getClass().getResource("images/CoursExterieur.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Creattion de label des scenes
		JLabel scene1 = new JLabel(new ImageIcon(images[0]));
		JLabel scene2 = new JLabel(new ImageIcon(images[1]));
		JLabel scene3 = new JLabel(new ImageIcon(images[2]));

		// Layout qui permet de mettre les labels un sur l'autre
		intro_panel.setLayout(card);

		// Ajout de scenes dans le JPanel
		intro_panel.add(scene1);
		intro_panel.add(scene2);
		intro_panel.add(scene3);

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
			if (counter < 2) {
				card.next(intro_panel);
				counter += 1;
			} else {
				intro_frame.dispose();
				Menu menu = new Menu();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// La methode pas utilisée mais n'est pas à supprimer !

	}

}

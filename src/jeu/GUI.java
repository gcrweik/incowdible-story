package jeu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

public class GUI implements ActionListener, java.io.Serializable {
	private static Jeu jeu;
	private static JFrame fenetre;
	private JPanel panel = new JPanel();
	private JTextField entree = new JTextField(34);
	private JTextArea texte = new JTextArea();
	public JLabel image = new JLabel();
	InputMap gui_input = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW); // Pour ouvrir le menu in game.
	static ImageIcon check_icon = new ImageIcon(GUI.class.getResource("images/check_icon.png")); // L'icone pour un
																									// MessageDialog

	public GUI(Jeu j) {
		jeu = j;
		creerGUI();
	}

	public void afficher(String s) {
		texte.append(s);
		texte.setCaretPosition(texte.getDocument().getLength());
	}

	public void afficher() {
		afficher("\n");
	}

	public void afficheImage(String nomImage) {
		URL imageURL = this.getClass().getClassLoader().getResource("jeu/images/" + nomImage);
		if (imageURL != null) {
			image.setIcon(new ImageIcon(imageURL));
			
			final JTextField text = new JTextField();
			;
			fenetre.add(text, BorderLayout.SOUTH);

			image.addMouseListener(new MouseListener() {
				public void mousePressed(MouseEvent me) {
				}

				public void mouseReleased(MouseEvent me) {
				}

				public void mouseEntered(MouseEvent me) {
				}

				public void mouseExited(MouseEvent me) {
				}

				public void mouseClicked(MouseEvent me) {
					int x = me.getX();
					int y = me.getY();
					text.setText("X:" + x + " Y:" + y);
				}
			});
		}
	}
	
	// Methode permettant de créer puis afficher l'elements aux coordonées donné.
	public JLabel afficheElement(Element element) {
		JLabel objectLabel = new JLabel();
		objectLabel.setBounds(element.x, element.y, element.imageWidth, element.imageHeight);

		URL imageURL = this.getClass().getClassLoader().getResource("jeu/images/" + element.imageName);
		if (imageURL != null) {
			objectLabel.setIcon(new ImageIcon(imageURL));
			image.add(objectLabel);
			return objectLabel;
		} else
			System.out.println("Une erreur est arrivée");
		return null;
	}

	public void enable(boolean ok) {
		entree.setEditable(ok);
		if (!ok)
			entree.getCaret().setBlinkRate(0);
	}

	private void creerGUI() {

		// Instance de la JFrame
		fenetre = new JFrame("The Incowdible Story");

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		fenetre.setIconImage(icon.getImage());

		texte.setEditable(false);
		JScrollPane listScroller = new JScrollPane(texte);
		listScroller.setPreferredSize(new Dimension(1920, 1080));
		listScroller.setMinimumSize(new Dimension(850, 600));

		image.setHorizontalAlignment(JLabel.CENTER);
		image.setVerticalAlignment(JLabel.CENTER);

		panel.setLayout(new BorderLayout());
		panel.add(image, BorderLayout.NORTH);
		panel.add(listScroller, BorderLayout.CENTER);
		panel.add(entree, BorderLayout.SOUTH);

		fenetre.getContentPane().add(panel, BorderLayout.CENTER);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Pour afficher un menu PopUp dans le jeu
		gui_input.put(KeyStroke.getKeyStroke("ESCAPE"), "escape_pressed");
		panel.getActionMap().put("escape_pressed", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				MenuInGame mini_menu = new MenuInGame();

			}
		});

		// Listener pour le chat.
		entree.addActionListener(this);
		entree.requestFocus();

		// Parametrage de l'ecran.
		fenetre.setSize(1920,800);
		fenetre.setVisible(true);
		entree.requestFocusInWindow();
	}

	/*
	 * Methode qui ferme la fenetre
	 */
	public static void disposeMenuFrame() {
		fenetre.dispose();
	}

	/**
	 * Une methode qui permet de sauvegarder une partie en cours.
	 * 
	 * @param save_name Le nom de la sauvegarde.
	 */
	static void savegame(String save_name) {
		try {
			File saved_file = new File("saves/"+save_name + ".sav");
			if (!saved_file.exists()) {
				FileOutputStream fos = new FileOutputStream(saved_file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(jeu);
				oos.flush();
				oos.close();
				System.out.println("Game saved");
				JOptionPane.showMessageDialog(MenuInGame.mini_menu, "Save has been created!", "Created",
						JOptionPane.INFORMATION_MESSAGE, check_icon);
			} else {
				JOptionPane.showMessageDialog(MenuInGame.mini_menu, "Slot with this name exists already! ",
						"Already Exists", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			System.out.println("Serialisation error!\n" + e.getClass() + ": " + e.getMessage() + "\n");

		}

	}
	
	
	/**
	 * Une methode qui permet de charger une partie à partir du fichier de sauvegarde.
	 * @return la partie sauvegardée.
	 */
	static Jeu loadGame() {
		try {
			File loaded_file = new File("saves/Save1.sav");
			FileInputStream fis = new FileInputStream(loaded_file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			jeu = (Jeu) ois.readObject();
			ois.close();
			System.out.println("Game Loaded!");
		} catch (Exception e) {
			System.out.println("Serialization error! Cant load data. \n ");
			System.out.println(e.getClass() + e.getMessage() + "\n");
		}
		return jeu;
	}

	public void actionPerformed(ActionEvent e) {
		executerCommande();
	}

	private void executerCommande() {
		String commandeLue = entree.getText();
		entree.setText("");
		jeu.traiterCommande(commandeLue);
	}

}
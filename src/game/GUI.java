package game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

/**
 * Une classe qui permet de initialiser le jeu comme le joueur le vois. Une des
 * classes les plus importantes.
 * 
 * @author mohamed_hanouche
 * @author roman_tyzio
 * @version 1.0.0
 *
 */
public class GUI implements ActionListener, java.io.Serializable {
	/**
	 * serialVersionUID pour la sauvegarde.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Une instance de la classe Game.
	 */
	private static Game game;
	/**
	 * JFrame de la classe Game.
	 */
	private static JFrame guiFrame;
	/**
	 * JPanel de la classe Game.
	 */
	private JPanel guiPanel = new JPanel();
	/**
	 * Une zone dans laquelle on peut rentrer les commandes.
	 */
	private JTextField textInput = new JTextField(34);
	/**
	 * Le chat du jeu.
	 */
	private JTextArea chat = new JTextArea();
	/**
	 * La zone que le joueur voit.
	 */
	public JLabel image = new JLabel();
	/**
	 * Une instance de la classe InputMap qui sert a ecouter si on appuie sur Escape
	 * pour ouvrir le menu dans le jeu.
	 */
	private InputMap guiInputMap = guiPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

	/**
	 * Une image qui remplace l'icone de MessageDialog.
	 */
	static ImageIcon checkIcon = new ImageIcon(GUI.class.getResource("images/check_icon.png"));
	/**
	 * La musique principale du jeu.
	 */
	static Music musicGame = new Music("GameMusic");

	/**
	 * Une des methodes qui permettent de lancer le jeu.
	 * 
	 * @param j Une instance de la classe Game;
	 */
	public GUI(Game j) {
		game = j;
		createGUI();
	}

	/**
	 * Une methode qui permet d'afficher un message dans le chat.
	 * 
	 * @param s Le message a afficher.
	 */
	public void show(String s) {
		chat.append(s);
		chat.setCaretPosition(chat.getDocument().getLength());
	}

	/**
	 * Une methode qui permet de revenir a la ligne.
	 */
	public void show() {
		show("\n");
	}

	/**
	 * Une methode qui affiche les zones.
	 * 
	 * @param nomImage Le nom de fichier image de zone.
	 */
	public void showImage(String nomImage) {
		URL imageURL = this.getClass().getClassLoader().getResource("game/images/" + nomImage);
		if (imageURL != null) {
			image.setIcon(new ImageIcon(imageURL));

			final JTextField text = new JTextField();
			;
			guiFrame.add(text, BorderLayout.SOUTH);

			image.addMouseListener(new MouseListener() {
				public void mousePressed(MouseEvent me) {
				}

				public void mouseReleased(MouseEvent me) {
				}

				public void mouseEntered(MouseEvent me) {
				}

				public void mouseExited(MouseEvent me) {
				}

				// Permet d'afficher les coordonnees de l'endroit d'ecran ou on clique.
				// Utilisation : connaitre les coordonnes pour placer les elements,
				// personnages,etc. Ne fonctionne plus car -> gameFrame.setResizable(false);
				public void mouseClicked(MouseEvent me) {
					int x = me.getX();
					int y = me.getY();
					text.setText("X:" + x + " Y:" + y);
				}
			});
		}
	}

	/**
	 * Une methode permettant de creer puis afficher l'elements aux coordonees
	 * donnees.
	 * 
	 * @param e L'element a afficher.
	 * @return Nouveau JLabel.
	 */
	public JLabel showElement(Element e) {
		JLabel objectLabel = new JLabel();
		objectLabel.setBounds(e.x, e.y, e.imageWidth, e.imageHeight);

		URL imageURL = this.getClass().getClassLoader().getResource("game/images/" + e.imageName);
		if (imageURL != null) {
			objectLabel.setIcon(new ImageIcon(imageURL));
			image.add(objectLabel);
			image.repaint();
			image.revalidate();
			return objectLabel;
		} else
			System.out.println("Une erreur est arrivee");
		return null;
	}

	/**
	 * Une methode permettant de replacer le personnage principal aux coordonnees
	 * donnees.
	 * 
	 * @param e Le personnage principal a deplacer.
	 * @param x La cordoonnee x pour le deplacement.
	 * @param y La cordoonnee y pour le deplacement.
	 */
	public void replaceMainCharacter(MainCharacter e, int x, int y) {
		// Enleve tout ce qu'il y a sur l'image
		image.removeAll();
		e.setCoordinates(x, y);
		showElement(e);
		game.initialize();
		if (!game.currentZone.exits.isEmpty()) {
			show(game.currentZone.longDescription());
			show();
		}
		if (!game.currentZone.actions.isEmpty()) {
			show(game.currentZone.longDescriptionAction());
			show();
		}
	}

	/**
	 * Permet d'ecrire dans le champs dedie au inputs d'utilisateur, en dessous du
	 * chat du jeu.
	 * 
	 * @param ok Permet d'ecrire.
	 */
	public void enable(boolean ok) {
		textInput.setEditable(ok);
		if (!ok)
			textInput.getCaret().setBlinkRate(0);
	}

	/**
	 * Une methode qui permet de creer le GUI du jeu.
	 */
	private void createGUI() {

		// Instance de la JFrame
		guiFrame = new JFrame("The Incowdible Story");

		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		guiFrame.setIconImage(icon.getImage());

		chat.setEditable(false);
		JScrollPane listScroller = new JScrollPane(chat);
		listScroller.setPreferredSize(new Dimension(1920, 1080));
		listScroller.setMinimumSize(new Dimension(850, 600));

		image.setHorizontalAlignment(JLabel.CENTER);
		image.setVerticalAlignment(JLabel.CENTER);

		guiPanel.setLayout(new BorderLayout());
		guiPanel.add(image, BorderLayout.NORTH);
		guiPanel.add(listScroller, BorderLayout.CENTER);
		guiPanel.add(textInput, BorderLayout.SOUTH);

		guiFrame.setResizable(false); // A noter: Ca enleve la barre des coordonees comme effet indesirable
		guiFrame.getContentPane().add(guiPanel, BorderLayout.CENTER);

		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Pour afficher un menu PopUp dans le jeu
		guiInputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "escape_pressed");
		guiPanel.getActionMap().put("escape_pressed", new AbstractAction() {
			/**
			 * serialVersionUID pour la sauvegarde.
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				MenuInGame miniMenu = new MenuInGame();

			}
		});

		// Listener pour le chat.
		textInput.addActionListener(this);
		textInput.requestFocus();

		// Parametrage de l'ecran.
		musicGame.playMusic();
		guiFrame.setSize(1920, 800);
		guiFrame.setVisible(true);
		textInput.requestFocusInWindow();
	}

	/**
	 * Une methode qui permet de fermer la fenetre du jeu.
	 */
	public static void disposeGUIFrame() {
		guiFrame.dispose();
	}

	/**
	 * Une methode qui permet de sauvegarder une partie en cours.
	 * 
	 * @param saveName Le nom de la sauvegarde.
	 */
	static void saveGame(String saveName) {
		try {
			File savedFile = new File("saves/gamesaves/" + saveName + ".sav");
			if (!savedFile.exists()) {
				FileOutputStream fos = new FileOutputStream(savedFile);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(game);
				oos.flush();
				oos.close();
				System.out.println("Game saved");
				JOptionPane.showMessageDialog(MenuInGame.miniMenu, "Save has been created!", "Created",
						JOptionPane.INFORMATION_MESSAGE, checkIcon);
			} else {
				JOptionPane.showMessageDialog(MenuInGame.miniMenu, "Slot with this name already exists! ",
						"Already Exists", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			System.out.println("Serialisation error!\n" + e.getClass() + ": " + e.getMessage() + "\n");

		}

	}

	/**
	 * Une methode qui permet de charger une partie a partir du fichier de
	 * sauvegarde.
	 * 
	 * @param loadName Le nom de la sauvegarde a charger.
	 * @return La La partie sauvegardee.
	 */
	static Game loadGame(String loadName) {
		try {
			File loadedFile = new File("saves/gamesaves/" + loadName + ".sav");
			FileInputStream fis = new FileInputStream(loadedFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			game = (Game) ois.readObject();
			ois.close();
			System.out.println("Game Loaded!");
		} catch (Exception e) {
			System.out.println("Serialization error! Cant load data. \n ");
			System.out.println(e.getClass() + e.getMessage() + "\n");
		}
		return game;
	}

	/**
	 * Une methode qui permet d'executer la commande.
	 */
	public void actionPerformed(ActionEvent e) {
		runCommand();
	}

	/**
	 * Une methode qui execute la commande d'utilisateur et remet l'espace pour
	 * rentrer les commandes vide.
	 */
	private void runCommand() {
		String commandeLue = textInput.getText();
		textInput.setText("");
		game.executeCommand(commandeLue);
	}

}
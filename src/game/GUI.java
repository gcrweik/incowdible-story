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

public class GUI implements ActionListener, java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Game game;
	private static JFrame guiFrame;
	private JPanel guiPanel = new JPanel();
	private JTextField textInput = new JTextField(34);
	private static JTextArea chat = new JTextArea();
	public static JLabel image = new JLabel();
	private InputMap guiInputMap = guiPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW); // Pour ouvrir le menu in game.
	static ImageIcon checkIcon = new ImageIcon(GUI.class.getResource("images/check_icon.png")); // L'icone pour un
																								// MessageDialog
	
	
	public GUI(Game j) {
		game = j;
		createGUI();
	}

	public static void show(String s) {
		chat.append(s);
		chat.setCaretPosition(chat.getDocument().getLength());
	}

	public void show() {
		show("\n");
	}

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

				public void mouseClicked(MouseEvent me) {
					int x = me.getX();
					int y = me.getY();
					text.setText("X:" + x + " Y:" + y);
				}
			});
		}
	}

	

	public void enable(boolean ok) {
		textInput.setEditable(ok);
		if (!ok)
			textInput.getCaret().setBlinkRate(0);
	}

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

		guiFrame.getContentPane().add(guiPanel, BorderLayout.CENTER);

		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Pour afficher un menu PopUp dans le jeu
		guiInputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "escape_pressed");
		guiPanel.getActionMap().put("escape_pressed", new AbstractAction() {
			/**
			 * 
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
		guiFrame.setSize(1920, 800);
		guiFrame.setVisible(true);
		textInput.requestFocusInWindow();
	}

	/*
	 * Methode qui ferme la fenetre
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
				JOptionPane.showMessageDialog(MenuInGame.miniMenu, "Slot with this name exists already! ",
						"Already Exists", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			System.out.println("Serialisation error!\n" + e.getClass() + ": " + e.getMessage() + "\n");

		}

	}

	/**
	 * Une methode qui permet de charger une partie à partir du fichier de
	 * sauvegarde.
	 * 
	 * @return la partie sauvegardée.
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

	public void actionPerformed(ActionEvent e) {
		runCommand();
	}

	private void runCommand() {
		String commandeLue = textInput.getText();
		textInput.setText("");
		game.executeCommand(commandeLue);
	}

}
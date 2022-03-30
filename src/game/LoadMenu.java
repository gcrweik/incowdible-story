package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class LoadMenu implements ActionListener {

	static JFrame loadFrame = new JFrame("Choose a Save");
	private JList<String> listSaves;
	private JPanel loadPanelList;
	private JPanel loadPanelButton;
	private JScrollPane loadScroll;
	private JButton btnPlay = new JButton("Play");
	private JButton btnDelete = new JButton("Delete");
	private String[] fileNames; // Une liste de nom de sauvegardes.
	private String[] refreshedSaves; // Une list pour refresh aprés une supression.
	private File f = new File("saves/gamesaves");// Le chemin vers le dossier des sauvegardes.
	private ImageIcon checkIcon = new ImageIcon(GUI.class.getResource("images/check_icon.png")); // L'icone pour un
																									// MessageDialog
	private Color blueBackground = new Color(170, 224, 242);
	private Border elementBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

	public LoadMenu() {

		// Initialisation des elements
		loadPanelList = new JPanel();
		loadPanelButton = new JPanel();
		loadScroll = new JScrollPane(loadPanelList);
		listSaves = new JList<String>();

		// Image du fond du menu
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("images/secondary_menu.png")));

		// Changement de l'icon de l'application
		java.net.URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		loadFrame.setIconImage(icon.getImage());

		fileNames = f.list(formatFilter);// Ajout dans la liste avec le filtre.

		listSaves.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public int getSize() {
				return fileNames.length;
			}

			@Override
			public String getElementAt(int i) {
				return removeExtension(fileNames[i]);
			}
		});

		listSaves.setCellRenderer(new DefaultListCellRenderer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				c.setPreferredSize(new Dimension(150, 50)); // Taille de l'element de la JList.
				((JComponent) c).setBorder(elementBorder);
				JPanel p = new JPanel();
				this.setHorizontalAlignment(JLabel.CENTER);
				this.setFont(new Font("Helvetica", Font.BOLD, 15));
				p.add(c);
				p.setBackground(blueBackground);
				return p;
			}
		});

		// Listeners pour les boutons.
		btnPlay.addActionListener(this);
		btnDelete.addActionListener(this);

		// Parametrage de la JFrame
		loadPanelList.setOpaque(false);
		loadPanelButton.setOpaque(false);
		loadFrame.setSize(new Dimension(400, 310));
		loadFrame.setResizable(false);
		loadFrame.setLocationRelativeTo(null);
		loadFrame.setContentPane(background);
		loadFrame.setLayout(new BorderLayout());
		loadPanelButton.add(btnPlay);
		loadPanelButton.add(btnDelete);
		loadPanelList.add(listSaves);
		loadScroll.setOpaque(false);
		loadScroll.getViewport().setOpaque(false);
		loadPanelList.setAutoscrolls(true);
		loadScroll.setPreferredSize(new Dimension(300, 150));
		loadFrame.add(loadScroll, BorderLayout.CENTER);
		loadFrame.add(loadPanelButton, BorderLayout.SOUTH);
		loadFrame.setVisible(true);

	}

	// Filtrer la recherche par le format, en occurrence .sav
	FilenameFilter formatFilter = new FilenameFilter() {
		@Override
		public boolean accept(File f, String name) {
			return name.endsWith(".sav");
		}
	};

	/**
	 * Une methode qui permet d'enlever le format d'un fichier dans un String.
	 * 
	 * @param fileNames Le nom du fichier.
	 * @return Retourne le fichier sans son format.
	 */
	private static String removeExtension(String fileNames) {
		fileNames = fileNames.substring(0, fileNames.lastIndexOf("."));
		return fileNames;
	}

	/**
	 * Une methode qui permet d'enlever le format de chaque element de String[]
	 * Array.
	 * 
	 * @param fileNames String[] Array avec les elements au format ".sav".
	 * @return String[] Array avec les elements sans le format.
	 */
	private static String[] removeListExtension(String[] fileNames) {
		ArrayList<String> tempSaves = new ArrayList<String>();
		for (int i = 0; i < fileNames.length; i++) {
			tempSaves.add(fileNames[i]);
		}
		tempSaves.replaceAll(saves -> saves.replace(".sav", ""));
		String[] newSaves = tempSaves.toArray(new String[0]);
		return newSaves;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPlay) {
			try {
				String playedSaves = listSaves.getSelectedValue().toString();
				Menu.musicMenu.stopMusic();
				Menu.disposeMenuFrame();
				loadFrame.dispose();
				Game loadedGame = GUI.loadGame(playedSaves); // Charge une partie à partir de la sauvegarde
				GUI gui = new GUI(loadedGame);
				loadedGame.setGUI(gui);
			} catch (Exception nothingChosen) {
				JOptionPane.showMessageDialog(loadFrame, "Choose a save !", "Warning", JOptionPane.WARNING_MESSAGE);

			}
		}

		if (e.getSource() == btnDelete) {
			try {
				String clickedSave = listSaves.getSelectedValue().toString();
				String deletedSave = "saves/gamesaves/" + clickedSave + ".sav";

				int confirmDelete = JOptionPane.showConfirmDialog(null, "Do you really want to delete this save?",
						"Delete a save!", JOptionPane.YES_NO_OPTION);
				if (confirmDelete == JOptionPane.YES_OPTION) {
					boolean result = Files.deleteIfExists(Paths.get(deletedSave));

					refreshedSaves = f.list(formatFilter);// Ajout dans la liste avec le filtre.

					listSaves.setListData(removeListExtension(refreshedSaves));
					loadPanelList.revalidate();
					loadPanelList.repaint();
					JOptionPane.showMessageDialog(MenuInGame.miniMenu, "Save is deleted!", "Deleted",
							JOptionPane.INFORMATION_MESSAGE, checkIcon);
					if (result) {
						System.out.println("Save is deleted!");
					} else {
						System.out.println("Error,can't delete this save!");
					}

				}

			} catch (Exception nothingChosen) {
				JOptionPane.showMessageDialog(loadFrame, "Choose a save !", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

}
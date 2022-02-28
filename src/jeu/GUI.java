package jeu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GUI implements ActionListener
{
	private Jeu jeu;
	private JFrame fenetre;
	private JTextField entree;
	private JTextArea texte;
	private JLabel image;
	
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

	public void afficheImage( String nomImage) {
	   	URL imageURL = this.getClass().getClassLoader().getResource("jeu/images/" + nomImage);
	   	if ( imageURL != null ) {
	   		image.setIcon( new ImageIcon( imageURL ));
	   	}
	}

	public void enable(boolean ok) {
		entree.setEditable(ok);
		if (!ok )
			entree.getCaret().setBlinkRate(0);
	}

	private void creerGUI() {
		
		//Titre du jeu 
		fenetre = new JFrame("Incowdible Story");
		entree = new JTextField(34);

		
		// Changement de l'icon de l'application
		URL iconURL = getClass().getResource("images/IconApplication.png");
		ImageIcon icon = new ImageIcon(iconURL);
		fenetre.setIconImage(icon.getImage());
		
		texte = new JTextArea();
		texte.setEditable(false);
		JScrollPane listScroller = new JScrollPane(texte);
		listScroller.setPreferredSize(new Dimension(1920,1080));
		listScroller.setMinimumSize(new Dimension(850, 600));

		JPanel panel = new JPanel();
		image = new JLabel();
		
		image.setHorizontalAlignment(JLabel.CENTER);
		image.setVerticalAlignment(JLabel.CENTER);
		
		panel.setLayout(new BorderLayout());
		panel.add(image, BorderLayout.NORTH);
		panel.add(listScroller, BorderLayout.CENTER);
		panel.add(entree, BorderLayout.SOUTH);
		
		fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		entree.addActionListener(this);

		
		// Permet de mettre la fenetre en plein ecran au lancement
		fenetre.pack();
		fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fenetre.setVisible(true);
		entree.requestFocus();
		

	}

	public void actionPerformed(ActionEvent e) {
		executerCommande();
	}

	private void executerCommande() {
		String commandeLue = entree.getText();
		entree.setText("");
		jeu.traiterCommande( commandeLue);
	}
}
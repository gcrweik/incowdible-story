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
		fenetre = new JFrame("Jeu");
		entree = new JTextField(34);

		texte = new JTextArea();
		texte.setEditable(false);
		JScrollPane listScroller = new JScrollPane(texte);
		listScroller.setPreferredSize(new Dimension(1920,1080));
		listScroller.setMinimumSize(new Dimension(850, 600));

		JPanel panel = new JPanel();
		image = new JLabel();
		
		
		panel.setLayout(new BorderLayout());
		panel.add(image, BorderLayout.NORTH);
		panel.add(listScroller, BorderLayout.CENTER);
		panel.add(entree, BorderLayout.SOUTH);
		
		fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		entree.addActionListener(this);

		fenetre.pack();
		// Permet de mettre la fenetre en plein ecran au lancement
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
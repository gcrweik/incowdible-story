package utilclass;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

/**
 * Une classe qui permet de creer les boutons ronds.
 * 
 * @author roman_tyzio
 * @version 1.0.0
 */
public class RoundedBorder implements Border {

	/**
	 * Le rayon de bouton.
	 */
	private int radius;

	/**
	 * Un costructeur de la classe RoundedBouton
	 * 
	 * @param radius A quel point le coins de bouton sont ronds.
	 */
	public RoundedBorder(int radius) {
		this.radius = radius;
	}

	/**
	 * Une methode qui creer un nouveau Inset avec le rayon souhaite.
	 */
	public Insets getBorderInsets(Component c) {
		return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
	}

	/**
	 * Une methode qui verifie si le composant est opaque.
	 */
	public boolean isBorderOpaque() {
		return true;
	}

	/**
	 * Une methode redefinie paintBorder.
	 */
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
	}

}

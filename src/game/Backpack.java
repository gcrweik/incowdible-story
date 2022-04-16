package game;

import java.util.ArrayList;

/**
 * Une classe qui permet de creer un sac a dos qui permet d'ajouter les elements
 * dans une ArrayList.
 * 
 * @author mohamed_hanouche
 * @author gerard_rweik
 * @version 1.0.0
 *
 */
public class Backpack implements java.io.Serializable {

	/**
	 * serialVersionUID pour la sauvegarde.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Presence des cigarettes dans le sac a dos.
	 */
	public boolean cigs;
	/**
	 * Presence d'une cle dans le sac a dos.
	 */
	public boolean key;
	/**
	 * Presence d'un secateur dans le sac a dos.
	 */
	public boolean pliers;
	/**
	 * Presence d'une pelle dans le sac a dos.
	 */
	public boolean shovel;
	/**
	 * Les elements du sac a dos.
	 */
	private ArrayList<Element> contenu;

	/**
	 * Un constructeur qui permet de creer un sac a dos avec les elements et leurs
	 * indicateurs sur "false".
	 */
	public Backpack() {
		this.contenu = new ArrayList<Element>();
		this.cigs = false;
		this.key = false;
		this.pliers = false;
		this.shovel = false;
	}

	/**
	 * Une methode qui renvoie le contenu du sac a dos.
	 * 
	 * @return Le contenu du sac a dos, les instances de la classe Element.
	 */
	public ArrayList<Element> getContenu() {
		return this.contenu;
	}

	/**
	 * Une methode qui permet d'ajouter un objet dans le sac a dos.
	 * 
	 * @param e Un objet de classe Element qui doit etre ajouter.
	 */
	public void addElement(Element e) {
		if (!contenu.contains(e))
			this.contenu.add(e);
	}

	/**
	 * Une methode qui permet d'enlever un objet dans le sac a dos.
	 * 
	 * @param e Un objet de classe Element qui doit etre supprimer.
	 */
	public void removeElement(Element e) {
		if (contenu.contains(e))
			this.contenu.remove(e);
	}

	@Override
	public String toString() {
		String result;
		if (this.contenu.size() > 0) {
			result = "Vous avez dans votre sac a dos les elements suivantes:" + "\n";
			for (Element e : this.getContenu()) {
				result += "-" + e.toString() + "\n";
			}
		} else {
			result = "Votre sac a dos est vide!";
		}
		return result;
	}
}

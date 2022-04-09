package game;

import java.util.ArrayList;

public class Backpack implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Element> contenu;
	
	public Backpack() {
		this.contenu = new ArrayList<Element>();
	}

	public ArrayList<Element> getContenu() {
		return this.contenu;
	}
	
	public void addElement(Element e) {
		if(!contenu.contains(e))
			this.contenu.add(e);
	}
	
	@Override
	public String toString() {
		String result;
		if(this.contenu.size() > 0) {
			result = "Vous avez dans votre sac � dos les elements suivantes:"+"\n";
			for(Element e: this.getContenu()) {
				result += "-"+e.toString()+"\n";
			}
		} else {
			result = "Votre sac � dos est vide!";
		}
		return result;
	}
}
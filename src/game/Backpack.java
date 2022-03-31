package game;

import java.util.ArrayList;

public class Backpack {
	private ArrayList<Element> contenu;
	
	public Backpack() {
		this.contenu = new ArrayList<Element>();
	}

	public ArrayList<Element> getContenu() {
		return this.contenu;
	}
	
	public void addElement(Element e) {
		this.contenu.add(e);
	}
	
	@Override
	public String toString() {
		String result;
		if(this.contenu.size() > 0) {
			result = "Vous avez dans votre sac à dos les elements suivantes:"+"\n";
			for(Element e: this.getContenu()) {
				result += "-"+e.toString()+"\n";
			}
		} else {
			result = "Votre sac à dos est vide!";
		}
		return result;
	}
}

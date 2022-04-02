package game;

import java.util.List;
import java.util.ArrayList;

public class Npc extends Element {
	
	private List<String> sentences = new ArrayList<String>();

	public Npc(int x, int y, int imageWidth, int imageHeight, String imageName) {
		super(x, y, imageWidth, imageHeight, imageName);
	}

	

}

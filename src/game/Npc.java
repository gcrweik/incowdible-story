package game;

import java.util.Map;
import java.util.HashMap;

public class Npc extends Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<Integer, String> billyDialogs = new HashMap<>();
	Map<Integer, String> joeDialogs = new HashMap<>();
	Map<Integer, String> matouDialogs = new HashMap<>();
	Map<Integer, String> jackDialogs = new HashMap<>();
	int dialogCounter = 0;
	private String phrase;
	private String nameNpc;
	int dialogStateBilly = 0;

	public Npc(int x, int y, int imageWidth, int imageHeight, String imageName, String nameNpc) {
		super(x, y, imageWidth, imageHeight, imageName);
		this.nameNpc = nameNpc;
	}

	public String dialogBilly() {
		billyDialogs.put(0, "Billy: Hey,toi...viens par ici...\n");
		billyDialogs.put(1, "Billy: T'es la nouvelle detenu c'est ca?\n");
		billyDialogs.put(2, "Billy: Pas très bavarde à ce que je vois.\n");
		billyDialogs.put(3, "Billy: Mais ca m'arronge, comme ca j'ai pas à m'arreter\n pour t'ecouter.\n");
		billyDialogs.put(4, "Billy: Aloooooors...par ou commencer...\n");
		billyDialogs.put(5,
				"Billy: Les personnes que tu dois connaître et les seuls qui sont ici d’ailleurs,\n"
						+ " sont Old Joe, c’est le plus  ancien ici, une légende raconte que\n"
						+ "c’est lui qui a bâti cette prison de ses propres mains.\n");

		billyDialogs.put(6, "Billy: Jack the  Smuggler, peu importe ce que tu recherches, il te le trouvera !\n");

		billyDialogs.put(7, "Billy: Matou le Maton, le seul maton de cette prison, mais je pense que c’est parce que\n"
				+ " les dévelo... , hmmm, les directeurs de la prison,  n’ont pas eu le temps d’employer d’autres, bref ,\n"
				+ " si Matou te pose une question sur sa matraque réponds lui simplement, “Jolie” ...”\n");
		billyDialogs.put(8, "Billy: Et moi-meme, appelle moi Billy.\n");
		billyDialogs.put(9, "Billy: Maintenant bouge d'ici avant que Matou nous voit discuter!\n");

		phrase = billyDialogs.get(dialogCounter);
		return phrase;

	}

	public String getName() {
		return this.nameNpc;
	}

	public Map<Integer, String> getNpcDialogs() {
		if (this.getName() == "Joe") {
			return this.joeDialogs;
		} else if (this.getName() == "Jack") {
			return jackDialogs;
		} else if (this.getName() == "Matou") {
			return matouDialogs;
		}
		return billyDialogs;
	}

}

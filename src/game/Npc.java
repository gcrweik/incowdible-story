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
	private int dialogStateBilly = 0;
	private int dialogStateJoe = 0;
	private int dialogStateMatou = 0;
	private int dialogStateJack = 0;
	private String dialogOwner;

	public Npc(int x, int y, int imageWidth, int imageHeight, String imageName, String nameNpc) {
		super(x, y, imageWidth, imageHeight, imageName);
		this.nameNpc = nameNpc;
	}

	public String dialogBilly() {
		dialogOwner = "💬Billy: ";
		if (dialogStateBilly == 0) {
			billyDialogs.put(0, dialogOwner + "Hey,toi...viens par ici...\n");
			billyDialogs.put(1, dialogOwner + "T'es la nouvelle detenu c'est ca?\n");
			billyDialogs.put(2, dialogOwner + "Pas très bavarde à ce que je vois.\n");
			billyDialogs.put(3, dialogOwner + "Mais ca m'arronge, comme ca j'ai pas à m'arreter\n pour t'ecouter.\n");
			billyDialogs.put(4, dialogOwner + "Aloooooors...par ou commencer...\n");
			billyDialogs.put(5,
					dialogOwner + "Les personnes que tu dois connaître et les seuls qui sont ici d’ailleurs,\n"
							+ " sont Old Joe, c’est le plus  ancien ici, une légende raconte que\n"
							+ "c’est lui qui a bâti cette prison de ses propres mains.\n");

			billyDialogs.put(6,
					dialogOwner + "Jack the  Smuggler, peu importe ce que tu recherches, il te le trouvera !\n");

			billyDialogs.put(7, dialogOwner
					+ "Matou le Maton, le seul maton de cette prison, mais je pense que c’est parce que\n"
					+ " les dévelo... , hmmm, les directeurs de la prison,  n’ont pas eu le temps d’employer d’autres, bref ,\n"
					+ " si Matou te pose une question sur sa matraque réponds lui simplement, “Jolie” ...”\n");
			billyDialogs.put(8, dialogOwner + "Et moi-meme, appelle moi Billy.\n");
			billyDialogs.put(9,
					dialogOwner + "Maintenant bouge d'ici,va voir Old Joe, avant que Matou nous voit discuter!\n");

			phrase = billyDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateBilly == 1) {
			billyDialogs.clear();
			billyDialogs.put(0, dialogOwner + "T'es encore la toi? Va voir Old Joe avant qu'on se fasse choper!\n");
			phrase = billyDialogs.get(dialogCounter);
			return phrase;
		}
		return null;

	}

	public String dialogJoe() {
		dialogOwner = "💬Joe: ";
		if (dialogStateJoe == 0) {
			joeDialogs.put(0, dialogOwner + "*Entrain de s'endormir sur sa canne.*\n");
			joeDialogs.put(1, dialogOwner + "GERALDINE !!! C'est toi?\n");
			joeDialogs.put(2, dialogOwner + "Ah non... et qui est tu?\n");
			joeDialogs.put(3,
					dialogOwner + "Quoi que, t'embete pas à me repondre, je vais pas m'en rappeler de toute facon\n");
			joeDialogs.put(4, dialogOwner + "Tu sais quoi reviens me voir plus tard... si je suis reveillé...\n");

			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateJoe == 1) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "GERALDINE !!! Ah, toujours pas...\n");
			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateJoe == 2) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "Ecoute attentivement mon enigme....\n");
			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}
		return null;

	}

	public String dialogMatou() {
		dialogOwner = "💬Matou: ";
		if (dialogStateMatou == 0) {
			matouDialogs.put(0, dialogOwner + "*Entrain de caresser la matraque dans ses pattes*\n");
			matouDialogs.put(1, dialogOwner + "*Regarde vers Marguerite*\n");
			matouDialogs.put(2, dialogOwner + "Oh ! J'en suis sure que c'est la nouvelle detenu dont ma chere\n "
					+ " Lucie m'a parlé...\n");
			matouDialogs.put(3, dialogOwner + "Et c'est ou qu tu vas comme ca?\n");
			matouDialogs.put(4, dialogOwner + "*Caresse la lettre 'L' sur la matraque*\n");
			matouDialogs.put(5, dialogOwner + "Ducoup tu veux passer dans la salle de sport c'est ca?\n");
			matouDialogs.put(6, dialogOwner + "Moi et Lucie on  adore les enigmes\n");
			matouDialogs.put(7,
					dialogOwner + "Tu reponds correctement à une seule question et je te laisse passer...\n");
			matouDialogs.put(8,
					dialogOwner + "Tu te trompes et... tu vas faire connaissance avec ma charmante Lucie.\n");
			matouDialogs.put(9, dialogOwner + "Alors dis-moi, comment trouves-tu cette matraque ?\n");
			matouDialogs.put(10, "Trois choses vous viennent à l'esprit : neuve, repugnante, jolie\n");
			matouDialogs.put(11, "Qu'est ce que vous allez dire ?\n");

			phrase = matouDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateMatou == 1) {
			matouDialogs.clear();
			matouDialogs.put(0, dialogOwner + "Aller ! Vite fait ton choix.\n");
			phrase = matouDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateMatou == 2) {
			matouDialogs.clear();
			matouDialogs.put(0, dialogOwner + "Faux ! Je vais te montrer le mauvais caractere de ma Lucie...\n");
			phrase = matouDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateMatou == 3) {
			matouDialogs.clear();
			matouDialogs.put(0, dialogOwner + "Exactement ! Tu l'as comprends si bien ! \n");
			matouDialogs.put(1, dialogOwner + "Tu peux passer mon ami ! \n");
			phrase = matouDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateMatou == 4) {
			matouDialogs.clear();
			matouDialogs.put(0, dialogOwner + "Laisse moi avec ma Lucie et vas embeter les autres ! \n");
			phrase = matouDialogs.get(dialogCounter);
			return phrase;
		}
		return null;

	}

	public String getName() {
		return this.nameNpc;
	}

	public Map<Integer, String> getNpcDialogs() {
		if (this.getName().equals("Joe")) {
			return this.joeDialogs;
		} else if (this.getName().equals("Jack")) {
			return jackDialogs;
		} else if (this.getName().equals("Matou")) {
			return matouDialogs;
		}
		return billyDialogs;
	}

	public void setDialogState(int i) {
		if (this.getName().equals("Billy")) {
			dialogStateBilly = i;
		} else if (this.getName().equals("Joe")) {
			dialogStateJoe = i;
		} else if (this.getName().equals("Matou")) {
			dialogStateMatou = i;
		} else if (this.getName().equals("Jack")) {
			dialogStateJack = i;
		}

	}

	public int getDialogState() {
		if (this.getName().equals("Billy")) {
			return dialogStateBilly;
		} else if (this.getName().equals("Joe")) {
			return dialogStateJoe;
		} else if (this.getName().equals("Matou")) {
			return dialogStateMatou;
		} else if (this.getName().equals("Jack")) {
			return dialogStateJack;
		}
		return 0;
	}

}

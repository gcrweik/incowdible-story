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
			billyDialogs.put(0, dialogOwner + "Hey, toi... viens par ici...\n");
			billyDialogs.put(1, dialogOwner + "T'es la nouvelle, c'est ca? Moi c'est Billy!\n");
			billyDialogs.put(2, dialogOwner + "... \n");
			billyDialogs.put(3, dialogOwner + "Pas très bavarde à ce que je vois...\n");
			billyDialogs.put(4, dialogOwner + "Mais ca m'arrange, comme ça j'ai pas à m'arreter\n pour t'ecouter.\n");
			billyDialogs.put(5, dialogOwner + "Aloooooors...par où commencer...\n");
			billyDialogs.put(6,
					dialogOwner + "Les personnes que tu dois connaître et les seules qui sont ici d’ailleurs,\n"
							+ " sont Old Joe, c’est le plus  ancien ici, une légende raconte que\n"
							+ "c’est lui qui a bâti cette prison de ses propres mains.\n");

			billyDialogs.put(7,
					dialogOwner + "Jack the Smuggler, peu importe ce que tu cherches, il te le trouvera !\n");

			billyDialogs.put(8, dialogOwner
					+ "Matou le Maton, le seul maton de cette prison, mais je pense que c’est parce que\n"
					+ " les dévelo... , hmmm, les directeurs de la prison,  n’ont pas eu le temps d’en employer d’autres, bref ,\n"
					+ " si Matou te pose une question sur sa matraque réponds lui simplement, “Jolie” ...”\n");
			billyDialogs.put(9,
					dialogOwner + "Maintenant bouge d'ici et va voir Old Joe, avant que Matou nous voit discuter!\n");

			phrase = billyDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateBilly == 1) {
			billyDialogs.clear();
			billyDialogs.put(0, dialogOwner + "T'es encore là toi? Va voir Old Joe avant qu'on s'fasse choper!\n");
			phrase = billyDialogs.get(dialogCounter);
			return phrase;
		}
		return null;

	}

	public String dialogJoe() {
		dialogOwner = "💬Joe: ";
		if (dialogStateJoe == 0) {
			joeDialogs.put(0, dialogOwner + "*Dors debout, canne à la main.*\n");
			joeDialogs.put(1, dialogOwner + "GERALDINE !!! C'est toi?\n");
			joeDialogs.put(2, dialogOwner + "Ah non... mais qui es-tu?\n");
			joeDialogs.put(3,
					dialogOwner + "Quoi que, t'embêtes pas à me repondre, je vais pas m'en rappeler de toute facon\n");
			joeDialogs.put(4, dialogOwner + "Tu sais quoi? Reviens me voir plus tard, si je suis reveillé..\n");

			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateJoe == 1) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "GERALDINE !!! GERALDINE !!!\n");
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
			matouDialogs.put(0, dialogOwner + "*Caresse sa matraque sensuellement de ses pattes*\n");
			matouDialogs.put(1, dialogOwner + "*Lève la tête et vous regarde*\n");
			matouDialogs.put(2, dialogOwner + "Oh ! t'es la nouvelle detenue, Lucie m'a parlé d'toi.\n"
			/* + " Lucie m'a parlé...\n" */);
			matouDialogs.put(3, dialogOwner + "Et c'est où qu'tu vas comme ça?\n");
			matouDialogs.put(4, dialogOwner + "*Caresse la lettre 'L' sur sa matraque*\n");
			matouDialogs.put(5, dialogOwner + "Ducoup tu veux faire un tour dans la salle de sport c'est ca?\n");
			matouDialogs.put(6, dialogOwner + "Ma p'tite Lucie et moi adorons les enigmes\n");
			matouDialogs.put(7, dialogOwner + "J'te laisse passer qu'si tu reponds bien,\n");
			matouDialogs.put(8,
					dialogOwner + "Tu t'trompes et... tu vas connaître la puissance d'ma charmante Lucie.\n");
			matouDialogs.put(9, dialogOwner + "Alors dis-moi, comment qu'elle est ma Lucie?\n");
			matouDialogs.put(10, "*Trois mots vous viennent à l'esprit : neuve, repugnante, jolie*\n");
			matouDialogs.put(11, "*Qu'allez-vous répondre?*\n");

			phrase = matouDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateMatou == 1) {
			matouDialogs.clear();
			matouDialogs.put(0, dialogOwner + "Allez! Vite! Fait ton choix!!\n");
			phrase = matouDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateMatou == 2) {
			matouDialogs.clear();
			matouDialogs.put(0, dialogOwner + "Faux ! J'vais t'montrer l'mauvais caractere d'ma Lucie...\n");
			phrase = matouDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateMatou == 3) {
			matouDialogs.clear();
			matouDialogs.put(0, dialogOwner + "Exactement ! Tu me comprends si bien ! \n");
			matouDialogs.put(1, dialogOwner + "Tu peux passer mon ami ! \n");
			phrase = matouDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateMatou == 4) {
			matouDialogs.clear();
			matouDialogs.put(0, dialogOwner + "J'ai assez perdu d'temps avec toi, laisse-moi profiter d'ma Lucie!\n");
			phrase = matouDialogs.get(dialogCounter);
			return phrase;
		}
		return null;

	}

	public String dialogJack() {
		if (dialogStateJack == 0) {
			jackDialogs.put(0, dialogOwner + "Allez.. tiens t'as mérité ça.");
			jackDialogs.put(1, dialogOwner + "D'ailleurs, si ça t'interesse Joe à un sécateur.");
		}
		if (dialogStateJack == 1) {
			jackDialogs.put(0, dialogOwner + "Allez.. tiens t'as mérité ça.");
			jackDialogs.put(1, dialogOwner + "D'ailleurs, si ça t'interesse Joe à un sécateur.");
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

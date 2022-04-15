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
		String sp = spaceAdder(3);
		if (dialogStateBilly == 0) {
			billyDialogs.put(0, dialogOwner + "Hey, toi.. viens par ici..\n");
			billyDialogs.put(1, dialogOwner + "T'es la nouvelle, c'est ca? Moi c'est Billy!\n");
			billyDialogs.put(2, dialogOwner + "... \n");
			billyDialogs.put(3, dialogOwner + "Pas très bavarde à ce que je vois...\n");
			billyDialogs.put(4,
					dialogOwner + "Mais ca m'arrange, comme ça j'ai pas à m'arreter\n" + sp + "pour t'ecouter.\n");
			billyDialogs.put(5, dialogOwner + "Aloooooors...par où commencer...\n");
			billyDialogs.put(6,
					dialogOwner + "Les personnes que tu dois connaître et les seules qui sont ici d’ailleurs, sont:\n");
			billyDialogs.put(7, dialogOwner + "Old Joe, c’est le plus  ancien ici, une légende raconte que\n" + sp
					+ "c’est lui qui a bâti cette prison de ses propres mains.\n");

			billyDialogs.put(8,
					dialogOwner + "Jack the Smuggler, peu importe ce que tu cherches, il te le trouvera !\n");

			billyDialogs.put(9, dialogOwner
					+ "Matou le Maton, le seul maton de cette prison, mais je pense que c’est parce que\n" + sp
					+ "les dévelo... ,*toussote* hum hum, les directeurs de la prison,  n’ont pas eu le temps d’en employer d’autres, bref ,\n"
					+ sp + "si Matou te pose une question sur sa matraque réponds lui simplement, “Jolie” ...”\n");
			billyDialogs.put(10,
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
		if (dialogStateBilly == 2) {
			billyDialogs.clear();
			billyDialogs.put(0, dialogOwner + "*Billy regarde ses papiers*\n");
			phrase = billyDialogs.get(dialogCounter);
			return phrase;
		}
		return null;

	}

	public String dialogJoe() {
		dialogOwner = "💬Joe: ";
		String sp = spaceAdder(5);
		if (dialogStateJoe == 0) {
			joeDialogs.put(0, dialogOwner + "*Dors debout, canne à la main.*\n");
			joeDialogs.put(1, dialogOwner + "Zzzzzzzz...\n");
			joeDialogs.put(2, dialogOwner + "GERALDINE !!! C'est toi?\n");
			joeDialogs.put(3, dialogOwner + "Ah non... mais qui es-tu?\n");
			joeDialogs.put(4,
					dialogOwner + "Quoi que, t'embêtes pas à me repondre, je vais pas m'en rappeler de toute facon\n");
			joeDialogs.put(5, dialogOwner + "Hmmmmm... je connais ce regard.. le regard de quelqu'un\n" + sp
					+ "qui rêve de liberté!\n");
			joeDialogs.put(6, dialogOwner + "Vas voir Jack à la salle de sport.\n");
			joeDialogs.put(7, dialogOwner + "Qui sait ce qu'il a à 't'offrir'. Mais fait attention à Matou..\n");
			joeDialogs.put(8, dialogOwner + "Reviens me voir plus tard, si je suis reveillé.\n");
			joeDialogs.put(9, dialogOwner + "Zzzzzzzzzz...\n");
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
			joeDialogs.put(0, dialogOwner + "Zzzzzzzzzzzzzzzz...\n");
			joeDialogs.put(1, dialogOwner + "Qui ose déranger mon sommeil? Oh, C'est toi.\n");
			joeDialogs.put(2,
					dialogOwner + "A te voir, on dirait que..\n" + sp
							+ "Que tu as reussi a passer à travers Matou et sa 'Lucie' ainsi que\n" + sp
							+ "Jack et son addiction aux cigarettes.\n");
			joeDialogs.put(3, dialogOwner + "Bon... vu que j'ai envie de dormir, allons droit au but.\n");
			joeDialogs.put(4, dialogOwner + "Je vais te poser deux questions.. et.. si tu reponds correctement au deux\n"
					+ sp + "mon sécateur sera à toi ainsi qu'un petit 'indice'!\n");
			joeDialogs.put(5, dialogOwner + "*Joe rigole pour la premiere fois*\n");
			joeDialogs.put(6, dialogOwner + "T'as le droit, a deux erreurs, à la troisieme...\n" + sp
					+ "J'appele Matou! et s'en est fini de toi!\n");
			joeDialogs.put(7, dialogOwner + "Je prends ton silence pour un oui, donc écoute attentivement.\n");

			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateJoe == 3) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "Combien de chaises y-a-t'il dans la salle de sport?\n");

			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateJoe == 4) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "Faux ! C'est ta première erreur.. !\n");
			joeDialogs.put(1, dialogOwner + "Essaye encore !\n");

			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}

		if (dialogStateJoe == 5) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "Faux ! T'as plus le droit à l'erreur !\n");
			joeDialogs.put(1, dialogOwner + "Choisi bien ta reponse...\n");

			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}

		if (dialogStateJoe == 6) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "Exactement!\n");
			joeDialogs.put(1, dialogOwner + "Prochaine question.\n");

			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}

		if (dialogStateJoe == 7) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "Combien y-a-t il de barbecues dans la cours exterieure?\n");

			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}

		if (dialogStateJoe == 8) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "Tu viens de te tromper une troisieme fois...\n");
			joeDialogs.put(1, dialogOwner + "MATOUUUUUU !!!\n");
			joeDialogs.put(2, dialogOwner + "Elle essaye de s'echapper !!\n");

			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateJoe == 9) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "C'est exactement ça !\n");
			joeDialogs.put(1, dialogOwner + "T'as merité ce secateur ainsi que ce petit indice...\n");
			joeDialogs.put(2,
					dialogOwner + "Si tu trouves une clé dans un trou..\n");
			joeDialogs.put(3, dialogOwner + "N'hesite pas d'y sauter,\n" + sp
					+ "ca te rameneras surement quelque part..\n");
			joeDialogs.put(4, dialogOwner + "*Il rigole encore*\n");
			joeDialogs.put(5, dialogOwner + "Aller, aller Marguerite.. bon courage à toi..\n");
			joeDialogs.put(6, dialogOwner + "*Il se rendort*\n");
			joeDialogs.put(7, "*Mais comment connait-il donc votre prenom?*\n");

			phrase = joeDialogs.get(dialogCounter);
			return phrase;
		}

		if (dialogStateJoe == 10) {
			joeDialogs.clear();
			joeDialogs.put(0, dialogOwner + "Zzzzzz....\n");
			joeDialogs.put(1, dialogOwner + "Geraldine... que tu me manques..\n");
			joeDialogs.put(2, dialogOwner + "Zzzzzz....\n");

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
			matouDialogs.put(2, dialogOwner + "Oh ! T'es la nouvelle detenue, Lucie m'a parlé d'toi.\n"
			/* + " Lucie m'a parlé...\n" */);
			matouDialogs.put(3, dialogOwner + "Et c'est où qu'tu vas comme ça?\n");
			matouDialogs.put(4, dialogOwner + "*Caresse la lettre 'L' sur sa matraque*\n");
			matouDialogs.put(5, dialogOwner + "Ducoup tu veux faire un tour dans la salle de sport c'est ca?\n");
			matouDialogs.put(6, dialogOwner + "Ma p'tite Lucie et moi adorons les enigmes.\n");
			matouDialogs.put(7, dialogOwner + "J'te laisse passer qu'si tu reponds bien.\n");
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
			matouDialogs.put(0, dialogOwner + "Allez! Vite! Fait ton choix !!\n");
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
		dialogOwner = "💬Jack: ";
		String sp = spaceAdder(5);
		if (dialogStateJack == 0) {
			jackDialogs.put(0, dialogOwner + "Hey.. pssssst.. hey!\n");
			jackDialogs.put(1, dialogOwner + "Tu veux du fromage?\n");
			jackDialogs.put(2, dialogOwner + "*Son nez bouge d'excitation*\n");
			jackDialogs.put(3, dialogOwner + ".....\n");
			jackDialogs.put(4, dialogOwner + "Non? C'est dommage...\n");
			jackDialogs.put(5, dialogOwner + "C'est pourtant de la bonne, tout droit venu du Maroc..\n");
			jackDialogs.put(6, dialogOwner + "Laisse moi deviner, c'est Old Joe qui t'envoie?\n");
			jackDialogs.put(7, dialogOwner + "*Remarque le regarde que vous jetez sur sa pelle*\n");
			jackDialogs.put(8, dialogOwner + "Tu la veux? Hmmmm....\n");
			jackDialogs.put(9,
					dialogOwner + "Ramene moi du fromage ouuu...\n" + sp + "un paquet de cigarettes !\n");
			jackDialogs.put(10, dialogOwner + "Et traine pas trop sinon tu peux dire au revoir à la pelle hehe !\n");

			phrase = jackDialogs.get(dialogCounter);
			return phrase;
		}

		if (dialogStateJack == 1) {
			jackDialogs.clear();
			jackDialogs.put(0, dialogOwner + "T'es encore la toi ? Grouille toi !!\n");
			jackDialogs.put(1, dialogOwner + "Sinon pas de pelle.\n");

			phrase = jackDialogs.get(dialogCounter);
			return phrase;

		}

		if (dialogStateJack == 2) {
			jackDialogs.clear();
			jackDialogs.put(0, dialogOwner + "Ohhh! Mes cigarettes !!\n");
			jackDialogs.put(1, dialogOwner + "*Allume une cigarette directement et tire dessus*\n");
			jackDialogs.put(2, dialogOwner + "*Son nez s'agite de plaisir*\n");
			jackDialogs.put(3, dialogOwner + "Allez... tiens, t'as mérité ça.\n");
			jackDialogs.put(4, dialogOwner + "Tu peux prendre ma pelle. Maintenant j'ai bien mieux, les cigarettes... !\n");
			jackDialogs.put(5, dialogOwner + "D'ailleurs, si ça t'interesse Joe à un sécateur.\n");
			jackDialogs.put(6, dialogOwner + "Je dis ca, je dis rien.\n");

			phrase = jackDialogs.get(dialogCounter);
			return phrase;
		}
		if (dialogStateJack == 3) {
			jackDialogs.clear();
			jackDialogs.put(0, dialogOwner + "Laisse-moi fumer en paix !\n");

			phrase = jackDialogs.get(dialogCounter);
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

	/**
	 * Une methode qui permet d'inserer certain nombres d'espaces en fonctions de
	 * celui qui parle et un parametre d'ajustement.
	 * 
	 * @param adj Permet d'ajuster un peu.
	 * @return Un String qui contient un certain nombre d'espace.
	 */
	public String spaceAdder(int adj) {
		int len = dialogOwner.length() + adj;
		String result = String.format("%-" + len + "s", "");

		return result;
	}

}

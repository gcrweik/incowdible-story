package game;

public class Main {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Introduction intro = new Introduction();
		
		Game jeu = new Game();
		GUI gui = new GUI(jeu);
		jeu.setGUI(gui);
;	}
}

package game;

/**
 * Une enumeration qui contient toutes les actions.
 * 
 * @author mohamed_hanouche
 * @author roman_tyzio
 * @version 1.0.0
 * 
 *
 */
public enum Action {
	/**
	 * Pour s'approcher de Billy.
	 */
	BILLY,
	/**
	 * Pour s'approcher de Jack.
	 */
	JACK,
	/**
	 * Pour s'approcher de Joe.
	 */
	JOE,
	/**
	 * Pour s'approcher de Matou.
	 */
	MATOU,
	/**
	 * Aller au pot de fleurs n1.
	 */
	P1,
	/**
	 * Aller au pot de fleurs n2.
	 */
	P2,
	/**
	 * Parler au personnages.
	 */
	PARLER,
	/**
	 * Prendre un objet.
	 */
	PRENDRE,
	/**
	 * Aller au grillage.
	 */
	GRILLAGE,
	/**
	 * Passer a travers le grillage a l'aide de secateur
	 */
	COUPER,
	/**
	 * Creuser a l'emplacement n1.
	 */
	T1,
	/**
	 * Creuser a l'emplacement n2.
	 */
	T2,
	/**
	 * Creuser a l'emplacement n3.
	 */
	T3,
	/**
	 * Creuser a l'emplacement n4.
	 */
	T4,
	/**
	 * Sauter dans le trou ou dans la trappe.
	 */
	SAUTER,
	/**
	 * Fuir la prison.
	 */
	FUIR,
	/**
	 * Aller au bureau du directeur.
	 */
	BUREAU;
}
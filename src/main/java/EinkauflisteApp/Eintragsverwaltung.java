package EinkauflisteApp;

import java.util.HashMap;
import java.util.Map;

public class Eintragsverwaltung {
	private static Eintragsverwaltung instance;
	private Map<String, Integer> eintraege = new HashMap<String, Integer>();
	

	private Eintragsverwaltung() {
	}

	/**
	 * Singleton Erzeuger
	 * 
	 * @return Instanz der Eintragsverwaltung
	 */
	public static Eintragsverwaltung getInstance() {
		if (Eintragsverwaltung.instance == null) {
			Eintragsverwaltung.instance = new Eintragsverwaltung();
		}
		return Eintragsverwaltung.instance;
	}

	/**
	 * Fügt einen Eintrag hinzu
	 * 
	 * @param bez   der Bezeichner
	 * @param menge die Menge
	 */
	public void eintragHinzu(String bez, int menge) {
		// Falls es den schon gibt, ändert er nur die Menge
		if (eintraege.containsKey(bez)) {
			int alteMenge = eintraege.get(bez);
			eintraege.remove(bez);
			eintraege.put(bez, menge + alteMenge);
		} else {
			eintraege.put(bez, menge);
		}
	}

	/**
	 * Editiert einen Eintrag
	 * 
	 * @param ein   Der betreffende Eintrag
	 * @param bez   der neue Bezeichner
	 * @param menge die neue Menge
	 */
	public void eintragEdit(String id, String bez, int menge) {
		eintraege.remove(id);
		eintraege.put(bez,menge);
	}

	/**
	 * Entfernt einen Eintrag
	 * 
	 * @param bez der Bezeichner der entfernt werden soll
	 */
	public void eintragEntfernen(String bez) {
		eintraege.remove(bez);

	}

	public Map<String, Integer> getEintraege() {
		return new HashMap<String, Integer>(eintraege);
	}

}

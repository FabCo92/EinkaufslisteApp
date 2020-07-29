package EinkauflisteApp;

import java.util.ArrayList;

public class Eintragsverwaltung {
	private static Eintragsverwaltung instance;
	private ArrayList<Eintraege> eintraege = new ArrayList<Eintraege>();
	

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
	public Eintraege eintragHinzu(String bez, int menge) {
		Eintraege ein = new Eintraege(bez,menge);
		eintraege.add(ein);
		return ein;
	}
		
		
		

	/**
	 * Editiert einen Eintrag
	 * 
	 * @param ein   Der betreffende Eintrag
	 * @param bez   der neue Bezeichner
	 * @param menge die neue Menge
	 */
	public void eintragEdit(Eintraege ein, String bez, int menge) {
		ein.setBezeichner(bez);
		ein.setMenge(menge);
	}

	/**
	 * Entfernt einen Eintrag
	 * 
	 * @param ein der Eintrag der entfernt werden soll
	 */
	public void eintragEntfernen(Eintraege ein) {
		if(eintraege.contains(ein)) eintraege.remove(ein);

	}

	public ArrayList<Eintraege> getEintraege() {
		return new ArrayList<Eintraege>(eintraege);
	}

}

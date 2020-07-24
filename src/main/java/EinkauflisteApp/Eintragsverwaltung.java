package EinkauflisteApp;

import java.util.HashSet;

public class Eintragsverwaltung {
	private static Eintragsverwaltung instance;
	private HashSet<Eintraege> eintraege;

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
	 * @param bez der Bezeichner
	 * @param menge die Menge
	 */
	public void eintragHinzu(String bez, int menge) {
		//Falls es den schon gibt, ändert er nur die Menge
		if (gibtEsSchon(bez)) {
			for (Eintraege ein : eintraege) {
				if (bez == ein.getBezeichner()) {
					int alteMenge = ein.getMenge();
					eintraege.remove(ein);
					Eintraege e = new Eintraege(bez, menge + alteMenge);
					eintraege.add(e);
				}
			}
		} else {
			Eintraege e = new Eintraege(bez, menge);
			eintraege.add(e);
		}

	}

	/**
	 * Editiert einen Eintrag

	 * @param ein Der betreffende Eintrag
	 * @param bez der neue Bezeichner
	 * @param menge die neue Menge
	 */
	public void eintragEdit(Eintraege ein, String bez, int menge) {
			eintraege.remove(ein);
			if(ein.getBezeichner() != bez) ein.setBezeichner(bez);
			if(ein.getMenge() != menge) ein.setMenge(menge);

	}
	
	/**
	 * Entfernt einen Eintrag
	 * 
	 * @param bez der Bezeichner der entfernt werden soll
	 */
	public void eintragEntfernen(String bez) {
		for (Eintraege ein : eintraege) {
			if (bez == ein.getBezeichner()) {
				eintraege.remove(ein);
			}
		}

	}

	/**
	 * Helferfunktion um zu schauen, ob es einen Eintrag mit dem Bezeichner schon gibt
	 * @param bez der Bezeichner
	 * @return true, wenn es ihn gibt
	 */
	public boolean gibtEsSchon(String bez) {
		for (Eintraege ein : eintraege) {
			if (bez == ein.getBezeichner())
				return true;
		}
		return false;
	}
}

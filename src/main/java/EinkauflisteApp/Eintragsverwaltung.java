package EinkauflisteApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Eintragsverwaltung {
	private static Eintragsverwaltung instance;
	private ArrayList<Eintraege> eintraege = new ArrayList<Eintraege>();

	static final String SPEICHER_DATEI_PFAD = "./data.ser";

	@SuppressWarnings("unchecked")
	private Eintragsverwaltung() {
		try {
			FileInputStream fileIn = new FileInputStream(SPEICHER_DATEI_PFAD);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			eintraege = (ArrayList<Eintraege>) in.readObject();
			in.close();
		} catch (FileNotFoundException ex) {
			System.err.println(
					"Keine Speicherdatei gefunden. Beim ersten Start der Applikation ist dies erwartetes Verhalten.");

			eintraege = new ArrayList<Eintraege>();
		} catch (IOException e) {
			System.err.println("Fehler beim Laden der Speicherdatei: ");
			e.printStackTrace();
			System.exit(-1);
		} catch (ClassNotFoundException e) {
			System.err.println("Fehler beim Laden der Speicherdatei: ");
			e.printStackTrace();
			System.exit(-2);
		}

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

	public void speichern() {
		try {
			FileOutputStream fileOut = new FileOutputStream(SPEICHER_DATEI_PFAD);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(eintraege);
			out.close();
		} catch (FileNotFoundException e) {
			System.err.println("Fehler beim Schreiben der Speicherdatei: ");
			e.printStackTrace();
			System.exit(-3);
		} catch (IOException e) {
			System.err.println("Fehler beim Schreiben der Speicherdatei: ");
			e.printStackTrace();
			System.exit(-4);
		}
	}

	/**
	 * Fügt einen Eintrag hinzu
	 * 
	 * @param bez   der Bezeichner
	 * @param menge die Menge
	 */
	public Eintraege eintragHinzu(String bez, String menge) {
		Eintraege ein = new Eintraege(bez, menge);
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
	public void eintragEdit(Eintraege ein, String bez, String menge) {
		ein.setBezeichner(bez);
		ein.setMenge(menge);
	}

	/**
	 * Entfernt einen Eintrag
	 * 
	 * @param ein der Eintrag der entfernt werden soll
	 */
	public void eintragEntfernen(Eintraege ein) {
		if (eintraege.contains(ein))
			eintraege.remove(ein);

	}

	public ArrayList<Eintraege> getEintraege() {
		return new ArrayList<Eintraege>(eintraege);
	}

	public void loescheEintraege() {
		eintraege.clear();
		
	}

}

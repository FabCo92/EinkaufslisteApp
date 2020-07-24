package EinkauflisteApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EintragsverwaltungTest {
	private Eintragsverwaltung ev;
	@Before
	public void setUp() throws Exception {
		ev = Eintragsverwaltung.getInstance();
		ev.eintragHinzu("Eier", 2);
	}

	@Test
	public void testGetInstance() {
		assertNotNull(ev);
	}

	@Test
	public void testEintragHinzu() {
		int before = ev.getEintraege().size();
		ev.eintragHinzu("Butter", 2);
		int after = ev.getEintraege().size();
		assertEquals(before+1,after);
	}

	@Test
	public void testEintragEdit() {
		ev.eintragEdit("Eier", "Eier", 3);
		int value = ev.getEintraege().get("Eier");
		assertEquals(value,3);
		
			
	}

	@Test
	public void testEintragEntfernen() {
		int before = ev.getEintraege().size();
		ev.eintragEntfernen("Eier");
		int after = ev.getEintraege().size();
		assertEquals(before-1,after);
		
	}


}

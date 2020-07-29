package EinkauflisteApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EintragsverwaltungTest {
	private Eintragsverwaltung ev;
	Eintraege ein;
	@Before
	public void setUp() throws Exception {
		ev = Eintragsverwaltung.getInstance();
		ein = ev.eintragHinzu("Eier", "2");
		
	}

	@Test
	public void testGetInstance() {
		assertNotNull(ev);
	}

	@Test
	public void testEintragHinzu() {
		int before = ev.getEintraege().size();
		ev.eintragHinzu("Butter", "2");
		int after = ev.getEintraege().size();
		assertEquals(before+1,after);
	}

	@Test
	public void testEintragEdit() {
		ev.eintragEdit(ein, "Eier", "3");
		String value = ein.getMenge();
		assertEquals(value,"3");
		
			
	}

	@Test
	public void testEintragEntfernen() {
		int before = ev.getEintraege().size();
		ev.eintragEntfernen(ein);
		int after = ev.getEintraege().size();
		assertEquals(after,before-1);
		
	}
	
	@Test
	public void testEintragEntfernenExistiertNicht() {
		int before = ev.getEintraege().size();
		Eintraege ein2 = new Eintraege("String","5");
		ev.eintragEntfernen(ein2);
		int after = ev.getEintraege().size();
		assertEquals(before,after);
	}


}

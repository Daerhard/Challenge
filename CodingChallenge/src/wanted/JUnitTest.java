package wanted;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTest {

	@org.junit.jupiter.api.Test
	void test() {
		System.out.println("");
		
		APIaccess testAPIaccess = new APIaccess();
		
		//Methodenaufruf der zu testenden Klasse		
		String testCriminal = testAPIaccess.getCriminal();
		String testMugshotURL = testAPIaccess.getMugshotUrl();
		//String Variable für den Vergleich
		String empty = "";

		assertNotEquals(testCriminal, empty);
		assertNotEquals(testMugshotURL, empty);
	}

}


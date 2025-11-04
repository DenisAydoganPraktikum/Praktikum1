package export;

import java.io.IOException;

public abstract class Creator {
	
	// Hier wird die Fabrik-Methode deklariert
	// Sie gibt ein Objekt vom abstrakten Typ Product zurück
	public abstract Product factoryMethod() throws IOException;
	
	// Diese Hilfsmethode steuert den gesamten Exportprozess
	// Sie wird im Model aufgerufen und ersetzt die Kommentare
	public void schreibeInDatei(Object object) throws IOException {
		// Hier wird das konkrete Produkt erzeugt
		Product product = factoryMethod(); 
		// Hier wird die Logik des Produkts benutzt
		product.fuegelnDateiHinzu(object);
		// Hier wird die Datei geschlossen
		product.schliesseDatei();
	}
}
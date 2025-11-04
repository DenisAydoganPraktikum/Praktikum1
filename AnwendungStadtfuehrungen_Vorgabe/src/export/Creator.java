package export;

import java.io.IOException;

public abstract class Creator {
	
	// Hier wird die Fabrik-Methode deklariert
	// Sie gibt ein Objekt vom abstrakten Typ Product zurück
	public abstract Product factoryMethod() throws IOException;
	
	
	// Die folgende Hilfsmethode wurde entfernt, da sie nicht
    // Teil der Aufgabenstellung im PDF war und die Logik
    // stattdessen in das BahnhofModel verschoben wurde.
	
	/*
	public void schreibeInDatei(Object object) throws IOException {
		// Hier wird das konkrete Produkt erzeugt
		Product product = factoryMethod(); 
		// Hier wird die Logik des Produkts benutzt
		product.fuegelnDateiHinzu(object);
		// Hier wird die Datei geschlossen
		product.schliesseDatei();
	}
	*/
    
}
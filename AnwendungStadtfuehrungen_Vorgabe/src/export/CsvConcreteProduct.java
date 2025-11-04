package export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import business.Bahnhof;

public class CsvConcreteProduct extends Product {
	
	private BufferedWriter writer;
	private static final String DATEINAME = "BahnhoefeAusgabe.csv";

	// Der BufferedWriter wird im Konstruktor initialisiert
	public CsvConcreteProduct() throws IOException {
		this.writer = new BufferedWriter(new FileWriter(DATEINAME, true));
	}

	@Override
	public void fuegelnDateiHinzu(Object object) throws IOException {
		// Hier erfolgt die spezifische Formatierung für den Bahnhof in CSV
		if (object instanceof Bahnhof) {
			Bahnhof bahnhof = (Bahnhof) object;
			this.writer.write(bahnhof.gibBahnhofZurueckFuerCsv());
			this.writer.newLine(); 
		} else {
			// Dies ist ein Platzhalter falls das Objekt nicht der erwartete Typ ist
			throw new IllegalArgumentException("Das Objekt ist kein Bahnhof.");
		}
	}

	@Override
	public void schliesseDatei() throws IOException {
		// Schließt die Datei nach dem Schreiben
		this.writer.close();
	}
}
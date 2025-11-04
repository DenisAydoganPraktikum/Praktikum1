package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// Importiert die UML-Namen
import export.Creator;
import export.CsvConcreteCreator;
import export.TxtConcreteCreator; 
 
public class BahnhofModel {
	
	private Bahnhof bahnhof;
	
	public BahnhofModel()
	{
	}
	
	public String getUeberschrift()
	{
		 // Hier wird der Fenstertitel auf die korrigierte Schreibweise gesetzt
		 return "Verwaltung von Bahnhoefen"; 
	} 
	
	// Implementierung für das Schreiben in CSV unter Verwendung der Fabrik-Methode
	public void schreibeBahnhofInCsvDatei() throws IOException
	{
		if (this.bahnhof == null) {
            throw new IOException("Kein Bahnhof zum Speichern vorhanden.");
        }
		
        // Kreieren eines Creator-Objekts und Abspeicherung mit Hilfe
        // einer Variablen vom Typ der entsprechenden abstrakten
        // Creator-Klasse.
		Creator creator = new CsvConcreteCreator();
		
        // Kreieren eines Product-Objekts mit Hilfe der Factory-
        // Methode des Creator-Objekts und Abspeicherung mit Hilfe
        // einer Variablen vom Typ der entsprechenden abstrakten
        // Product-Klasse.
        // Der Creator übernimmt die Erstellung und Nutzung des Product-Objekts
		creator.schreibeInDatei(this.bahnhof);
	}
	
	// Implementierung für das Schreiben in TXT unter Verwendung der Fabrik-Methode
	public void schreibeBahnhofInTxtDatei() throws IOException
	{
		if (this.bahnhof == null) {
            throw new IOException("Kein Bahnhof zum Speichern vorhanden.");
        }
		
        // Hier wird die Fabrik für den TXT-Export verwendet
		Creator creator = new TxtConcreteCreator();
		creator.schreibeInDatei(this.bahnhof);
	}
	
	// Implementierung für das Lesen aus der CSV-Datei
	public void leseBahnhofAusCsvDatei() throws IOException, NumberFormatException {
        BufferedReader ein = new BufferedReader(new FileReader("Bahnhof.csv"));
        String zeileStr = ein.readLine();
        if (zeileStr == null) {
            ein.close();
            throw new IOException("CSV-Datei ist leer.");
        }
        
        String[] zeile = zeileStr.split(";");
        if (zeile.length < 5) {
            ein.close();
            throw new IOException("CSV-Datei hat ein ungültiges Format.");
        }
        
        String name = zeile[0];
        String ort = zeile[1];
        int anzahlGleise = Integer.parseInt(zeile[2]); 
        int letzteRenovierung = Integer.parseInt(zeile[3]); 
        String[] zugarten = zeile[4].split("_"); 
        
        this.bahnhof = new Bahnhof(name, ort, anzahlGleise, letzteRenovierung, zugarten);
        ein.close();
    }
	
    // Implementierung für das Lesen aus der TXT-Datei
    public void leseBahnhofAusTxtDatei() throws IOException, NumberFormatException {
        BufferedReader ein = new BufferedReader(new FileReader("BahnhoefeAusgabe.txt")); 
        String line;
        
        String name = null;
        String ort = null;
        String anzahlGleiseStr = null;
        String letzteRenovierungStr = null;
        String zugartenStr = null;
        
        while ((line = ein.readLine()) != null) {
            if (line.trim().isEmpty() || line.contains("---")) {
                continue; 
            }
            if (line.startsWith("Name des Bahnhofs: ")) {
                name = line.substring("Name des Bahnhofs: ".length()).trim();
            } else if (line.startsWith("Ort des Bahnhofs: ")) {
                ort = line.substring("Ort des Bahnhofs: ".length()).trim();
            } else if (line.startsWith("Anzahl Gleise: ")) {
                anzahlGleiseStr = line.substring("Anzahl Gleise: ".length()).trim();
            } else if (line.startsWith("Letzte Renovierung: ")) {
                letzteRenovierungStr = line.substring("Letzte Renovierung: ".length()).trim();
            } else if (line.startsWith("Zugarten: ")) {
                zugartenStr = line.substring("Zugarten: ".length()).trim();
            }
        }
        ein.close();
        if (name == null || ort == null || anzahlGleiseStr == null || letzteRenovierungStr == null || zugartenStr == null) {
             throw new IOException("TXT-Datei ist unvollständig oder ungültig formatiert. Fehlende Felder.");
        }
        int anzahlGleise = Integer.parseInt(anzahlGleiseStr);
        int letzteRenovierung = Integer.parseInt(letzteRenovierungStr);
        String[] zugarten;
        if (zugartenStr.isEmpty()) { zugarten = new String[]{}; } else { zugarten = zugartenStr.split(" "); }
        this.bahnhof = new Bahnhof(name, ort, anzahlGleise, letzteRenovierung, zugarten);
    }
	
	public Bahnhof getBahnhof()
	{
		return bahnhof;
	}
	
	public void setBahnhof(Bahnhof bahnhof)
	{
		this.bahnhof = bahnhof;
	}
}
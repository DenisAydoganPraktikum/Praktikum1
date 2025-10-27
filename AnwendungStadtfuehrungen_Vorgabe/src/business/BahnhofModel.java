package business;

import java.io.BufferedReader; // Hinzugefügt
import java.io.BufferedWriter;
import java.io.FileReader; // Hinzugefügt
import java.io.FileWriter;
import java.io.IOException;
 
public class BahnhofModel {
	
	private Bahnhof bahnhof;
	
	public BahnhofModel()
	{
	}
	
	public String getUeberschrift()
	{
		 return "Verwaltung von Bahnhöfen";
	} 
	
	// Implementierung für das Schreiben in CSV
	public void schreibeBahnhofInCsvDatei() throws IOException
	{
		if (this.bahnhof == null) {
            throw new IOException("Kein Bahnhof zum Speichern vorhanden.");
        }
		// Schreibt in "BahnhoefeAusgabe.csv" (wie in BahnhoefeAnwendungssystem.java)
		// true = Append-Modus (fügt hinzu)
		BufferedWriter aus = new BufferedWriter(new FileWriter("BahnhoefeAusgabe.csv", true));
		// Verwendet die spezielle CSV-Methode aus Bahnhof.java
		aus.write(this.getBahnhof().gibBahnhofZurueckFuerCsv());
		aus.newLine(); // Fügt einen Zeilenumbruch nach dem Eintrag hinzu
		aus.close();
	}
	
	// Implementierung für das Lesen aus CSV
	public void leseBahnhofAusCsvDatei() throws IOException, NumberFormatException {
		// Liest aus "Bahnhof.csv" (wie in BahnhoefeAnwendungssystem.java)
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
        
        // Format erwartet: Name;Ort;AnzahlGleise;LetzteRenovierung;Zugarten(mit _)
        String name = zeile[0];
        String ort = zeile[1];
        int anzahlGleise = Integer.parseInt(zeile[2]); // Kann NumberFormatException werfen
        int letzteRenovierung = Integer.parseInt(zeile[3]); // Kann NumberFormatException werfen
        String[] zugarten = zeile[4].split("_"); // Trennt Zugarten am Unterstrich
        
        // Erstellt und setzt den neuen Bahnhof
        this.bahnhof = new Bahnhof(name, ort, anzahlGleise, letzteRenovierung, zugarten);
        ein.close();
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
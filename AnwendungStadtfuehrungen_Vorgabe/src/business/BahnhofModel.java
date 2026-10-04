package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BahnhofModel {
	
	private Bahnhof bahnhof;
	
	public BahnhofModel() {
	}
	
	public String getUeberschrift() {
		return "Verwaltung von Bahnhoefen"; 
	} 
	
	// CSV-Import: Liest die CSV-Datei und erzeugt ein Bahnhof-Objekt
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
	
	// TXT-Import: Liest die TXT-Datei und erzeugt ein Bahnhof-Objekt
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
		if (zugartenStr.isEmpty()) { 
			zugarten = new String[]{}; 
		} else { 
			zugarten = zugartenStr.split(" "); 
		}
		
		this.bahnhof = new Bahnhof(name, ort, anzahlGleise, letzteRenovierung, zugarten);
	}
	
	// CSV-Export: Schreibt den Bahnhof in eine CSV-Datei
	public void schreibeBahnhofInCsvDatei() throws IOException {
		if (this.bahnhof == null) {
			throw new IOException("Kein Bahnhof zum Speichern vorhanden.");
		}
		
		BufferedWriter aus = new BufferedWriter(new FileWriter("BahnhoefeAusgabe.csv", true));
		aus.write(bahnhof.gibBahnhofZurueckFuerCsv());
		aus.newLine(); 
		aus.close();
	}
	
	// TXT-Export: Schreibt den Bahnhof in eine TXT-Datei
	public void schreibeBahnhofInTxtDatei() throws IOException {
		if (this.bahnhof == null) {
			throw new IOException("Kein Bahnhof zum Speichern vorhanden.");
		}
		
		BufferedWriter aus = new BufferedWriter(new FileWriter("BahnhoefeAusgabe.txt", true));
		
		aus.write("--- Daten des Bahnhofs ---");
		aus.newLine();
		aus.write("Name des Bahnhofs: " + bahnhof.getName());
		aus.newLine();
		aus.write("Ort des Bahnhofs: " + bahnhof.getOrt());
		aus.newLine();
		aus.write("Anzahl Gleise: " + bahnhof.getAnzahlGleise());
		aus.newLine();
		aus.write("Letzte Renovierung: " + bahnhof.getLetzteRenovierung());
		aus.newLine();
		aus.write("Zugarten: " + bahnhof.getZugartenAlsString(' '));
		aus.newLine();
		aus.write("--------------------------");
		aus.newLine();
		aus.close();
	}
	
	public Bahnhof getBahnhof() {
		return bahnhof;
	}
	
	public void setBahnhof(Bahnhof bahnhof) {
		this.bahnhof = bahnhof;
	}
}

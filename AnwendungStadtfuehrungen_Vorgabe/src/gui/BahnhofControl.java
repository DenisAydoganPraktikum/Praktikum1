package gui;

import java.io.IOException;
import business.Bahnhof;
import business.BahnhofModel;
import javafx.stage.Stage; 
import ownUtil.PlausiException;

public class BahnhofControl {

	private BahnhofView view;
	private BahnhofModel model;
	
    // Hier wird der Control initialisiert
    public BahnhofControl(Stage primaryStage)
	{
		this.model = new BahnhofModel();
		this.view = new BahnhofView(this, primaryStage, model);
	}
	
    // Hier werden die Eingaben von der View verarbeitet
    public void nehmeBahnhofAuf(String name, String ort, String anzahlGleiseStr, String letzteRenovierungStr, String zugartenStr)
    {
    	try {
    		// Hier wird die formale Prüfung durchgeführt
    		if (name == null || name.isEmpty()) {
                throw new PlausiException(PlausiException.FORMAL, "Name");
            }
            // Hier wird die formale Prüfung fortgesetzt
            if (ort == null || ort.isEmpty()) {
                throw new PlausiException(PlausiException.FORMAL, "Ort");
            }
            // Hier wird die formale Prüfung fortgesetzt
            if (anzahlGleiseStr == null || anzahlGleiseStr.isEmpty()) {
                throw new PlausiException(PlausiException.FORMAL, "Anzahl Gleise");
            }
            // Hier wird die formale Prüfung fortgesetzt
            if (letzteRenovierungStr == null || letzteRenovierungStr.isEmpty()) {
                throw new PlausiException(PlausiException.FORMAL, "Letzte Renovierung");
            }
            // Hier wird die formale Prüfung fortgesetzt
            if (zugartenStr == null || zugartenStr.isEmpty()) {
                throw new PlausiException(PlausiException.FORMAL, "Zugarten");
            }

            int anzahlGleise;
            // Hier wird die Umwandlung in eine Zahl geprüft
            try {
                anzahlGleise = Integer.parseInt(anzahlGleiseStr);
            } catch (NumberFormatException e) {
                throw new PlausiException(PlausiException.INHALTLICH, "Anzahl Gleise muss eine Zahl sein");
            }
            
            int letzteRenovierung;
            // Hier wird die Umwandlung in eine Zahl geprüft
            try {
                letzteRenovierung = Integer.parseInt(letzteRenovierungStr);
            } catch (NumberFormatException e) {
                throw new PlausiException(PlausiException.INHALTLICH, "Letzte Renovierung muss eine Zahl sein");
            }
            
            // Hier wird der Zugarten-String in ein Array aufgeteilt
            String[] zugarten = zugartenStr.split(";"); 
    		
    		// Hier wird das Model mit dem neuen Bahnhof aktualisiert
			model.setBahnhof(new Bahnhof(name, ort, anzahlGleise, letzteRenovierung, zugarten));
			// Hier wird ein Informationsfenster angezeigt
			view.zeigeInformationsfensterAn("Der Bahnhof wurde aufgenommen!");
			
    	} catch (PlausiException e) {
    		// Hier wird die PlausiException an die View zur Anzeige übergeben
    		view.zeigeFehlermeldungsfensterAn(e.getPlausiTyp() + "r Fehler", e.getMessage());
    	} catch (Exception e) {
    		// Hier wird ein allgemeiner Fehler an die View übergeben
    		view.zeigeFehlermeldungsfensterAn("Allgemeiner Fehler", "Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
    	}
    }
    
    // Hier wird der Export gesteuert
    void schreibeBahnhofInDatei(String typ) throws IOException
    {
    	 try{
    		 // Hier wird geprüft ob ein Bahnhof zum Speichern vorhanden ist
    		 if (model.getBahnhof() == null) {
                 view.zeigeInformationsfensterAn("Es wurde noch kein Bahnhof aufgenommen der gespeichert werden könnte.");
                 return;
             }
    		 
    		 // Hier wird die CSV-Logik ausgeführt
    		 if("csv".equals(typ))
    		 {
    			 model.schreibeBahnhofInCsvDatei();
    			 view.zeigeInformationsfensterAn("Der Bahnhof wurde erfolgreich in BahnhoefeAusgabe.csv gespeichert.");
    		 }
    		 // Hier wird die TXT-Logik ausgeführt
    		 else if ("txt".equals(typ)) 
    	 	 {
    			 model.schreibeBahnhofInTxtDatei();
    			 view.zeigeInformationsfensterAn("Der Bahnhof wurde erfolgreich in BahnhoefeAusgabe.txt gespeichert.");
    	 	 }
    		 // Hier wird ein Platzhalter für nicht implementierte Exporte ausgegeben
    		 else 
    	 	 {
    			 view.zeigeInformationsfensterAn(typ + "-Export ist nicht implementiert!");
    	 	 }
    	 }
    	 catch(IOException exc) {
             view.zeigeFehlermeldungsfensterAn("Speicherfehler", "Fehler beim Speichern der Datei: " + exc.getMessage());
         }
    	 catch(Exception exc)
    	 {
    		 view.zeigeFehlermeldungsfensterAn("Allgemeiner Fehler", "Unbekannter Fehler beim Speichern: " + exc.getMessage());
    	 }
    }
   
    // Hier wird der Import gesteuert
    public void leseBahnhofAusDatei(String typ) {
        try {
            // Hier wird die CSV-Import-Logik ausgeführt
            if ("csv".equals(typ)) {
                model.leseBahnhofAusCsvDatei();
                view.zeigeInformationsfensterAn("Bahnhof erfolgreich aus Bahnhof.csv importiert.");
                // Hier wird die Anzeige nach dem Import aktualisiert
                view.zeigeBahnhofAn(); 
            } 
            // Hier wird die TXT-Import-Logik ausgeführt
            else if ("txt".equals(typ)) { 
            	model.leseBahnhofAusTxtDatei();
                view.zeigeInformationsfensterAn("Bahnhof erfolgreich aus BahnhoefeAusgabe.txt importiert.");
                // Hier wird die Anzeige nach dem Import aktualisiert
                view.zeigeBahnhofAn();
            } 
            // Hier wird ein Platzhalter für nicht implementierte Imports ausgegeben
            else {
                view.zeigeInformationsfensterAn(typ + "-Import ist noch nicht implementiert!");
            }
        } catch (IOException exc) {
            view.zeigeFehlermeldungsfensterAn("Importfehler", "Fehler beim Lesen der Datei: " + exc.getMessage());
        } catch (NumberFormatException exc) {
             view.zeigeFehlermeldungsfensterAn("Importfehler", "Fehler im Dateiformat Ungültige Zahl gefunden.");
        } catch (Exception exc) {
            view.zeigeFehlermeldungsfensterAn("Allgemeiner Fehler", "Unbekannter Fehler beim Importieren: " + exc.getMessage());
        }
    }
}
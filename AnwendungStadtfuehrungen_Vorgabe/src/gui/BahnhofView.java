package gui;

import java.io.IOException;
import business.BahnhofModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*; 
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;

public class BahnhofView {
	
	private BahnhofControl control;
	private BahnhofModel model;
	
	// Hier werden die Attribute der grafischen Oberflaeche deklariert
    private Pane pane     				= new  Pane();
    private Label lblEingabe    	 	= new Label("Eingabe");
    private Label lblAnzeige   	 	    = new Label("Anzeige");
    private Label lblName 				= new Label("Name:");
    private Label lblOrt   		 		= new Label("Ort:");
    private Label lblAnzahlGleise  	 	= new Label("Anzahl Gleise:");
    private Label lblLetzteRenovierung   = new Label("Letzte Renovierung:");
    private Label lblZugarten  			= new Label("Zugarten mit ;:");
    private TextField txtName 	 		= new TextField();
    private TextField txtOrt			= new TextField();
    private TextField txtAnzahlGleise	= new TextField();
    private TextField txtLetzteRenovierung = new TextField();
    private TextField txtZugarten	 	= new TextField();
    private TextArea txtAnzeige  		= new TextArea();
    private Button btnEingabe 		 	= new Button("Eingabe");
    private Button btnAnzeige 		 	= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  	= new MenuBar();
    private Menu mnDatei             	= new Menu("Datei");
    
    private MenuItem mnItmCsvImport 	= new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport 	= new MenuItem("txt-Import");
    private MenuItem mnItmCsvExport 	= new MenuItem("csv-Export");
    private MenuItem mnItmTxtExport 	= new MenuItem("txt-Export");
	
    // Hier wird die View initialisiert
    public BahnhofView(BahnhofControl control, Stage primaryStage, BahnhofModel model)
    {
    	this.control = control;
    	this.model = model;
    	
    	Scene scene = new Scene(this.pane, 700, 340); 
    	primaryStage.setScene(scene);
        // Hier wird der Fenstertitel aus dem Model abgerufen
    	primaryStage.setTitle(this.model.getUeberschrift());
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }
    
    // Hier werden die Komponenten positioniert und zur Oberfläche hinzugefügt
    private void initKomponenten(){
    	// Hier werden Labels und deren Positionen gesetzt
       	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24);
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400); 
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblName.setLayoutX(20);
    	lblName.setLayoutY(90);
    	lblOrt.setLayoutX(20);
    	lblOrt.setLayoutY(130);
    	lblAnzahlGleise.setLayoutX(20);
    	lblAnzahlGleise.setLayoutY(170);
    	lblLetzteRenovierung.setLayoutX(20);
    	lblLetzteRenovierung.setLayoutY(210);
    	lblZugarten.setLayoutX(20);
    	lblZugarten.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblName, lblOrt, lblAnzahlGleise,
       		lblLetzteRenovierung, lblZugarten);
    
    	// Hier werden Textfelder und deren Positionen gesetzt
     	txtName.setLayoutX(170); 
    	txtName.setLayoutY(90);
    	txtName.setPrefWidth(200);
    	txtOrt.setLayoutX(170);
    	txtOrt.setLayoutY(130);
    	txtOrt.setPrefWidth(200);
    	txtAnzahlGleise.setLayoutX(170);
    	txtAnzahlGleise.setLayoutY(170);
    	txtAnzahlGleise.setPrefWidth(200);
      	txtLetzteRenovierung.setLayoutX(170);
    	txtLetzteRenovierung.setLayoutY(210);
    	txtLetzteRenovierung.setPrefWidth(200);
    	txtZugarten.setLayoutX(170);
    	txtZugarten.setLayoutY(250);
    	txtZugarten.setPrefWidth(200);
     	pane.getChildren().addAll( 
     		txtName, txtOrt, txtAnzahlGleise,
     		txtLetzteRenovierung, txtZugarten);
     	
        // Hier wird der Textbereich zur Anzeige initialisiert
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400); 
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Hier werden Buttons hinzugefügt
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400); 
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Hier wird das Menü initialisiert
   	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
        
        // Hier wird die Liste der Menü-Items vor dem Hinzufügen gelöscht
        this.mnDatei.getItems().clear(); 
        
        // Hier werden die Imports und Exports zum Menü hinzugefügt
  	    this.mnDatei.getItems().add(mnItmCsvImport); 
  	    this.mnDatei.getItems().add(mnItmTxtImport); 
  	    this.mnDatei.getItems().add(new SeparatorMenuItem()); 
  	    this.mnDatei.getItems().add(mnItmCsvExport);
  	    this.mnDatei.getItems().add(mnItmTxtExport);
 	    
        pane.getChildren().add(mnbrMenuLeiste);
   }
    
    // Hier werden die Listener für die Interaktion initialisiert
    private void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Hier wird die Eingabe an den Control übergeben
        	    control.nehmeBahnhofAuf(
        	    	txtName.getText(), 
        	    	txtOrt.getText(),
        	    	txtAnzahlGleise.getText(),
        	    	txtLetzteRenovierung.getText(),
        	    	txtZugarten.getText()
        	    );
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
                // Hier wird die Methode zur Anzeige des Bahnhofs aufgerufen
	    		zeigeBahnhofAn();
	        } 
   	    });
	    
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
                // Hier wird der CSV-Import beim Control angefordert
	       	 	control.leseBahnhofAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
                // Hier wird der TXT-Import beim Control angefordert
		    	control.leseBahnhofAusDatei("txt");
		    }
    	});
    	
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
	    	 @Override
	    	 public void handle(ActionEvent e) {
                // Hier wird der CSV-Export angefordert
	    		 schreibeBahnhofInDatei("csv");
	    	 }
	    });
    	 mnItmTxtExport.setOnAction(new EventHandler<ActionEvent>() {
	    	 @Override
	    	 public void handle(ActionEvent e) {
                // Hier wird der TXT-Export angefordert
	    		 schreibeBahnhofInDatei("txt");
	    	 }
	    });
    }
    
    // Hier wird der aktuell gespeicherte Bahnhof angezeigt
    public void zeigeBahnhofAn(){
    	if(model.getBahnhof() != null){
    		txtAnzeige.setText(
                // Hier wird der Bahnhof aus dem Model geholt und formatiert
    			model.getBahnhof().gibBahnhofZurueck(' ')); 
    	}
    	else{
            // Hier wird eine Meldung ausgegeben wenn kein Bahnhof gespeichert ist
    		zeigeInformationsfensterAn("Bisher wurde kein Bahnhof aufgenommen!");
    	}
    }
    
    // Hier wird ein Informationsfenster angezeigt
    void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    // Hier wird ein Fehlermeldungsfenster angezeigt
    void zeigeFehlermeldungsfensterAn(String fehlertyp, String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	fehlertyp, meldung).zeigeMeldungsfensterAn(); 
    }
    
    // Hier wird der Export-Aufruf an den Control delegiert
    private void schreibeBahnhofInDatei(String typ) {
    	try {
			control.schreibeBahnhofInDatei(typ);
		} catch (IOException e) {
            // Hier wird ein Fehler beim Schreiben der Datei an die View übergeben
			zeigeFehlermeldungsfensterAn("IOException", "Fehler beim Schreiben der Datei: " + e.getMessage());
		}
    }
}
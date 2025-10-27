package gui;

import java.io.IOException;
import business.BahnhofModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*; // Importiert
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;

public class BahnhofView {
	
	private BahnhofControl control;
	private BahnhofModel model;
	
	//Anfang Attribute der grafischen Oberflaeche
    private Pane pane     				= new  Pane();
    private Label lblEingabe    	 	= new Label("Eingabe");
    private Label lblAnzeige   	 	    = new Label("Anzeige");
    private Label lblName 				= new Label("Name:");
    //  Start: Korrigierte Labels und Felder 
    private Label lblOrt   		 		= new Label("Ort:");
    private Label lblAnzahlGleise  	 	= new Label("Anzahl Gleise:");
    private Label lblLetzteRenovierung   = new Label("Letzte Renovierung:");
    private Label lblZugarten  			= new Label("Zugarten (mit ;):");
    private TextField txtName 	 		= new TextField();
    private TextField txtOrt			= new TextField();
    private TextField txtAnzahlGleise	= new TextField();
    private TextField txtLetzteRenovierung = new TextField();
    private TextField txtZugarten	 	= new TextField();
    // Ende: Korrektur 
    private TextArea txtAnzeige  		= new TextArea();
    private Button btnEingabe 		 	= new Button("Eingabe");
    private Button btnAnzeige 		 	= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  	= new MenuBar();
    private Menu mnDatei             	= new Menu("Datei");
    //  Start: Hinzugefügte Menü-Items 
    private MenuItem mnItmCsvImport 	= new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport 	= new MenuItem("txt-Import");
    //  Ende: Hinzugefügt 
    private MenuItem mnItmCsvExport 	= new MenuItem("csv-Export");
    private MenuItem mnItmTxtExport 	= new MenuItem("txt-Export");
    //Ende Attribute der grafischen Oberflaeche
	
    
    public BahnhofView(BahnhofControl control, Stage primaryStage, BahnhofModel model)
    {
    	this.control = control;
    	this.model = model;
    	
    	Scene scene = new Scene(this.pane, 700, 340); // Breite angepasst
    	primaryStage.setScene(scene);
    	primaryStage.setTitle(this.model.getUeberschrift());
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }
    
    private void initKomponenten(){
    	// Labels
       	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24);
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400); // Position angepasst
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
    
    	// Textfelder
     	txtName.setLayoutX(170); // Position angepasst
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
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400); // Position angepasst
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400); // Position angepasst
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menu
   	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport); // Hinzugefügt
  	    this.mnDatei.getItems().add(mnItmTxtImport); // Hinzugefügt
  	    this.mnDatei.getItems().add(new SeparatorMenuItem()); // Hinzugefügt
  	    this.mnDatei.getItems().add(mnItmCsvExport);
  	    this.mnDatei.getItems().add(mnItmTxtExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
    
    private void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	//  Start: Korrektur 
            	// Übergibt ALLE Textfelder an den Controller
        	    control.nehmeBahnhofAuf(
        	    	txtName.getText(), 
        	    	txtOrt.getText(),
        	    	txtAnzahlGleise.getText(),
        	    	txtLetzteRenovierung.getText(),
        	    	txtZugarten.getText()
        	    );
        	    //  Ende: Korrektur 
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	    		zeigeBahnhofAn();
	        } 
   	    });
	    
	    //  Start: Hinzugefügte Listener 
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	       	 	control.leseBahnhofAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	control.leseBahnhofAusDatei("txt");
		    }
    	});
    	// Ende: Hinzugefügte Listener 
    	
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
	    	 @Override
	    	 public void handle(ActionEvent e) {
	    		 schreibeBahnhofInDatei("csv");
	    	 }
	    });
    	 mnItmTxtExport.setOnAction(new EventHandler<ActionEvent>() {
	    	 @Override
	    	 public void handle(ActionEvent e) {
	    		 schreibeBahnhofInDatei("txt");
	    	 }
	    });
    }
    
    // public, damit der Controller die Anzeige aktualisieren kann (z.B. nach Import)
    public void zeigeBahnhofAn(){
    	if(model.getBahnhof() != null){
    		txtAnzeige.setText(
    			model.getBahnhof().gibBahnhofZurueck(' ')); // ' ' wird ignoriert, siehe Bahnhof.java
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde kein Bahnhof aufgenommen!");
    	}
    }
    
    void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String fehlertyp, String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	fehlertyp, meldung).zeigeMeldungsfensterAn(); // Titel vereinfacht
    }
    
    private void schreibeBahnhofInDatei(String typ) {
    	 // Aufruf des Controls zum Schreiben
    	try {
			control.schreibeBahnhofInDatei(typ);
		} catch (IOException e) {
			zeigeFehlermeldungsfensterAn("IOException", "Fehler beim Schreiben der Datei: " + e.getMessage());
		}
    }
}
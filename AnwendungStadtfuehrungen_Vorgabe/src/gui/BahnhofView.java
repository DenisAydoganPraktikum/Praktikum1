package gui;


	
	

	import java.io.IOException;

import business.BahnhofModel;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.Menu;
	import javafx.scene.control.MenuBar;
	import javafx.scene.control.MenuItem;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;
	import javafx.scene.control.Alert.AlertType;
	import javafx.scene.layout.Pane;
	import javafx.scene.text.Font;
	import javafx.stage.Stage;
	import ownUtil.MeldungsfensterAnzeiger;

	
	public class BahnhofView {
		private BahnhofControl control;
		private BahnhofModel model;
		
		//---Anfang Attribute der grafischen Oberflaeche---
	    private Pane pane     				= new  Pane();
	    private Label lblEingabe    	 	= new Label("Eingabe");
	    private Label lblAnzeige   	 	    = new Label("Anzeige");
	    private Label lblName 				= new Label("Name:");
	    private Label lblGeoeffnetVon   	= new Label("Geöffnet von:");
	    private Label lblGeoeffnetBis  	 	= new Label("Geöffnet bis:");
	    private Label lblBeckenlaenge   	= new Label("Gleislaenge:");
	    private Label lblWassTemperatur  	= new Label("Wassertemperatur:");
	    private TextField txtName 	 		= new TextField();
	    private TextField txtGeoeffnetVon	= new TextField();
	    private TextField txtGeoeffnetBis	= new TextField();
	    private TextField txtBeckenlaenge	= new TextField();
	    private TextField txtWassTemperatur	= new TextField();
	    private TextArea txtAnzeige  		= new TextArea();
	    private Button btnEingabe 		 	= new Button("Eingabe");
	    private Button btnAnzeige 		 	= new Button("Anzeige");
	    private MenuBar mnbrMenuLeiste  	= new MenuBar();
	    private Menu mnDatei             	= new Menu("Datei");
	    private MenuItem mnItmCsvExport 	= new MenuItem("csv-Export");
	    private MenuItem mnItmTxtExport 	= new MenuItem("txt-Export");
	    //-------Ende Attribute der grafischen Oberflaeche-------
		
	    
	    public BahnhofView(BahnhofControl control, Stage primaryStage, BahnhofModel model)
	    {
	    	this.control = control;
	    	this.model = model;
	    	
	    	Scene scene = new Scene(this.pane, 560, 340);
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
	    	lblAnzeige.setLayoutX(310);
	    	lblAnzeige.setLayoutY(40);
	      	lblAnzeige.setFont(font);
	       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
	       	lblName.setLayoutX(20);
	    	lblName.setLayoutY(90);
	    	lblGeoeffnetVon.setLayoutX(20);
	    	lblGeoeffnetVon.setLayoutY(130);
	    	lblGeoeffnetBis.setLayoutX(20);
	    	lblGeoeffnetBis.setLayoutY(170);
	    	lblBeckenlaenge.setLayoutX(20);
	    	lblBeckenlaenge.setLayoutY(210);
	    	lblWassTemperatur.setLayoutX(20);
	    	lblWassTemperatur.setLayoutY(250);    	
	       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
	       		lblName, lblGeoeffnetVon, lblGeoeffnetBis,
	       		lblBeckenlaenge, lblWassTemperatur);
	    
	    	// Textfelder
	     	txtName.setLayoutX(130);
	    	txtName.setLayoutY(90);
	    	txtGeoeffnetVon.setLayoutX(130);
	    	txtGeoeffnetVon.setLayoutY(130);
	    	txtGeoeffnetBis.setLayoutX(130);
	    	txtGeoeffnetBis.setLayoutY(170);
	      	txtBeckenlaenge.setLayoutX(130);
	    	txtBeckenlaenge.setLayoutY(210);
	    	txtWassTemperatur.setLayoutX(130);
	    	txtWassTemperatur.setLayoutY(250);
	     	pane.getChildren().addAll( 
	     		txtName, txtGeoeffnetVon, txtGeoeffnetBis,
	     		txtBeckenlaenge, txtWassTemperatur);
	     	
	        // Textbereich	
	        txtAnzeige.setEditable(false);
	     	txtAnzeige.setLayoutX(310);
	    	txtAnzeige.setLayoutY(90);
	     	txtAnzeige.setPrefWidth(220);
	    	txtAnzeige.setPrefHeight(185);
	       	pane.getChildren().add(txtAnzeige); 
	       	
	        // Buttons
	        btnEingabe.setLayoutX(20);
	        btnEingabe.setLayoutY(290);
	        btnAnzeige.setLayoutX(310);
	        btnAnzeige.setLayoutY(290);
	        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
	        
	 		// Menu
	   	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
	  	    this.mnDatei.getItems().add(mnItmCsvExport);
	  	    this.mnDatei.getItems().add(mnItmTxtExport);
	 	    pane.getChildren().add(mnbrMenuLeiste);
	   }
	    
	    
	    private void initListener() {
		    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	
	        	    control.nehmeBahnhofAuf(txtName.getText(), null, 0, 0, null);
	            }
		    });
		    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
		    	@Override
		        public void handle(ActionEvent e) {
		    		zeigeBahnhofAn();
		        } 
	   	    });  
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
	    
	    private void zeigeBahnhofAn(){
	    	if(model.getBahnhof() != null){
	    		txtAnzeige.setText(
	    			model.getBahnhof().gibBahnhofZurueck(' '));
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
	        	fehlertyp + "Fehler", meldung).zeigeMeldungsfensterAn();
	    }
	    
	    
	    private void schreibeBahnhofInDatei(String typ)
	    {
	    	 // Aufruf des Controls zum Schreiben des Bahnhofs in die
	    	 // Datei des vorgegebenen Typs.
	    	try {
				control.schreibeBahnhofInDatei(typ);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    }
	}
	 



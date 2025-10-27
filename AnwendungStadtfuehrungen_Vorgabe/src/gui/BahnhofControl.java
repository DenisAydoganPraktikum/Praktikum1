package gui;



	import java.io.IOException;
	import business.Bahnhof;
	import business.BahnhofModel;
	import javafx.stage.Stage; 
	import ownUtil.PlausiException;

	
	
	public class BahnhofControl {


		


	

	
	
		private BahnhofView view;
		private BahnhofModel model;
		
	    
		public BahnhofControl(Stage primaryStage)
		{
			this.model = new BahnhofModel();
			this.view = new BahnhofView(this, primaryStage, model);
		}
		
	    public void nehmeBahnhofAuf(String name, String ort, int anzahlGleise, int letzteRenovierung, String[] zugarten)
	    {
	    	model.setBahnhof(new Bahnhof(name, ort, anzahlGleise, letzteRenovierung, zugarten));
			view.zeigeInformationsfensterAn("Der Bahnhof wurde aufgenommen!");
	    }
	    
	   /* public Bahnhof getBahnhof()
	    {
	    	return Bahnhof;
	    }
	    */
	    void schreibeBahnhofInDatei(String typ) throws IOException
	    {
	    	 try{
	    		 if("csv".equals(typ))
	    		 {
	    			 // Aufruf des Models zum Schreiben des
	    			
	    			 // Typs und Ausgabe der Meldung
	    			 model.schreibeBahnhofInCsvDatei();
	    		 }
	    		 else
	    	 	{
	    		 view.zeigeInformationsfensterAn("Noch nicht implementiert!");
	    	 	}
	    	 	}
	    	
	    	 catch(Exception exc)
	    	 {
	    		view.zeigeFehlermeldungsfensterAn(exc.getCause() + "er ", "Unbekannter Fehler beim Speichern!");
	    	}
	    }
	}
	
	


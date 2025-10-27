package business;



		import java.io.BufferedWriter;
		import java.io.FileWriter;
		import java.io.IOException;
	 

public class BahnhofModel {
	

	

		

		
			;
			private Bahnhof bahnhof;
			
			public BahnhofModel()
			{
				//this.control = control;
			}
			
			public String getUeberschrift()
			{
				 return "Verwaltung von Bahnhöfen";
			} 
			
			public void schreibeFreizeitbaederInCsvDatei() throws IOException
			
			{
				BufferedWriter aus = new BufferedWriter(new FileWriter("Bahnhof.csv", true));
				aus.write(this.getBahnhof().gibBahnhofZurueck(';'));
				aus.close();
			}
			
			public Bahnhof getBahnhof()
			{
				return bahnhof;
			}
			
			public void setBahnhof(Bahnhof bahnhof)
			{
				this.bahnhof = bahnhof;
			}

			public void schreibeBahnhofInCsvDatei() {
				// TODO Auto-generated method stub
				
			}
		
		
		
		
	}



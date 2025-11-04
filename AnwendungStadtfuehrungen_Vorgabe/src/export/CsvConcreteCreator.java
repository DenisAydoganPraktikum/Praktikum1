package export;

import java.io.IOException;

public class CsvConcreteCreator extends Creator {

	// Hier wird die Fabrik-Methode implementiert
	@Override
	public Product factoryMethod() throws IOException {
		// Diese Zeile erzeugt das konkrete CSV-Product
		return new CsvConcreteProduct();
	}
}
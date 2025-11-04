package export;

import java.io.IOException;

public class TxtConcreteCreator extends Creator {

	@Override
	public Product factoryMethod() throws IOException {
		// Diese Zeile erzeugt das konkrete TXT-Product
		return new TxtConcreteProduct();
	}
}
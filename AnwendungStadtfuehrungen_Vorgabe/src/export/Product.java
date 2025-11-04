package export;

import java.io.IOException;

public abstract class Product {
    
    // Die Methode zum Hinzufügen einer Zeile zur Datei ist abstrakt
    public abstract void fuegelnDateiHinzu(Object object) throws IOException;
    
    // Die Methode zum Schließen der Datei ist abstrakt
    public abstract void schliesseDatei() throws IOException;
}
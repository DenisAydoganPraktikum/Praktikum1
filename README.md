# Praktikum1 - MVC-Aufbau

Dieses Projekt ist bereits in einer MVC-Architektur organisiert.

## Struktur

- `AnwendungStadtfuehrungen_Vorgabe/src/main/Main.java`
  - Startpunkt der Anwendung
  - startet den Controller

- `AnwendungStadtfuehrungen_Vorgabe/src/gui/BahnhofControl.java`
  - Controller
  - verarbeitet Eingaben und verbindet View und Model

- `AnwendungStadtfuehrungen_Vorgabe/src/gui/BahnhofView.java`
  - View
  - enthält die Oberfläche und zeigt Informationen an

- `AnwendungStadtfuehrungen_Vorgabe/src/business/BahnhofModel.java`
  - Model
  - enthält die Geschäftslogik und die Dateiverarbeitung

- `AnwendungStadtfuehrungen_Vorgabe/src/business/Bahnhof.java`
  - Datenmodell für einen Bahnhof

## MVC-Prinzip

- Die View kennt das Model nicht direkt und arbeitet nur mit dem Controller.
- Der Controller verwaltet die Interaktion zwischen View und Model.
- Das Model enthält die Daten und Logik, aber keine GUI-Elemente.
- Die Anwendung startet über `Main`, nicht über die alte UI-Klasse.

## Alte Klasse

Die Klasse `BahnhoefeAnwendungssystem.java` ist eine ältere, gemischte Lösung und sollte nicht mehr als Hauptklasse für die Anwendung verwendet werden.

Die aktuelle, saubere Einstiegsklasse ist:

```java
new BahnhofControl(primaryStage);
```

## Fazit

Die Anwendung ist damit in der gewählten MVC-Struktur organisiert und kann sauber erweitert werden.

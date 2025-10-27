package main;

import gui.BahnhoefeAnwendungssystem;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		new BahnhoefeAnwendungssystem(primaryStage);
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}

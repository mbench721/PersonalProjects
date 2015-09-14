package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import managers.GameManager;
import settings.Settings;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	private GameManager game;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene( root, 500, 500,Color.BLACK);
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			game = new GameManager(scene);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}

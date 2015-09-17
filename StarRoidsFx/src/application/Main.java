package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import managers.GameManager;
import settings.Settings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	private GameManager game;
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			Group root = new Group();
			
			game = new GameManager(root);
			Scene scene = new Scene( root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT,Color.BLACK);
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

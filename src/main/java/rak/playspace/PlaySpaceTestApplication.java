package rak.playspace;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PlaySpaceTestApplication extends Application {
	private static Stage primaryStage;
	
	public final static String MAIN_MENU = "MainView";
	public final static long DEFAULT_SEED = 123;
	public final static int DEFAULT_DENSITY = 400;
	
	public static void main(String[] args) {
		launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		PlaySpaceTestApplication.primaryStage = primaryStage;
		
        primaryStage.setTitle("Playspace Tester");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images//Logo Icon.png")));
        setScene(MAIN_MENU);
        primaryStage.show();
	}
	
	public static void setScene(String sceneName){
		try {
			Scene scene = loadFXML(sceneName);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Scene loadFXML(String fileName) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(PlaySpaceTestApplication.class.getResource("view/" + fileName + ".fxml"));
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		return scene;
	}
	
}

package dad.gesCon.ui.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

	public static Stage primaryStage;
	
	private MainController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		App.primaryStage = primaryStage;
		
		controller = new MainController();
		
		primaryStage.setTitle("Contactos");
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/contacts-icon-32x32.png")));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

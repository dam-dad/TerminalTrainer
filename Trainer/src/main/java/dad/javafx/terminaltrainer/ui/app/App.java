package dad.javafx.terminaltrainer.ui.app;

import dad.javafx.terminaltrainer.controllers.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	MainController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new MainController();
		
		Scene scene = new Scene(controller.getView());
		
		primaryStage.setTitle("Trainer");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}

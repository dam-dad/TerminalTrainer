package dad.javafx.terminaltrainer.ui.app;

import dad.javafx.terminaltrainer.config.Config;
import dad.javafx.terminaltrainer.controllers.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import dad.javafx.terminaltrainer.ui.app.App;

public class App extends Application {
	MainController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new MainController();

		Scene scene = new Scene(controller.getView());
		primaryStage.setTitle("Trainer");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.out.println("Stage is closing");
				Config.CONFIG.disable();
				System.out.println(Config.CONFIG.isEnabled());
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}

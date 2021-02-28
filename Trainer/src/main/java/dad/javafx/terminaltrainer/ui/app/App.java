package dad.javafx.terminaltrainer.ui.app;

import dad.javafx.terminaltrainer.config.Config;
import dad.javafx.terminaltrainer.controllers.MainController;
import dad.javafx.terminaltrainer.monitoring.Monitoring;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dad.javafx.terminaltrainer.ui.app.App;

public class App extends Application {
	static MainController mainController;
	private static Stage primaryStage;

	@Override
	public void init() throws Exception {

		if (!Config.CONFIG.isEnabled())
			Config.CONFIG.enable();
		Monitoring.start();

		super.init();
	}

	@Override
	public void stop() throws Exception {

		Monitoring.stop();
		Config.CONFIG.disable();

		super.stop();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainController = new MainController();
		App.primaryStage = primaryStage;

		Scene scene = new Scene(mainController.getView());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Trainer");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		// TODO Auto-generated method stub
		return primaryStage;
	}

}

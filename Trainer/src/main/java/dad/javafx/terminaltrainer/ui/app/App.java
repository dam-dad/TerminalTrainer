package dad.javafx.terminaltrainer.ui.app;

import dad.javafx.terminaltrainer.config.Config;
import dad.javafx.terminaltrainer.controllers.MainController;
import dad.javafx.terminaltrainer.controllers.MainTrainerController;
import dad.javafx.terminaltrainer.monitoring.Monitoring;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dad.javafx.terminaltrainer.ui.app.App;

public class App extends Application {
	static MainTrainerController controller;

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		if (!Config.CONFIG.isEnabled())
			Config.CONFIG.enable();
		Monitoring.start();
		super.init();
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		Monitoring.stop();
		Config.CONFIG.disable();
		super.stop();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new MainTrainerController();

		Scene scene = new Scene(controller.getView());
		primaryStage.setTitle("Trainer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static MainTrainerController getController() {
		return controller;
	}

}

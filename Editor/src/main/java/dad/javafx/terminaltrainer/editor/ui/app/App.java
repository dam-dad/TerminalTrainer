package dad.javafx.terminaltrainer.editor.ui.app;

import dad.javafx.terminaltrainer.editor.model.Memory;
import dad.javafx.terminaltrainer.editor.ui.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	private static Stage primaryStage;

	private static MainController controller;

	public static Memory config = new Memory();

	@Override
	public void start(Stage primaryStage) throws Exception {

		App.primaryStage = primaryStage;

		controller = new MainController();

		Scene scene = new Scene(controller.getView(), config.getAncho(), config.getAlto());
		// Bindeo del tamaño de la escena
		config.anchoProperty().bind(scene.widthProperty());
		config.altoProperty().bind(scene.heightProperty());

		primaryStage.setX(config.getPosX());
		primaryStage.setY(config.getPosY());
		config.posXProperty().bind(primaryStage.xProperty());
		config.posYProperty().bind(primaryStage.yProperty());

		primaryStage.setScene(scene);
		primaryStage.setTitle("Editor");
		primaryStage.getIcons().add(new Image("/images/icon-small.png"));
		primaryStage.show();
	}

	/**
	 * Loads the property file from the .terminaltrainer folder.
	 */
	@Override
	public void init() throws Exception {
		config.loadFile();
	}

	/**
	 * Saves a property file.
	 */
	@Override
	public void stop() throws Exception {

		config.setTheme(controller.getView().getStylesheets().get(0).toString());

		config.setSplitPosLeft(controller.getSplitPos());
		config.saveFile();
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static Memory getConfig() {
		return config;
	}

	public static MainController getController() {
		return controller;
	}

	public static void main(String[] args) {
		launch(args);
	}

}

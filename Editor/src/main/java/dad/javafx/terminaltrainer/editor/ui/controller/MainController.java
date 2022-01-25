package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.reports.MainReport;
import dad.javafx.terminaltrainer.editor.ui.app.App;
import dad.javafx.terminaltrainer.utils.JSONUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import net.sf.jasperreports.engine.JRException;

public class MainController implements Initializable {

	// controladores
	ChallengeController challengeController = new ChallengeController();
	GoalController goalController = new GoalController();

	// Model
	private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();

	@FXML
	private BorderPane view;

	@FXML
	private SplitPane split;

	String ruta;

	/**
	 * Loads the MainView.fxml view.
	 * 
	 * @throws IOException
	 */
	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.getView().getStylesheets().clear();
		this.getView().getStylesheets().add(App.getConfig().getTheme());
		challengeController.getView().getStylesheets().clear();
		challengeController.getView().getStylesheets().add(App.getConfig().getTheme());
		goalController.getView().getStylesheets().clear();
		goalController.getView().getStylesheets().add(App.getConfig().getTheme());

		challengeController.setGoalController(goalController);

		split.setDividerPositions(App.config.getSplitPosLeft());

		split.getItems().addAll(challengeController.getView(), goalController.getView());

		challengeController.challengeProperty().bind(challenge);
		challenge.set(new Challenge());

		goalController.getView().disableProperty()
				.bind(challengeController.getTableGoals().getSelectionModel().selectedItemProperty().isNull());

	}

	/**
	 * Initializes a new challenge.
	 * 
	 * @param event
	 */
	@FXML
	void onNewAction(ActionEvent event) {
		challenge.set(new Challenge());
	}

	/**
	 * Opens a .challenge file with a FileChooser, and proceeds to set the challenge
	 * properties.
	 * 
	 * @param event
	 */
	@FXML
	void onOpenAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open a challenge file.");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Challenge (*.challenge)", "*.challenge"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All types of files", "."));
		File chFile = fileChooser.showOpenDialog(App.getPrimaryStage());
		if (chFile != null) {
			try {
				ruta = chFile.getAbsolutePath();
				challenge.set(JSONUtils.fromJson(chFile, Challenge.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Saves the challenge properties with a FileChooser.
	 * 
	 * @param event
	 */
	@FXML
	void onSaveAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save a challenge.");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Challenge (*.challenge)", "*.challenge"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los archivos", "*.*"));
		File chFile = fileChooser.showSaveDialog(App.getPrimaryStage());
		if (chFile != null) {
			try {
				JSONUtils.toJson(chFile, challenge.get());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Sets the app theme to a more dark one.
	 * 
	 * @param event
	 */
	@FXML
	void onDarkThemeAction(ActionEvent event) {
		this.getView().getStylesheets().clear();
		this.getView().getStylesheets().add("/css/dark-theme.css");
		challengeController.getView().getStylesheets().clear();
		challengeController.getView().getStylesheets().add("/css/dark-theme.css");
		goalController.getView().getStylesheets().clear();
		goalController.getView().getStylesheets().add("/css/dark-theme.css");
	}

	/**
	 * Sets the app theme to a lighter one(oh my eyes, the light).
	 * 
	 * @param event
	 */
	@FXML
	void onLightThemeAction(ActionEvent event) {
		this.getView().getStylesheets().clear();
		this.getView().getStylesheets().add("/css/light-theme.css");
		challengeController.getView().getStylesheets().clear();
		challengeController.getView().getStylesheets().add("/css/light-theme.css");
		goalController.getView().getStylesheets().clear();
		goalController.getView().getStylesheets().add("/css/light-theme.css");

	}

	@FXML
	void onGenerateReportAction(ActionEvent event) throws JRException, IOException {
		MainReport.generarPdf(challenge.get());
	}

	public BorderPane getView() {
		return this.view;
	}

	public ChallengeController getChallengeController() {
		return challengeController;
	}

	public double getSplitPos() {
		return split.getDividerPositions()[0];
	}

}

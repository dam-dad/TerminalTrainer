package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.ui.app.App;
import dad.javafx.terminaltrainer.utils.JSONUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {//.terminaltrainer en usuarios. Bindfx con preferences(solo 1 instancia).
		
		challengeController.setGoalController(goalController);
		
		split.setDividerPositions(0.5, 0.5);
		split.getItems().addAll(challengeController.getView(), goalController.getView());

		challenge.addListener((o, ov, nv) -> onChallengeChanged(o, ov, nv));

		challenge.set(new Challenge());

	}

	private void onChallengeChanged(ObservableValue<? extends Challenge> o, Challenge ov, Challenge nv) {
		if (ov != null) {
			challengeController.challengeProperty().unbind();
			// TODO desbindear el resto de elementos
		}

		if (nv != null) {
			challengeController.challengeProperty().bind(challenge);
			// TODO bindear el resto de elementos
		}
	}

	@FXML
	void onNewAction(ActionEvent event) {
		challenge.set(new Challenge());
	}

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

	public BorderPane getView() {
		return this.view;
	}

}

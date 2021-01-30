package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.model.Goal;
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
	private ObjectProperty<Goal> goal = new SimpleObjectProperty<>();

	@FXML
	private BorderPane view;

	@FXML
	private SplitPane split;

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		split.setDividerPositions(0.5, 0.5);
		split.getItems().addAll(challengeController.getView(), goalController.getView());

		challenge.addListener((o, ov, nv) -> onChallengeChanged(o, ov, nv));

		challenge.set(new Challenge());

	}

	private void onChallengeChanged(ObservableValue<? extends Challenge> o, Challenge ov, Challenge nv) {
		if (ov != null) {
			challengeController.challengeProperty().unbind();
			goalController.goalProperty().unbind();
			// TODO desbindear el resto de elementos
		}

		if (nv != null) {
			challengeController.challengeProperty().bind(challenge);
			goalController.goalProperty().bind(goal);
			// TODO bindear el resto de elementos
		}
	}

	@FXML
	void onNewAction(ActionEvent event) {// No desbindea la tabla.
		challenge.set(new Challenge());
	}

	@FXML
	void onSaveAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Guardar un challenge.");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Challenge (*.ch)", "*.ch"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los archivos", "*.*"));
		File cvFile = fileChooser.showSaveDialog(App.getPrimaryStage());
		if (cvFile != null) {
			try {
				JSONUtils.toJson(cvFile, challenge.get());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public BorderPane getView() {
		return this.view;
	}

}

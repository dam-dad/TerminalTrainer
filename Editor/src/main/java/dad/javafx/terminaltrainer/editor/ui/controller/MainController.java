package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
	// controladores
	ChallengeController challengeController = new ChallengeController();
	GoalController goalController = new GoalController();
	
	//Model
	private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();

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

	}

	private void onChallengeChanged(ObservableValue<? extends Challenge> o, Challenge ov, Challenge nv) {
		if(ov != null) {
			challengeController.challengeProperty().unbind();
			//TODO desbindear el resto de elementos
		}
		
		if(nv != null) {
			challengeController.challengeProperty().bind(challenge);
			//TODO bindear el resto de elementos
		}
	}

	@FXML
	void onNuevoAction(ActionEvent event) {//Sólo desbindea 1 elemento de la tabla, mirar desbindeos.
		challenge.set(new Challenge());
	}

	public BorderPane getView() {
		return this.view;
	}

}

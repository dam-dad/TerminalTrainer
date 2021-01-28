package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.model.Goal;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
	// controladores
	ChallengeController challengeController = new ChallengeController();

	// model
	private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();
	private ListProperty<Goal> goals = new SimpleListProperty<Goal>(FXCollections.observableArrayList());

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
		split.getItems().addAll(challengeController.getView());

//		challenge.addListener((o, ov, nv) -> onChallengeChanged(o, ov, nv));
//		goals.addListener((o, ov, nv) -> onGoalsChanged(o, ov, nv));

		// deshabilita el botón de eliminar goals si la tabla está vacía
		// btnRemoveGoal.disableProperty().bind(tableGoals.getSelectionModel().selectedItemProperty().isNull());

		// bindeo de columnas de la tabla goal
//		descriptionColumn.setCellValueFactory(v -> v.getValue().descriptionProperty());
//		shellColumn.setCellValueFactory(v -> v.getValue().shellProperty());
//		pwdColumn.setCellValueFactory(v -> v.getValue().pathProperty());
//		userColumn.setCellValueFactory(v -> v.getValue().usernameProperty());
//		
//		tableGoals.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onSelectedItemChanged(o, ov, nv));

	}

//	private void onSelectedItemChanged(ObservableValue<? extends Goal> o, Goal ov, Goal nv) {
//		
//	}

//	private void onGoalsChanged(ObservableValue<? extends ObservableList<Goal>> o, ObservableList<Goal> ov,
//			ObservableList<Goal> nv) {
//		if (ov != null) {
//			// desbindeo de la tabla de goals
//			tableGoals.itemsProperty().unbindBidirectional(goals);
//
//			textUserGoal.textProperty().unbindBidirectional(goals);
//
//		}
//
//		if (nv != null) {
//			// bindeo de la tabla goals
//			tableGoals.itemsProperty().bindBidirectional(goals);
//
//			textUserGoal.textProperty().bindBidirectional(goals.get().get(0).usernameProperty());
//		}
//	}
//
//	private void onChallengeChanged(ObservableValue<? extends Challenge> o, Challenge ov, Challenge nv) {
//
//		if (ov != null) {
//
//			textDescriptionChallengue.textProperty().unbindBidirectional(ov.descriptionProperty());
//			textNameChallenge.textProperty().unbindBidirectional(ov.nameProperty());
//			// TODO unbind properties
//		}
//
//		if (nv != null) {
//
//			textDescriptionChallengue.textProperty().bindBidirectional(nv.descriptionProperty());
//			textNameChallenge.textProperty().bindBidirectional(nv.nameProperty());
//			
//			// TODO bind properties
//		}
//	}

	public BorderPane getView() {
		return this.view;
	}

}

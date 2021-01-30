package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.model.Goal;
import dad.javafx.terminaltrainer.editor.model.Shell;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class ChallengeController implements Initializable {
	// Controllers
	GoalController goalController = new GoalController();
	// Model
	private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();
	private ListProperty<Goal> goals = new SimpleListProperty<Goal>(FXCollections.observableArrayList());

	@FXML
	private GridPane view;

	@FXML
	private JFXButton btnAddGoal;

	@FXML
	private JFXButton btnRemoveGoal;

	@FXML
	private JFXRadioButton radioOSChallenge;

	@FXML
	private JFXRadioButton radioOSChallenge2;

	@FXML
	private JFXTextField textNameChallenge;

	@FXML
	private JFXTextArea textDescriptionChallengue;

	@FXML
	private TableView<Goal> tableGoals;

	@FXML
	private TableColumn<Goal, String> descriptionColumn;

	@FXML
	private TableColumn<Goal, Shell> shellColumn;

	@FXML
	private TableColumn<Goal, String> pwdColumn;

	@FXML
	private TableColumn<Goal, String> userColumn;

	private ToggleGroup grupoRadioButtons;

	Challenge modeloChallenge = new Challenge();

	@SuppressWarnings("static-access")
	@FXML
	void onAddGoalAction(ActionEvent event) {
		Goal goal = new Goal();
		goal.setDescription("Descripción por defecto");
		goal.setPath("C:\\Users");
		Shell shell = null;
		goal.setShell(shell.CMD);
		goal.setUsername("Usuario");
		goals.add(goal);
		tableGoals.getItems().addAll(goals);
		tableGoals.getSelectionModel().selectLast();
	}

	@FXML
	void onRemoveGoalAction(ActionEvent event) {// No borra
		modeloChallenge.getGoals().remove(tableGoals.getSelectionModel().getSelectedItem());
	}

	public ChallengeController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChallengeView.fxml"));
		loader.setController(this);
		loader.load();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// ToggleGroup para que al marcar un radioButton se desmarque el otro.
		grupoRadioButtons = new ToggleGroup();
		grupoRadioButtons.getToggles().addAll(radioOSChallenge, radioOSChallenge2);

		challenge.addListener((o, ov, nv) -> onChallengeChanged(o, ov, nv));

		// deshabilita el botón de eliminar goals si la tabla está vacía
		btnRemoveGoal.disableProperty().bind(tableGoals.getSelectionModel().selectedItemProperty().isNull());

		// bindeo de columnas de la tabla goal
		descriptionColumn.setCellValueFactory(v -> v.getValue().descriptionProperty());
		shellColumn.setCellValueFactory(v -> v.getValue().shellProperty());
		pwdColumn.setCellValueFactory(v -> v.getValue().pathProperty());
		userColumn.setCellValueFactory(v -> v.getValue().usernameProperty());

		tableGoals.getSelectionModel().selectedItemProperty()
				.addListener((o, ov, nv) -> onSelectedItemChanged(o, ov, nv));

	}

	private void onSelectedItemChanged(ObservableValue<? extends Goal> o, Goal ov, Goal nv) {

	}

	private void onChallengeChanged(ObservableValue<? extends Challenge> o, Challenge ov, Challenge nv) {
		if (ov != null) {
			tableGoals.itemsProperty().unbindBidirectional(goals);

			textDescriptionChallengue.textProperty().unbindBidirectional(ov.descriptionProperty());
			textNameChallenge.textProperty().unbindBidirectional(ov.nameProperty());
			// TODO unbind properties
		}

		if (nv != null) {
			tableGoals.itemsProperty().bindBidirectional(goals);

			textDescriptionChallengue.textProperty().bindBidirectional(nv.descriptionProperty());
			textNameChallenge.textProperty().bindBidirectional(nv.nameProperty());

			// TODO bind properties
		}
	}

	public GridPane getView() {
		return view;
	}

	public final ObjectProperty<Challenge> challengeProperty() {
		return this.challenge;
	}

	public final Challenge getChallenge() {
		return this.challengeProperty().get();
	}

	public final void setChallenge(final Challenge challenge) {
		this.challengeProperty().set(challenge);
	}

}

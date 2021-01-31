package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.model.Goal;
import dad.javafx.terminaltrainer.editor.model.OS;
import dad.javafx.terminaltrainer.editor.model.Shell;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class ChallengeController implements Initializable {
	
	// Controllers
	GoalController goalController = new GoalController();
	// Model
	private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();

	@FXML
	private GridPane view;

	@FXML
	private JFXButton btnAddGoal;

	@FXML
	private JFXButton btnRemoveGoal;

    @FXML
    private JFXComboBox<OS> comboOS;

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


	Challenge modeloChallenge = new Challenge();

	@SuppressWarnings("static-access")
	@FXML
	void onAddGoalAction(ActionEvent event) {
		Goal goal = new Goal();
		goal.setDescription("Default description");
		goal.setPath("C:\\Users");
		Shell shell = null;
		goal.setShell(shell.CMD);
		goal.setUsername("User");
		tableGoals.getItems().add(goal);
		tableGoals.getSelectionModel().selectLast();
	}

	@FXML
	void onRemoveGoalAction(ActionEvent event) {
		tableGoals.getItems().remove(tableGoals.getSelectionModel().getSelectedItem());
	}

	public ChallengeController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChallengeView.fxml"));
		loader.setController(this);
		loader.load();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

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
		
		
		//Agrega los datos de el enum OS al combobox y selecciona el primero
		comboOS.getItems().setAll(OS.values());
		comboOS.getSelectionModel().selectFirst();
	}

	private void onSelectedItemChanged(ObservableValue<? extends Goal> o, Goal ov, Goal nv) {

	}

	private void onChallengeChanged(ObservableValue<? extends Challenge> o, Challenge ov, Challenge nv) {
		if (ov != null) {
			textDescriptionChallengue.textProperty().unbindBidirectional(ov.descriptionProperty());
			textNameChallenge.textProperty().unbindBidirectional(ov.nameProperty());
			comboOS.valueProperty().unbindBidirectional(ov.osProperty());
			tableGoals.itemsProperty().unbindBidirectional(ov.goalsProperty());
			// TODO unbind properties
		}

		if (nv != null) {
			textDescriptionChallengue.textProperty().bindBidirectional(nv.descriptionProperty());
			textNameChallenge.textProperty().bindBidirectional(nv.nameProperty());
			comboOS.valueProperty().bindBidirectional(nv.osProperty());
			tableGoals.itemsProperty().bindBidirectional(nv.goalsProperty());

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

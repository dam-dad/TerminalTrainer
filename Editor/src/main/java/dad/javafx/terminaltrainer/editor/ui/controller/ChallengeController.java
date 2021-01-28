package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.model.Goal;
import dad.javafx.terminaltrainer.editor.model.Shell;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ChallengeController implements Initializable {
	private ListProperty<Goal> goals = new SimpleListProperty<Goal>(FXCollections.observableArrayList());

	@FXML
	private GridPane view;

	@FXML
	private Button btnAddGoal;

	@FXML
	private Button btnRemoveGoal;

	@FXML
	private TextField textNameChallenge;

	@FXML
	private TextArea textDescriptionChallengue;

	@FXML
	private TableView<?> tableGoals;

	@FXML
	private TableColumn<?, ?> descriptionColumn;

	@FXML
	private TableColumn<?, ?> shellColumn;

	@FXML
	private TableColumn<?, ?> pwdColumn;

	@FXML
	private TableColumn<?, ?> userColumn;

	@FXML
	private RadioButton radioOSChallenge;

	@FXML
	private RadioButton radioOSChallenge2;

	private ToggleGroup grupoRadioButtons;

	Challenge modeloChallenge = new Challenge();

	@FXML
	void onAddGoalAction(ActionEvent event) {
		Goal goal = new Goal();
		goal.setDescription("Descripción por defecto");
		goal.setPath("C:\\Users");
		Shell shell = null;
		goal.setShell(shell.CMD);
		goal.setUsername("Usuario");
		goals.add(goal);
		tableGoals.getSelectionModel().selectLast();
	}

	@FXML
	void onRemoveGoalAction(ActionEvent event) {
		// modeloChallenge.getGoals().remove(tableGoals.getSelectionModel().getSelectedIndex());
	}

	public GridPane getView() {
		return view;
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

	}
}

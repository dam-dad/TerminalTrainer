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
	private TableColumn<Goal, String> shellColumn;

	@FXML
	private TableColumn<Goal, String> pwdColumn;

	@FXML
	private TableColumn<Goal, String> userColumn;

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
		tableGoals.getItems().addAll(goals);
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

package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import dad.javafx.terminaltrainer.editor.model.Goal;
import dad.javafx.terminaltrainer.editor.model.Shell;
import javafx.fxml.Initializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class GoalController implements Initializable {
	private ListProperty<Goal> goals = new SimpleListProperty<Goal>(FXCollections.observableArrayList());

	@FXML
	private GridPane view;

	@FXML
	private JFXButton btnAddCommand;

	@FXML
	private JFXButton btnRemoveCommand;

	@FXML
	private JFXButton btnAddTip;

	@FXML
	private JFXButton btnRemoveTip;

	@FXML
	private JFXTextField textDescription;

	@FXML
	private JFXComboBox<Shell> comboShell;

	@FXML
	private JFXTextField textPWD;

	@FXML
	private JFXTextField textUser;

	@FXML
	private JFXListView<String> ListCommands;

	@FXML
	private JFXListView<String> ListTips;

	@FXML
	void onAddCommandAction(ActionEvent event) {

	}

	@FXML
	void onAddTippAction(ActionEvent event) {

	}

	@FXML
	void onRemoveCommandAction(ActionEvent event) {

	}

	@FXML
	void onRemoveTipAction(ActionEvent event) {

	}

	public GoalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GoalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		goals.addListener((o, ov, nv) -> onGoalsChanged(o, ov, nv));

		comboShell.getItems().setAll(Shell.values());
	}

	private void onGoalsChanged(ObservableValue<? extends ObservableList<Goal>> o, ObservableList<Goal> ov,
			ObservableList<Goal> nv) {
		if (ov != null) {
			textDescription.textProperty().unbindBidirectional(goals);
			textUser.textProperty().unbindBidirectional(goals);

		}

		if (nv != null) {
			textDescription.textProperty().bindBidirectional(goals.get().get(0).descriptionProperty());
			textUser.textProperty().bindBidirectional(goals.get().get(0).usernameProperty());
		}
	}

	public GridPane getView() {
		return view;
	}

}

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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.GridPane;

public class GoalController implements Initializable {

	private ObjectProperty<Goal> goal = new SimpleObjectProperty<Goal>();

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
		String command = "default command";
		ListCommands.getItems().add(command);
		ListCommands.getSelectionModel().selectLast();
	}

	@FXML
	void onAddTippAction(ActionEvent event) {
		String tip = "default tip";
		ListTips.getItems().add(tip);
		ListTips.getSelectionModel().selectLast();
	}

	@FXML
	void onRemoveCommandAction(ActionEvent event) {
		ListCommands.getItems().remove(ListCommands.getSelectionModel().getSelectedItem());
	}

	@FXML
	void onRemoveTipAction(ActionEvent event) {
		ListTips.getItems().remove(ListTips.getSelectionModel().getSelectedItem());
	}

	public GoalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GoalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ListCommands.setCellFactory(TextFieldListCell.forListView());
		ListTips.setCellFactory(TextFieldListCell.forListView());

		btnRemoveCommand.disableProperty().bind(ListCommands.getSelectionModel().selectedItemProperty().isNull());
		btnRemoveTip.disableProperty().bind(ListTips.getSelectionModel().selectedItemProperty().isNull());

		goal.addListener((o, ov, nv) -> onGoalsChanged(o, ov, nv));

		comboShell.getItems().setAll(Shell.values());
	}

	private void onGoalsChanged(ObservableValue<? extends Goal> o, Goal ov, Goal nv) {
		if (ov != null) {
			textDescription.textProperty().unbindBidirectional(ov.descriptionProperty());
			comboShell.valueProperty().unbindBidirectional(ov.shellProperty());
			textPWD.textProperty().unbindBidirectional(ov.pathProperty());
			textUser.textProperty().unbindBidirectional(ov.usernameProperty());
			ListCommands.itemsProperty().unbindBidirectional(ov.validCommandsProperty());
			ListTips.itemsProperty().unbindBidirectional(ov.tipsProperty());
		}

		if (nv != null) {
			textDescription.textProperty().bindBidirectional(nv.descriptionProperty());
			comboShell.valueProperty().bindBidirectional(nv.shellProperty());
			textPWD.textProperty().bindBidirectional(nv.pathProperty());
			textUser.textProperty().bindBidirectional(nv.usernameProperty());
			ListCommands.itemsProperty().bindBidirectional(nv.validCommandsProperty());
			ListTips.itemsProperty().bindBidirectional(nv.tipsProperty());
		}else {
			textDescription.clear();
			comboShell.getSelectionModel().clearSelection();
			textPWD.clear();
			textUser.clear();
			ListCommands.getItems().clear();
			ListTips.getItems().clear();
		}
	}

	public GridPane getView() {
		return view;
	}

	public final ObjectProperty<Goal> goalProperty() {
		return this.goal;
	}

	public final Goal getGoal() {
		return this.goalProperty().get();
	}

	public final void setGoal(final Goal goal) {
		this.goalProperty().set(goal);
	}

}

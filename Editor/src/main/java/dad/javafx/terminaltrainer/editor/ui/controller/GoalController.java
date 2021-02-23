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
import javafx.collections.FXCollections;
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
	private JFXListView<String> listCommands;

	@FXML
	private JFXListView<String> listTips;

	
	/**
	 * 
	 * Adds a default command to the list of commands of the selected goal.
	 * 
	 * @param event
	 */
	@FXML
	void onAddCommandAction(ActionEvent event) {
		String command = "default command";
		getGoal().getValidCommands().add(command);
		listCommands.getSelectionModel().selectLast();
	}

	/**
	 * 
	 * Adds a default tip to the list of tips of the selected goal.
	 * 
	 * @param event
	 */
	@FXML
	void onAddTippAction(ActionEvent event) {
		String tip = "default tip";
		getGoal().getTips().add(tip);
		listTips.getSelectionModel().selectLast();
	}

	
	/**
	 * 
	 * Deletes the command which is selected from the list of commands of the selected goal.
	 * 
	 * @param event
	 */
	@FXML
	void onRemoveCommandAction(ActionEvent event) {
		listCommands.getItems().remove(listCommands.getSelectionModel().getSelectedItem());
	}

	/**
	 * 
	 * Deletes the tip which is selected from the list of tips of the selected goal.
	 * 
	 * @param event
	 */
	@FXML
	void onRemoveTipAction(ActionEvent event) {
		listTips.getItems().remove(listTips.getSelectionModel().getSelectedItem());
	}

	public GoalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GoalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listCommands.setCellFactory(TextFieldListCell.forListView());
		listTips.setCellFactory(TextFieldListCell.forListView());

		btnRemoveCommand.disableProperty().bind(listCommands.getSelectionModel().selectedItemProperty().isNull());
		btnRemoveTip.disableProperty().bind(listTips.getSelectionModel().selectedItemProperty().isNull());

		goal.addListener((o, ov, nv) -> onGoalsChanged(o, ov, nv));

		comboShell.getItems().setAll(Shell.values());
	}

	private void onGoalsChanged(ObservableValue<? extends Goal> o, Goal ov, Goal nv) {
		if (ov != null) {
			
			textDescription.textProperty().unbindBidirectional(ov.descriptionProperty());
			textDescription.clear();
			
			comboShell.valueProperty().unbindBidirectional(ov.shellProperty());
			comboShell.getSelectionModel().clearSelection();

			textPWD.textProperty().unbindBidirectional(ov.pathProperty());
			textPWD.clear();
			
			textUser.textProperty().unbindBidirectional(ov.usernameProperty());
			textUser.clear();
			
			listCommands.setItems(FXCollections.observableArrayList());
			listTips.setItems(FXCollections.observableArrayList());
			
		}

		if (nv != null) {
			textDescription.textProperty().bindBidirectional(nv.descriptionProperty());
			comboShell.valueProperty().bindBidirectional(nv.shellProperty());
			textPWD.textProperty().bindBidirectional(nv.pathProperty());
			textUser.textProperty().bindBidirectional(nv.usernameProperty());
			listCommands.setItems(nv.validCommandsProperty());
			listTips.setItems(nv.tipsProperty());
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

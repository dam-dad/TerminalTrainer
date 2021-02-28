package dad.javafx.terminaltrainer.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.model.Goal;
import dad.javafx.terminaltrainer.monitoring.ExecutedCommand;
import dad.javafx.terminaltrainer.monitoring.Monitoring;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class GoalController implements Initializable {

	// Model
	private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();
	private int tryCounter = 1;

	@FXML
	private GridPane view;

	@FXML
	private JFXListView<Goal> listGoals;


	@FXML
	private JFXTextArea textDescription;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		challenge.addListener((o, ov, nv) -> onChallengeChanged(o, ov, nv));
		listGoals.setMouseTransparent(true);
		listGoals.setFocusTraversable(false);
		listGoals.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Goal>() {
			public void changed(ObservableValue<? extends Goal> observable, Goal oldValue, Goal newValue) {
				textDescription.setText(newValue.getDescription());
			}
		});
		
		

		Monitoring.executedCommandsProperty().addListener(new ListChangeListener<ExecutedCommand>() {
			@Override
			public void onChanged(Change<? extends ExecutedCommand> c) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {

						// TODO Auto-generated method stub
						while (c.next()) {
							c.getAddedSubList().stream().forEach(command -> {

								if (checkCommandResult(command)) {
									// CORRECT, CHOOSE ANOTHER EXERCISE
									listGoals.getSelectionModel().getSelectedItem().setDescription("GOAL DONE!");
									listGoals.getSelectionModel().selectNext();
									resetTryCounter();
									//setBackgroundCompletedGoal(command);
								} else {
									tryCounter++;
									if (tryCounter % 4 == 0) {
										// notification goes here
										
									}
								}

							});
						}
					}

				});
				
				
			}
		});
	}

	public boolean checkCommandResult(ExecutedCommand command) {
		boolean result = false;
		Goal currentGoal = listGoals.getSelectionModel().getSelectedItem();
		if (currentGoal.getValidCommands().contains(command.getCommand())
				&& currentGoal.getPath().equals(command.getOldPwd())
				&& currentGoal.getShell().toString().equals(command.getShell().toUpperCase())
				&& currentGoal.getUsername().equals(command.getUsername())) {
			result = true;
		}

		return result;
	}
	
	public void setBackgroundCompletedGoal(ExecutedCommand command) {
		listGoals.setCellFactory(lv -> new ListCell<Goal>() {

            @Override
            protected void updateItem(Goal item, boolean empty) {
                super.updateItem(item, empty);
                if (checkCommandResult(command)) {
                	this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                } else {
                	this.setBackground(Background.EMPTY);
                }
            }
        });
	}

	private void onChallengeChanged(ObservableValue<? extends Challenge> o, Challenge ov, Challenge nv) {
		// TODO Auto-generated method stub
		if (ov != null) {
			listGoals.itemsProperty().unbindBidirectional(ov.goalsProperty());
		}

		if (nv != null) {
			listGoals.itemsProperty().bindBidirectional(nv.goalsProperty());
			listGoals.getSelectionModel().selectFirst();
		}
	}

	public void resetTryCounter() {
		tryCounter = 1;
	}

	public GoalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GoalTabView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}

	public ObjectProperty<Challenge> challengeProperty() {
		return challenge;
	}

	public JFXListView<Goal> getListGoals() {
		return listGoals;
	}

}

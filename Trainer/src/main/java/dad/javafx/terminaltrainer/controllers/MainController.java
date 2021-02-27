package dad.javafx.terminaltrainer.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.model.Goal;
import dad.javafx.terminaltrainer.editor.ui.app.App;
import dad.javafx.terminaltrainer.monitoring.ExecutedCommand;
import dad.javafx.terminaltrainer.monitoring.Monitoring;
import dad.javafx.terminaltrainer.utils.JSONUtils;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {

	private ArrayList<ArrayList<ExecutedCommand>> userResults;
	private Challenge challenge;
	private Goal currentGoal;
	private int selectedIndex;

	@FXML
	private BorderPane view;

	@FXML
	private TextField textChallengeName;

	@FXML
	private TextArea textDescription;

	@FXML
	private ComboBox<Goal> comboGoals;

	@FXML
	private TextArea goalDescription;

	@FXML
	private TextArea goalTips;

	@FXML
	void onGoalSelectedAction(ActionEvent event) {
		currentGoal = comboGoals.getSelectionModel().getSelectedItem();
		selectedIndex = comboGoals.getSelectionModel().getSelectedIndex();
		if (currentGoal != null) {
			goalDescription.setText(currentGoal.getDescription());
			goalTips.setText(currentGoal.getTips().toString());
		} else {
			goalDescription.setText("");
			goalTips.setText("");
		}
	}

	@FXML
	void onDebugAction(ActionEvent event) {
		for (ArrayList<ExecutedCommand> arrayList : userResults) {
			System.out.println(userResults.indexOf(arrayList) + " | " + arrayList.toString());
		}
	}

	@FXML
	void onPrintAction(ActionEvent event) throws IOException {
		// WE HAVE TO CHANGE THIS INTO A PDF, THIS IS ONLY FOR DEBBUGING
		File file = new File("C:\\Users\\abcar\\Desktop\\archivoPrueba.json");
		JSONUtils.toJson(file, userResults);
	}

	@FXML
	void onLoadChallengeAction(ActionEvent event) {
		view.setDisable(false);

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open a challenge file.");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Challenge (*.challenge)", "*.challenge"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All types of files", "."));
		File chFile = fileChooser.showOpenDialog(App.getPrimaryStage());
		if (chFile != null) {
			try {

				challenge = JSONUtils.fromJson(chFile, Challenge.class);

				textChallengeName.setText(challenge.getName());
				textDescription.setText(challenge.getDescription());

				userResults = new ArrayList<>();
				for (Goal goal : challenge.getGoals()) {
					userResults.add(new ArrayList<ExecutedCommand>());
				}

				comboGoals.getItems().addAll(challenge.getGoals());
				comboGoals.getSelectionModel().selectFirst();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainViewTrainer.fxml"));
		loader.setController(this);
		loader.load();
	}

	public BorderPane getView() {
		return view;
	}

	public Challenge getChallenge() {
		return this.challenge;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		Monitoring.executedCommandsProperty().addListener(new ListChangeListener<ExecutedCommand>() {
			@Override
			public void onChanged(Change<? extends ExecutedCommand> c) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (c.next()) {
							c.getAddedSubList().stream().forEach(command -> {
								/*
								 * System.out.println("comando:" +
								 * currentGoal.getValidCommands().contains(command.getCommand()));
								 * System.out.println("PWD: " +
								 * currentGoal.getPath().equals(command.getOldPwd()));
								 * System.out.println("Shell:" +
								 * currentGoal.getShell().equals(command.getShell()));
								 * System.out.println("Username: " +
								 * currentGoal.getUsername().equals(command.getUsername()));
								 */

								userResults.get(selectedIndex).add(command);
								
								if(currentGoal.getShell().toString().equals(command.getShell().toUpperCase())){
									System.out.println("Correcto");
								}else {
									System.out.println("Error");
								}
								System.out.println(currentGoal.getShell().toString() + " " + command.getShell().toUpperCase());

								if (currentGoal.getValidCommands().contains(command.getCommand())
										&& currentGoal.getPath().equals(command.getOldPwd())
								// && currentGoal.getShell().toString().equals(command.getShell().toUpperCase())
										&& currentGoal.getUsername().equals(command.getUsername())) {
									// CORRECT, CHOOSE ANOTHER EXERCISE
									comboGoals.getItems().remove(selectedIndex);
									comboGoals.getSelectionModel().clearSelection();
								}

							});
						}
					}

				});

			}
		});
	}
}

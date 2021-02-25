package dad.javafx.terminaltrainer.controllers;

import java.io.File;
import java.io.IOException;



import dad.javafx.terminaltrainer.cli.ExecutionResult;
import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.ui.app.App;
import dad.javafx.terminaltrainer.utils.JSONUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController {

	// Model
	ExecutionResult executionResult = new ExecutionResult();
	// private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();
	private Challenge challenge = new Challenge();

	@FXML
	private BorderPane view;

	@FXML
    private TextField textChallengeName;

    @FXML
    private TextArea textDescription;

	String path;

	@FXML
	void onLoadChallengeAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open a challenge file.");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Challenge (*.challenge)", "*.challenge"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All types of files", "."));
		File chFile = fileChooser.showOpenDialog(App.getPrimaryStage());
		if (chFile != null) {
			try {
				path = chFile.getAbsolutePath();
				challenge = JSONUtils.fromJson(chFile, Challenge.class);

				textChallengeName.setText(challenge.getName());
				textDescription.setText(challenge.getDescription());

				System.out.println("ChallengeName " + challenge.getName());
				System.out.println("Description: " + challenge.getDescription());
				for (int i = 0; i < challenge.getGoals().size(); i++) {
					System.out.println("Goals: " + challenge.getGoals().get(i));
					for (int j = 0; j < challenge.getGoals().get(i).getValidCommands().size(); j++) {
						System.out.println("Valid commands: " + challenge.getGoals().get(i).getValidCommands().get(j));
					}
				}
				System.out.println("Description: " + challenge.getDescription());
				System.out.println("OS: : " + challenge.getOs());
				
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
	
	/*
	public ObjectProperty<Challenge> getChallenge() {
		return challenge;
	}
	*/

}

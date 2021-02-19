package dad.javafx.terminaltrainer.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.cli.ExecutionResult;
import dad.javafx.terminaltrainer.editor.ui.app.App;
import dad.javafx.terminaltrainer.trainer.model.Challenge;
import dad.javafx.terminaltrainer.utils.JSONUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {
	//Model
	ExecutionResult executionResult = new ExecutionResult();
	private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();

	@FXML
	private BorderPane view;

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
				challenge.set(JSONUtils.fromJson(chFile, Challenge.class));
//				System.out.println("ChallengeName " + challenge.get().getName());
//				System.out.println("Description: " + challenge.get().getDescription());
//				for(int i = 0; i < challenge.get().getGoals().size(); i++) {
//					System.out.println("Goals: " + challenge.get().getGoals().get(i));
//					for(int j = 0; j < challenge.get().getGoals().get(i).getValidCommands().size(); j++) {
//						System.out.println("Valid commands: "+  challenge.get().getGoals().get(i).getValidCommands().get(j));
//					}
//				}
//				System.out.println("Description: " + challenge.get().getDescription());
//				System.out.println("OS: : " + challenge.get().getOs());
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}

	public BorderPane getView() {
		return view;
	}

}

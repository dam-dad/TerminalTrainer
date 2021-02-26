package dad.javafx.terminaltrainer.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import dad.javafx.terminaltrainer.cli.ExecutionResult;
import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.ui.app.App;
import dad.javafx.terminaltrainer.monitoring.ExecutedCommand;
import dad.javafx.terminaltrainer.monitoring.Monitoring;
import dad.javafx.terminaltrainer.utils.JSONUtils;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {

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
				/*
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
				*/
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
				while (c.next()) {
					System.out.println("ultimo comando :");
					c.getAddedSubList().stream().forEach( command -> {
						System.out.println("Comando: " + command.getCommand());
						System.out.println("Pwd: " + command.getPwd());
					});
					
				}
				
				/*
				System.out.println("Comando ejecutado: " + comando.getCommand());

				for (int i = 0; i < challenge.getGoals().size(); i++) {
					for (int j = 0; j < challenge.getGoals().get(i).getValidCommands().size(); j++) {

						System.out.println(challenge.getGoals().get(i).getValidCommands().get(j));
						if (comando.getCommand()
								.equals(challenge.getGoals().get(i).getValidCommands().get(j))) {

							System.out.println("MU BIEN MI NIÑO");

						} else {

							System.out.println("AY NO, NO PUEDE SEH");

						}
					}
				}
				
				*/

			}
		});

		/*
		new Thread(() -> {
			Sleep.minutes(5);
			Monitoring.stop();
			if (Config.CONFIG.isEnabled())
				Config.CONFIG.disable();
		}).start();
		*/
	}
	
	

}

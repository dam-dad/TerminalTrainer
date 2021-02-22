package dad.javafx.terminaltrainer.ui.app;

import dad.javafx.terminaltrainer.cli.ExecutionResult;
import dad.javafx.terminaltrainer.config.Config;
import dad.javafx.terminaltrainer.controllers.MainController;
import dad.javafx.terminaltrainer.editor.model.Goal;
import dad.javafx.terminaltrainer.monitoring.ExecutedCommand;
import dad.javafx.terminaltrainer.monitoring.Monitoring;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dad.javafx.terminaltrainer.cli.ExecutionResult;
import dad.javafx.terminaltrainer.config.Config;
import dad.javafx.terminaltrainer.controllers.MainController;
import dad.javafx.terminaltrainer.monitoring.ExecutedCommand;
import dad.javafx.terminaltrainer.monitoring.Monitoring;
import dad.javafx.terminaltrainer.trainer.model.Challenge;
import dad.javafx.terminaltrainer.ui.app.App;
import dad.javafx.terminaltrainer.utils.Sleep;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;

public class App extends Application {
	MainController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new MainController();

		Scene scene = new Scene(controller.getView());
		Challenge challenge = controller.getChallenge();

		if (!Config.CONFIG.isEnabled())
			Config.CONFIG.enable();

		Monitoring.start();
		ExecutionResult executionResult = new ExecutionResult();

		Monitoring.getExecutedCommands().addListener(new ListChangeListener<ExecutedCommand>() {
			@Override
			public void onChanged(Change<? extends ExecutedCommand> c) {
				while (c.next()) {
					c.getAddedSubList().stream().forEach(System.out::println);
				}

				/*
					 for (int i = 0; i < challenge.getGoals().size(); i++) {
						for (int j = 0; j < challenge.getGoals().get(i).getValidCommands().size(); j++) {
	
							System.out.println(challenge.getGoals().get(i).getValidCommands().get(j));
							if (executionResult.getExecutedCommand()
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

		new Thread(() -> {
			Sleep.minutes(5);
			Monitoring.stop();
			if (Config.CONFIG.isEnabled())
				Config.CONFIG.disable();
		}).start();

		primaryStage.setTitle("Trainer");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}

package dad.javafx.terminaltrainer.ui.app;

import dad.javafx.terminaltrainer.cli.ExecutionResult;
import dad.javafx.terminaltrainer.config.Config;
import dad.javafx.terminaltrainer.controllers.MainController;
import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.monitoring.ExecutedCommand;
import dad.javafx.terminaltrainer.monitoring.Monitoring;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dad.javafx.terminaltrainer.ui.app.App;
import dad.javafx.terminaltrainer.utils.Sleep;

public class App extends Application {
	MainController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new MainController();

		Scene scene = new Scene(controller.getView());
		Challenge challenge = controller.getChallenge();

		challenge.setDescription(controller.getChallenge().getDescription());

		if (!Config.CONFIG.isEnabled())
			Config.CONFIG.enable();

		Monitoring.start();
		ExecutedCommand comando = new ExecutedCommand();

		Monitoring.getExecutedCommands().addListener(new ListChangeListener<ExecutedCommand>() {
			@Override
			public void onChanged(Change<? extends ExecutedCommand> c) {
				while (c.next()) {
					c.getAddedSubList().stream().forEach(System.out::println);
					comando.setCommand(c.getList().get(c.getList().size() - 1).getCommand());
				}

				System.out.println("Comando ejecutado: " + comando.getCommand());

				for (int i = 0; i < controller.getChallenge().getGoals().size(); i++) {
					for (int j = 0; j < controller.getChallenge().getGoals().get(i).getValidCommands().size(); j++) {

						System.out.println(controller.getChallenge().getGoals().get(i).getValidCommands().get(j));
						if (comando.getCommand()
								.equals(controller.getChallenge().getGoals().get(i).getValidCommands().get(j))) {

							System.out.println("MU BIEN MI NIÑO");

						} else {

							System.out.println("AY NO, NO PUEDE SEH");

						}
					}
				}

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

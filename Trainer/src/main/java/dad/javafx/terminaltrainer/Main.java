package dad.javafx.terminaltrainer;

import java.io.IOException;

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

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {

		if (!Config.CONFIG.isEnabled()) Config.CONFIG.enable();

		Monitoring.start();
		
//		MainController controller = new MainController();
		ExecutionResult executionResult = new ExecutionResult();
		ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();
		
//		challenge.set(controller.getChallenge().get());

		Monitoring.getExecutedCommands().addListener(new ListChangeListener<ExecutedCommand>() {
			@Override
			public void onChanged(Change<? extends ExecutedCommand> c) {
				while (c.next()) {
					c.getAddedSubList().stream().forEach(System.out::println);
				}
				
				for(int i=0; i<challenge.get().getGoals().size(); i++) {
					for(int j=0; j<challenge.get().getGoals().get(i).getValidCommands().size(); j++) {
						
						if( executionResult.getExecutedCommand().equals( challenge.get().getGoals().get(i).getValidCommands().get(j) ) ) {
							
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
			if (Config.CONFIG.isEnabled()) Config.CONFIG.disable();
		}).start();
		
		App.main(args);
	
//		Config.CONFIG.disable();
		
	}

}

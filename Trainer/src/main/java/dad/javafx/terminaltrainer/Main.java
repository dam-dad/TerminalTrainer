package dad.javafx.terminaltrainer;

import dad.javafx.terminaltrainer.config.Config;
import dad.javafx.terminaltrainer.monitoring.ExecutedCommand;
import dad.javafx.terminaltrainer.monitoring.Monitoring;
import dad.javafx.terminaltrainer.utils.Sleep;
import javafx.collections.ListChangeListener;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		if (!Config.CONFIG.isEnabled()) Config.CONFIG.enable();

		Monitoring.start();

		Monitoring.getExecutedCommands().addListener(new ListChangeListener<ExecutedCommand>() {
			@Override
			public void onChanged(Change<? extends ExecutedCommand> c) {
				while (c.next()) {
					c.getAddedSubList().stream().forEach(System.out::println);
				}
			}
		});

		new Thread(() -> {
			Sleep.minutes(1);
			Monitoring.stop();
			if (Config.CONFIG.isEnabled()) Config.CONFIG.disable();
		}).start();
	
//		Config.CONFIG.disable();
		
	}

}

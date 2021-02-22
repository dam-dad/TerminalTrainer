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
		App.main(args);	
	}

}

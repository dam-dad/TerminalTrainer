package dad.javafx.terminaltrainer.cli;

public class BASHCommand extends Command {
	
	private static final String BASH = "bash";
	
	public BASHCommand() {
		this(null);
	}

	public BASHCommand(String command) {
		super(BASH, "-c", command);
	}
	
}

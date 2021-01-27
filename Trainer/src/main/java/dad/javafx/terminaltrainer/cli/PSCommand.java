package dad.javafx.terminaltrainer.cli;

public class PSCommand extends Command {

	private static final String PS = "powershell";

	public PSCommand() {
		this(null);
	}

	public PSCommand(String cmdlet) {
		super(PS, "-NoProfile", "-WindowStyle", "Hidden", "-Command", cmdlet);
	}

}

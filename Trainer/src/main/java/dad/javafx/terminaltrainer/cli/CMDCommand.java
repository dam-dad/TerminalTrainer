package dad.javafx.terminaltrainer.cli;

public class CMDCommand extends Command {

	private static final String CMD = "cmd";

	public CMDCommand() {
		this(null);
	}

	public CMDCommand(String command) {
		super(CMD, "/d", "/c", command);
	}

}

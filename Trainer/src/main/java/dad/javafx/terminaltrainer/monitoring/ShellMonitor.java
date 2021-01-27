package dad.javafx.terminaltrainer.monitoring;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class ShellMonitor extends Monitor {
	
	public static final String APPNAME		= ResourceBundle.getBundle("main").getString("app.name");
	
	public static final String COMMAND 		= "command";
	public static final String USERNAME 	= "username";
	public static final String TIMESTAMP 	= "timestamp";
	public static final String PWD 			= "pwd";
	public static final String OLDPWD 		= "oldpwd";
	public static final String SHELL 		= "shell";
	
	private List<String> excludedCommands;
	
	public ShellMonitor(String name) {
		super(name);
		this.excludedCommands = new ArrayList<>();
	}
		
	public List<String> getExcludedCommands() {
		return excludedCommands;
	}

}

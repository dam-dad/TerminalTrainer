package dad.javafx.terminaltrainer.config;

import dad.javafx.terminaltrainer.cli.PSScript;

public class WindowsConfig extends Config {
	
	private static final String SCRIPT_NAME = "config-windows-monitoring.ps1";
	
	@Override
	public boolean isEnabled() {
		return new PSScript(getScript(SCRIPT_NAME), "-Test").execute().getExitValue() == 0;
	}
	
	@Override
	public boolean enable() {
		return new PSScript(true, getScript(SCRIPT_NAME), "-Enable").execute().getExitValue() == 0;
	}
	
	@Override
	public boolean disable() {
		return new PSScript(true, getScript(SCRIPT_NAME), "-Disable").execute().getExitValue() == 0;
	}

}

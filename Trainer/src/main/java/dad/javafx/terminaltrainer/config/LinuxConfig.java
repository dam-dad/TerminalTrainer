package dad.javafx.terminaltrainer.config;

import dad.javafx.terminaltrainer.cli.BASHScript;

public class LinuxConfig extends Config {
	
	private static final String SCRIPT_NAME = "config-linux-monitoring.sh";
	
	@Override
	public boolean isEnabled() {
		return new BASHScript(getScript(SCRIPT_NAME), "--test").execute().getExitValue() == 0;
	}
	
	@Override
	public boolean enable() {
		return new BASHScript(true, getScript(SCRIPT_NAME), "--enable").execute().getExitValue() == 0;
	}
	
	@Override
	public boolean disable() {
		return new BASHScript(true, getScript(SCRIPT_NAME), "--disable").execute().getExitValue() == 0;
	}

}

package dad.javafx.terminaltrainer.cli;

import java.io.File;

public class BASHScript extends Command {
	
	private static final String BASH = "bash";
	
	public BASHScript() {
		this("");
	}

	public BASHScript(String scriptPath, String ...params) {
		this(false, scriptPath, params);
	}

	public BASHScript(File scriptFile, String ...params) {
		this(false, scriptFile.getAbsolutePath(), params);
	}
	
	public BASHScript(boolean asRoot, File scriptFile, String ...params) {
		this(asRoot, scriptFile.getAbsolutePath(), params);
	}

	public BASHScript(boolean asRoot, String scriptPath, String ... params) {
		super(BASH, scriptPath);
		getArguments().addAll(params);
	}

}

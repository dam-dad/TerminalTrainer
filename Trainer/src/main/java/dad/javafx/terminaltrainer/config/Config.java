package dad.javafx.terminaltrainer.config;

import java.io.File;

import org.apache.commons.lang.SystemUtils;

import dad.javafx.terminaltrainer.utils.ResourceUtils;

public abstract class Config {

	public static final Config CONFIG = newConfig();
	
	private static Config newConfig() {
		if (SystemUtils.IS_OS_WINDOWS) return new WindowsConfig();
		if (SystemUtils.IS_OS_LINUX) return new LinuxConfig();
		if (SystemUtils.IS_OS_MAC_OSX) return new MacConfig(); // TODO comprobar si la configuración de Linux sirve en Mac OS
		return null;
	}
	
	protected File getScript(String scriptName) {
		try {
			File script = new File(System.getProperty("java.io.tmpdir"), scriptName);
			ResourceUtils.copyResourceToFile("/config/" + scriptName, script);
			return script;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public abstract boolean isEnabled();
	public abstract boolean enable();
	public abstract boolean disable();

}

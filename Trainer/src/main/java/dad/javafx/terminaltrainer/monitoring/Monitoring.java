package dad.javafx.terminaltrainer.monitoring;

import java.time.LocalDateTime;

import org.apache.commons.lang.SystemUtils;

import dad.javafx.terminaltrainer.config.Config;
import dad.javafx.terminaltrainer.monitoring.linux.LinuxMonitor;
import dad.javafx.terminaltrainer.monitoring.windows.WindowsMonitor;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class Monitoring {
	
	private static ListProperty<ExecutedCommand> COMMANDS = new SimpleListProperty<>(FXCollections.observableArrayList());
	private static ShellMonitor MONITOR;
	
	private static ShellMonitor newMonitor() {
		ShellMonitor shellMonitor = null;
		if (SystemUtils.IS_OS_WINDOWS) shellMonitor = new WindowsMonitor();
		if (SystemUtils.IS_OS_LINUX) shellMonitor = new LinuxMonitor();
		if (SystemUtils.IS_OS_MAC_OSX) shellMonitor = null; // TODO comprobar si el monitor de Linux vale en MacOSX		
		return shellMonitor;
	}
	
	public static boolean isMonitoringEnabled() {
		System.out.print("Checking terminal monitoring status ... ");		
		boolean enabled = Config.CONFIG.isEnabled();
		if (enabled) {
	        System.out.println("[ENABLED]");
		} else {
	        System.out.println("[DISABLED]");
		}
		return enabled;
	}
	
	public static void start() {
		stop();
		MONITOR = newMonitor();
		MONITOR.addListener((monitor, data) -> {
			ExecutedCommand cmd = new ExecutedCommand();
			cmd.setShell((String) data.get(ShellMonitor.SHELL));
			cmd.setCommand((String) data.get(ShellMonitor.COMMAND));
			cmd.setUsername((String) data.get(ShellMonitor.USERNAME));
			cmd.setTimestamp((LocalDateTime) data.get(ShellMonitor.TIMESTAMP));
			cmd.setPwd((String) data.get(ShellMonitor.PWD));
			cmd.setOldPwd((String) data.get(ShellMonitor.OLDPWD));
			COMMANDS.add(cmd);
		});
		MONITOR.start();
	}
	
	public static void stop() {
		if (isRunning()) {
			MONITOR.requestStop();
		}
	}
	
	public static boolean isRunning() {
		return (MONITOR != null && MONITOR.isAlive());
	}

	public static ListProperty<ExecutedCommand> executedCommandsProperty() {
		return COMMANDS;
	}

}

package dad.javafx.terminaltrainer.monitoring.linux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dad.javafx.terminaltrainer.cli.BASHCommand;
import dad.javafx.terminaltrainer.cli.Command;
import dad.javafx.terminaltrainer.cli.ExecutionResult;
import dad.javafx.terminaltrainer.monitoring.ShellMonitor;
import dad.javafx.terminaltrainer.utils.StreamGobbler;

public class LinuxMonitor extends ShellMonitor {
	
	private static final String TAIL_SYSLOG = "tail -n 0 -f /var/log/syslog";
	
	// sample: "Apr  7 01:23:45 ssv-pc $APPNAME: bash:username:pwd:oldpwd:tail -n 0 -f /var/log/syslog"
	private Pattern pattern = Pattern.compile("^(\\w+)\\s+(\\d+) (\\d+:\\d+:\\d+) (.+) " + APPNAME + ": ([^:]+):([^:]+):([^:]*):([^:]*)?:(.*)$");
	
	private Command command;
	
	public LinuxMonitor() {
		super("Linux Shell Monitor");
		this.command = new BASHCommand(TAIL_SYSLOG);
	}
	
	@Override
	public void doWork() {
			
		ExecutionResult result = command.execute(false);
		
		if (result.getExitValue() != 0) {
			System.err.println(result.getError());
			return;
		}
		
		StreamGobbler output = new StreamGobbler(result.getOutputStream(), this::parseLine); 
		StreamGobbler error = new StreamGobbler(result.getErrorStream(), System.err::println); 
		
		output.start();
		error.start();
		
		while (!isStopped() && output.isAlive() && error.isAlive()) {
			// do nothing
		}
		
		output.requestStop();
		error.requestStop();

	}

	private void parseLine(String line) {
		
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			String month = matcher.group(1);
			String day = matcher.group(2);
			String time = matcher.group(3);
			String hostname = matcher.group(4);
			String shell = matcher.group(5);
			String username = matcher.group(6);
			String pwd = matcher.group(7);
			String oldpwd = matcher.group(8);
			String command = matcher.group(9);
			
			if (!getExcludedCommands().contains(command)) {
			
				LocalDateTime timestamp = LocalDateTime.of(LocalDate.now(), LocalTime.parse(time));
				
				Map<String, Object> data = new HashMap<>();
				data.put(SHELL, shell);
				data.put(COMMAND, command);
				data.put(USERNAME, username);
				data.put(TIMESTAMP, timestamp);
				data.put(PWD, pwd);
				data.put(OLDPWD, oldpwd);
				data.put("month", month);
				data.put("day", day);
				data.put("hostname", hostname);
				
				notifyAll(data);
				
			}
			
		}
	}

}

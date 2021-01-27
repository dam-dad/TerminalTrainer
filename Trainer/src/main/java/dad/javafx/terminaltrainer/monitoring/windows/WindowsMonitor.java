package dad.javafx.terminaltrainer.monitoring.windows;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dad.javafx.terminaltrainer.cli.CMDCommand;
import dad.javafx.terminaltrainer.cli.Command;
import dad.javafx.terminaltrainer.cli.ExecutionResult;
import dad.javafx.terminaltrainer.monitoring.ShellMonitor;
import dad.javafx.terminaltrainer.utils.Chronometer;
import dad.javafx.terminaltrainer.utils.DateTimeUtils;
import dad.javafx.terminaltrainer.utils.Sleep;
import dad.javafx.terminaltrainer.utils.XMLUtils;

public class WindowsMonitor extends ShellMonitor {
	
	private static final long DELAY = 1000L;
	private static final String QUERY_EVENTS_CMD = "wevtutil query-events \"Application\" /q:\"*"
			+ "[System"
				+ "[TimeCreated"
				+	 "[@SystemTime>'${TIME}']"
				+ "]"
				+ "[Provider"
				+	 "[@Name='${SOURCE}']"
				+ "]"
			+ "]\"";
	
	// pattern : shell:username:'pwd':'oldpwd':command
	// sample  : powershell:Fran:'C:\Users':'C:\Users\Fran':cd ..
	private Pattern pattern = Pattern.compile("^([^:]*):([^:]*):'([^']*)':'([^']*)':(.*)$");

	private long delay;
	private Command command;
	
	public WindowsMonitor(long delay) {
		super("Windows Monitor");
		this.delay = delay;
		this.command = new CMDCommand(QUERY_EVENTS_CMD);
		getExcludedCommands().add("");
	}
	
	public WindowsMonitor() {
		this(DELAY);
	}

	@Override
	public void doWork() {
		ZonedDateTime dateTime = ZonedDateTime.now(ZoneOffset.UTC);

		Chronometer chrono = new Chronometer();
		
		do {

			chrono.init();
			
			Map<String, Object> data = new HashMap<>();
			data.put("TIME", dateTime.toString());
			data.put("SOURCE", APPNAME);
			
			ExecutionResult result = command.execute(data);
			
			if (result.getOutput() != null && !result.getOutput().isEmpty()) {
				
				String xml = "<Events>" + result.getOutput() + "</Events>";
				
				Document doc = XMLUtils.stringToDocument(xml);
				NodeList nodes = doc.getElementsByTagName("Event");
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					
					String xmlDateTime = XMLUtils.searchAttribute(node, "System/TimeCreated", "SystemTime");
					ZonedDateTime timestamp = DateTimeUtils.xmlInstantToZonedDateTime(xmlDateTime);

					String message = XMLUtils.searchText(node, "EventData/Data");

					Matcher matcher = pattern.matcher(message);
					if (matcher.find()) {
	
						String shell = matcher.group(1);
						String username = matcher.group(2);
						String pwd = matcher.group(3);
						String oldpwd = matcher.group(4);
						String command = matcher.group(5);
						LocalDateTime localTimestamp = LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
																	
						if (!getExcludedCommands().contains(command)) {
							
							Map<String, Object> event = new HashMap<>();
							event.put(SHELL, shell);
							event.put(COMMAND, command);
							event.put(USERNAME, username);
							event.put(TIMESTAMP, localTimestamp);
							event.put(PWD, pwd);
							event.put(OLDPWD, oldpwd);
							
							notifyAll(event);
							
						}
						
					}

					dateTime = timestamp;

				}
				
			}
			
			Sleep.millis(delay - chrono.stop());
			
		} while (!isStopped());
		
	}
	
}

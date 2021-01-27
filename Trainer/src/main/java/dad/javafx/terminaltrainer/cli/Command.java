package dad.javafx.terminaltrainer.cli;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import dad.javafx.terminaltrainer.utils.Chronometer;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Command {
	
	private StringProperty executable = new SimpleStringProperty();
	private ListProperty<String> arguments = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ReadOnlyObjectWrapper<ExecutionResult> result = new ReadOnlyObjectWrapper<>();

	public Command(String executable, String ... arguments) {
		setExecutable(executable);
		getArguments().setAll(arguments); 
	}

	public Command() {
		this(null);
	}

	public StringProperty executableProperty() {
		return this.executable;
	}

	public String getExecutable() {
		return this.executableProperty().get();
	}

	public void setExecutable(final String command) {
		this.executableProperty().set(command);
	}
	
	public final ListProperty<String> argumentsProperty() {
		return this.arguments;
	}
	
	public final ObservableList<String> getArguments() {
		return this.argumentsProperty().get();
	}

	public final void setArguments(final ObservableList<String> arguments) {
		this.argumentsProperty().set(arguments);
	}

	public final ReadOnlyObjectProperty<ExecutionResult> resultProperty() {
		return this.result.getReadOnlyProperty();
	}

	public final ExecutionResult getResult() {
		return this.resultProperty().get();
	}

	public ExecutionResult execute() {
		return execute(Collections.emptyMap());
	}

	public ExecutionResult execute(boolean waitFor) {
		return execute(waitFor, Collections.emptyMap());
	}

	public ExecutionResult execute(Map<String, Object> data) {
		return execute(true, data);
	}

	public ExecutionResult execute(boolean waitFor, Map<String, Object> data) {
		ExecutionResult result = new ExecutionResult();
		
		try {

			final Chronometer chrono = new Chronometer();

			result.setExecutionTime(LocalDateTime.now());
			
			Map<String, Object> fullData = new HashMap<>();
			fullData.putAll(System.getenv());
			fullData.putAll(data);
			
			CommandLine cmdLine = new CommandLine(getExecutable());
			for (String argument : getArguments()) {
				cmdLine.addArgument(argument, false);
			}
			cmdLine.setSubstitutionMap(fullData);
			
			result.setParams(StringUtils.join(cmdLine.getArguments(), " "));
			result.setExecutedCommand(cmdLine.getExecutable() + " " + result.getParams());
			
			DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler() {
				private void update(int exitValue) {
					result.setExitValue(exitValue);
					result.setDuration(Duration.ofMillis(chrono.stop()));
				}
				public void onProcessComplete(int exitValue) {
					super.onProcessComplete(exitValue);
					update(exitValue);
				}
				public void onProcessFailed(ExecuteException e) {
					super.onProcessFailed(e);
					update(getExitValue());
				}
			};
			
			PipedOutputStream output = new PipedOutputStream();
			PipedOutputStream error = new PipedOutputStream();
			
			PumpStreamHandler streamHandler = new PumpStreamHandler(output, error);

			result.setOutputStream(new PipedInputStream(output));
			result.setErrorStream(new PipedInputStream(error));

			Executor executor = new DefaultExecutor();
			executor.setStreamHandler(streamHandler);
			executor.execute(cmdLine, handler);

			if (waitFor) {
				result.setOutput(IOUtils.toString(result.getOutputStream(), Charset.defaultCharset()).trim());
				result.setError(IOUtils.toString(result.getErrorStream(), Charset.defaultCharset()).trim());									
				handler.waitFor();
			}
			
		} catch (Exception e) {
			
			result.setError(e.getMessage());
			result.setExitValue(-1);
			e.printStackTrace();
			
		} finally {
			
			this.result.set(result);
			
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return getExecutable();
	}

}

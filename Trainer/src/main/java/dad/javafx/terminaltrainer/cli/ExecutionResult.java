package dad.javafx.terminaltrainer.cli;

import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Command execution result
 */
public class ExecutionResult {

	private IntegerProperty exitValue = new SimpleIntegerProperty(0);
	private ObjectProperty<InputStream> outputStream = new SimpleObjectProperty<>();
	private ObjectProperty<InputStream> errorStream = new SimpleObjectProperty<>();
	private StringProperty output = new SimpleStringProperty();
	private StringProperty error = new SimpleStringProperty();
	private StringProperty executedCommand = new SimpleStringProperty();
	private StringProperty params = new SimpleStringProperty();
	private ObjectProperty<LocalDateTime> executionTime = new SimpleObjectProperty<>();
	private ObjectProperty<Duration> duration = new SimpleObjectProperty<>();

	public final IntegerProperty exitValueProperty() {
		return this.exitValue;
	}

	public final int getExitValue() {
		return this.exitValueProperty().get();
	}

	public final void setExitValue(final int exitValue) {
		this.exitValueProperty().set(exitValue);
	}

	public final StringProperty executedCommandProperty() {
		return this.executedCommand;
	}

	public final String getExecutedCommand() {
		return this.executedCommandProperty().get();
	}

	public final void setExecutedCommand(final String executedCommand) {
		this.executedCommandProperty().set(executedCommand);
	}

	public final StringProperty paramsProperty() {
		return this.params;
	}

	public final String getParams() {
		return this.paramsProperty().get();
	}

	public final void setParams(final String params) {
		this.paramsProperty().set(params);
	}

	public final ObjectProperty<LocalDateTime> executionTimeProperty() {
		return this.executionTime;
	}

	public final LocalDateTime getExecutionTime() {
		return this.executionTimeProperty().get();
	}

	public final void setExecutionTime(final LocalDateTime executionTime) {
		this.executionTimeProperty().set(executionTime);
	}

	public final ObjectProperty<Duration> durationProperty() {
		return this.duration;
	}

	public final Duration getDuration() {
		return this.durationProperty().get();
	}

	public final void setDuration(final Duration duration) {
		this.durationProperty().set(duration);
	}

	public final ObjectProperty<InputStream> outputStreamProperty() {
		return this.outputStream;
	}

	public final InputStream getOutputStream() {
		return this.outputStreamProperty().get();
	}

	public final void setOutputStream(final InputStream outputStream) {
		this.outputStreamProperty().set(outputStream);
	}

	public final ObjectProperty<InputStream> errorStreamProperty() {
		return this.errorStream;
	}

	public final InputStream getErrorStream() {
		return this.errorStreamProperty().get();
	}

	public final void setErrorStream(final InputStream errorStream) {
		this.errorStreamProperty().set(errorStream);
	}

	public final StringProperty outputProperty() {
		return this.output;
	}

	public final String getOutput() {
		return this.outputProperty().get();
	}

	public final void setOutput(final String output) {
		this.outputProperty().set(output);
	}

	public final StringProperty errorProperty() {
		return this.error;
	}

	public final String getError() {
		return this.errorProperty().get();
	}

	public final void setError(final String error) {
		this.errorProperty().set(error);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ExecutedCommand : " + getExecutedCommand() + "\n");
		buffer.append("Params          : " + getParams() + "\n");
		buffer.append("ExecutionTime   : " + getExecutionTime() + "\n");
		buffer.append("Duration        : " + (getDuration() != null ? getDuration().toMillis() + "ms" : "" ) + "\n");
		buffer.append("ExitValue       : " + getExitValue() + "\n");
		buffer.append("Output:\n" + getOutput() + "\n");
		buffer.append("Error:\n" + getError());
		return buffer.toString();
	}
	
	

}

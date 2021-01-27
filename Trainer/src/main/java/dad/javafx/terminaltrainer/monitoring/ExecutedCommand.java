package dad.javafx.terminaltrainer.monitoring;

import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ExecutedCommand {

	private StringProperty shell = new SimpleStringProperty();
	private StringProperty command = new SimpleStringProperty();
	private StringProperty username = new SimpleStringProperty();
	private ObjectProperty<LocalDateTime> timestamp = new SimpleObjectProperty<>();
	private StringProperty pwd = new SimpleStringProperty();
	private StringProperty oldPwd = new SimpleStringProperty();

	public final StringProperty shellProperty() {
		return this.shell;
	}

	public final String getShell() {
		return this.shellProperty().get();
	}

	public final void setShell(final String shell) {
		this.shellProperty().set(shell);
	}

	public final StringProperty commandProperty() {
		return this.command;
	}

	public final String getCommand() {
		return this.commandProperty().get();
	}

	public final void setCommand(final String command) {
		this.commandProperty().set(command);
	}

	public final StringProperty usernameProperty() {
		return this.username;
	}

	public final String getUsername() {
		return this.usernameProperty().get();
	}

	public final void setUsername(final String username) {
		this.usernameProperty().set(username);
	}

	public final ObjectProperty<LocalDateTime> timestampProperty() {
		return this.timestamp;
	}

	public final LocalDateTime getTimestamp() {
		return this.timestampProperty().get();
	}

	public final void setTimestamp(final LocalDateTime timestamp) {
		this.timestampProperty().set(timestamp);
	}

	public final StringProperty pwdProperty() {
		return this.pwd;
	}

	public final String getPwd() {
		return this.pwdProperty().get();
	}

	public final void setPwd(final String pwd) {
		this.pwdProperty().set(pwd);
	}

	public final StringProperty oldPwdProperty() {
		return this.oldPwd;
	}

	public final String getOldPwd() {
		return this.oldPwdProperty().get();
	}

	public final void setOldPwd(final String oldPwd) {
		this.oldPwdProperty().set(oldPwd);
	}
	
	@Override
	public String toString() {
		return "shell=" + getShell() + ", username=" + getUsername() + ", command=" + getCommand();
	}

}

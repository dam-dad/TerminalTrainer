package dad.javafx.terminaltrainer.editor.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Goal {

	private ObjectProperty<Shell> shell = new SimpleObjectProperty<>();
	private StringProperty description = new SimpleStringProperty();
	private StringProperty path = new SimpleStringProperty();
	private StringProperty username = new SimpleStringProperty();
	private ListProperty<String> validCommands = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private ListProperty<String> tips = new SimpleListProperty<String>(FXCollections.observableArrayList());

	public final ObjectProperty<Shell> shellProperty() {
		return this.shell;
	}

	public final Shell getShell() {
		return this.shellProperty().get();
	}

	public final void setShell(final Shell shell) {
		this.shellProperty().set(shell);
	}

	public final StringProperty descriptionProperty() {
		return this.description;
	}

	public final String getDescription() {
		return this.descriptionProperty().get();
	}

	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
	}

	public final StringProperty pathProperty() {
		return this.path;
	}

	public final String getPath() {
		return this.pathProperty().get();
	}

	public final void setPath(final String path) {
		this.pathProperty().set(path);
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

	public final ListProperty<String> validCommandsProperty() {
		return this.validCommands;
	}

	public final ObservableList<String> getValidCommands() {
		return this.validCommandsProperty().get();
	}

	public final void setValidCommands(final ObservableList<String> validCommands) {
		this.validCommandsProperty().set(validCommands);
	}

	public final ListProperty<String> tipsProperty() {
		return this.tips;
	}

	public final ObservableList<String> getTips() {
		return this.tipsProperty().get();
	}

	public final void setTips(final ObservableList<String> tips) {
		this.tipsProperty().set(tips);
	}
	
	/**
	 * Overrides toString method to print its values.
	 */
	@Override
	public String toString() {
		return "- GOAL: " + this.description.get();
	}
	
	

}

package dad.javafx.terminaltrainer.informes;

import dad.javafx.terminaltrainer.editor.model.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class Challenges {
	private StringProperty name = new SimpleStringProperty();
	private StringProperty description = new SimpleStringProperty();
	// private ListProperty<Goal> goals = new
	// SimpleListProperty<Goal>(FXCollections.observableArrayList());

	public Challenges() {
	}

	public Challenges(StringProperty name, StringProperty description) {
		super();
		this.name = name;
		this.description = description;
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final String name) {
		this.nameProperty().set(name);
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

}

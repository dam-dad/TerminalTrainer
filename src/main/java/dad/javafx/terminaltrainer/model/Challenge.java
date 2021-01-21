package dad.javafx.terminaltrainer.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Challenge {

	private StringProperty name = new SimpleStringProperty();
	private StringProperty description = new SimpleStringProperty();
	private ListProperty<Goal> goals = new SimpleListProperty<Goal>(FXCollections.observableArrayList());

	public StringProperty nameProperty() {
		return this.name;
	}

	public String getName() {
		return this.nameProperty().get();
	}

	public void setName(final String name) {
		this.nameProperty().set(name);
	}

	public ListProperty<Goal> goalsProperty() {
		return this.goals;
	}

	public ObservableList<Goal> getGoals() {
		return this.goalsProperty().get();
	}

	public void setGoals(final ObservableList<Goal> goals) {
		this.goalsProperty().set(goals);
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

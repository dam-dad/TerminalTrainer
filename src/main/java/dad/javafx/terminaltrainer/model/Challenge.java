package dad.javafx.terminaltrainer.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Challenge {

	private StringProperty name = new SimpleStringProperty();
	private StringProperty descripcion = new SimpleStringProperty();
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

	public StringProperty descripcionProperty() {
		return this.descripcion;
	}

	public String getDescripcion() {
		return this.descripcionProperty().get();
	}

	public void setDescripcion(final String descripcion) {
		this.descripcionProperty().set(descripcion);
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

}

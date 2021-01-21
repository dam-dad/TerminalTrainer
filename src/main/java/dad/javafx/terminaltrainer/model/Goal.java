package dad.javafx.terminaltrainer.model;

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
	private ObjectProperty<OS> operativeSystem = new SimpleObjectProperty<>();
	private StringProperty description = new SimpleStringProperty();
	private StringProperty rute = new SimpleStringProperty();
	private StringProperty user = new SimpleStringProperty();
	private ListProperty<String> commands = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private ListProperty<String> tips = new SimpleListProperty<String>(FXCollections.observableArrayList());

	public final StringProperty descriptionProperty() {
		return this.description;
	}

	public final String getDescription() {
		return this.descriptionProperty().get();
	}

	public final void setDescription(final String descripcion) {
		this.descriptionProperty().set(descripcion);
	}

	public final StringProperty ruteProperty() {
		return this.rute;
	}

	public final String getRute() {
		return this.ruteProperty().get();
	}

	public final void setRuta(final String rute) {
		this.ruteProperty().set(rute);
	}

	public final StringProperty userProperty() {
		return this.user;
	}

	public final String getUser() {
		return this.userProperty().get();
	}

	public final void setUsuario(final String user) {
		this.userProperty().set(user);
	}

	public final ListProperty<String> commandsProperty() {
		return this.commands;
	}

	public final ObservableList<String> getComandos() {
		return this.commandsProperty().get();
	}

	public final void setComandos(final ObservableList<String> commands) {
		this.commandsProperty().set(commands);
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

	public ObjectProperty<Shell> shellProperty() {
		return shell;
	}

	public Shell getShell() {
		return this.shellProperty().get();
	}

	public void setShell(Shell shell) {
		this.shell.set(shell);
	}

	public final ObjectProperty<OS> operativeSystemProperty() {
		return this.operativeSystem;
	}

	public final OS getOperativeSystem() {
		return this.operativeSystemProperty().get();
	}

	public final void setOperativeSystem(final OS operativeSystem) {
		this.operativeSystemProperty().set(operativeSystem);
	}

	public static void main(String[] args) {
		/*
		 * Goal goal = new Goal(); goal.setShell(Shell.CMD);
		 */
	}

}

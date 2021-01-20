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
	private StringProperty descripcion = new SimpleStringProperty();
	private ObjectProperty<Shell> shell = new SimpleObjectProperty<Shell>();
	private StringProperty ruta = new SimpleStringProperty();
	private StringProperty usuario = new SimpleStringProperty();
	private ListProperty<String> comandos = new SimpleListProperty<String>();
	private ListProperty<String> tips = new SimpleListProperty<String>(FXCollections.observableArrayList());

	public final StringProperty descripcionProperty() {
		return this.descripcion;
	}

	public final String getDescripcion() {
		return this.descripcionProperty().get();
	}

	public final void setDescripcion(final String descripcion) {
		this.descripcionProperty().set(descripcion);
	}

	public final ObjectProperty<Shell> shellProperty() {
		return this.shell;
	}

	public final Shell getShell() {
		return this.shellProperty().get();
	}

	public final void setShell(final Shell shell) {
		this.shellProperty().set(shell);
	}

	public final StringProperty rutaProperty() {
		return this.ruta;
	}

	public final String getRuta() {
		return this.rutaProperty().get();
	}

	public final void setRuta(final String ruta) {
		this.rutaProperty().set(ruta);
	}

	public final StringProperty usuarioProperty() {
		return this.usuario;
	}

	public final String getUsuario() {
		return this.usuarioProperty().get();
	}

	public final void setUsuario(final String usuario) {
		this.usuarioProperty().set(usuario);
	}

	public final ListProperty<String> comandosProperty() {
		return this.comandos;
	}

	public final ObservableList<String> getComandos() {
		return this.comandosProperty().get();
	}

	public final void setComandos(final ObservableList<String> comandos) {
		this.comandosProperty().set(comandos);
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

}

package dad.javafx.terminaltrainer.model;

import java.util.Enumeration;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Goal {
	private StringProperty descripcion = new SimpleStringProperty();
	private Enumeration<String> shell;
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

	public Enumeration<String> getShell() {
		return shell;
	}

	public void setShell(Enumeration<String> shell) {
		this.shell = shell;
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

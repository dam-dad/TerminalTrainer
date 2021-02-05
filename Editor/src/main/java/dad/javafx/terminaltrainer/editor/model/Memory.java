package dad.javafx.terminaltrainer.editor.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Memory {
	private String pathDirectorio;
	private String pathFichero;
	private DoubleProperty alto;
	private DoubleProperty ancho;
	private DoubleProperty posX;
	private DoubleProperty posY;
	private DoubleProperty splitPos;

	public Memory() {
		pathDirectorio = System.getProperty("user.home") + File.separator + ".terminaltrainer";
		pathFichero = pathDirectorio + File.separator + "window.config";

		alto = new SimpleDoubleProperty();
		ancho = new SimpleDoubleProperty();
		posX = new SimpleDoubleProperty();
		posY = new SimpleDoubleProperty();
		splitPos = new SimpleDoubleProperty();
	}

	public void saveFile() throws FileNotFoundException, IOException {
		try (OutputStream fichero = new FileOutputStream(pathFichero)) {
			Properties prop = new Properties();

			prop.setProperty("size.width", ancho.get() + "");
			prop.setProperty("size.height", alto.get() + "");
			prop.setProperty("location.x", posX.get() + "");
			prop.setProperty("location.y", posY.get() + "");
			prop.setProperty("positionLeft.Divider", splitPos.get() + "");

			prop.store(fichero, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadFile() {
		try {

			File file = new File(pathDirectorio);
			file.mkdir();

			File memory = new File(pathFichero);
			boolean created = memory.createNewFile();

			if (created) {
				try (OutputStream output = new FileOutputStream(memory.getPath())) {
					Properties prop = new Properties();

					prop.setProperty("size.width", "1200");
					prop.setProperty("size.height", "650");
					prop.setProperty("location.x", "440");
					prop.setProperty("location.y", "244");
					prop.setProperty("positionLeft.Divider", "0.5");

					prop.store(output, null);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

			try (InputStream input = new FileInputStream(pathFichero)) {
				Properties prop = new Properties();

				prop.load(input);

				alto.set(Double.parseDouble(prop.getProperty("size.height")));
				ancho.set(Double.parseDouble(prop.getProperty("size.width")));
				posX.set(Double.parseDouble(prop.getProperty("location.x")));
				posY.set(Double.parseDouble(prop.getProperty("location.y")));
				splitPos.set(Double.parseDouble(prop.getProperty("positionLeft.Divider")));

			} catch (IOException ex) {
				ex.printStackTrace();
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public final DoubleProperty altoProperty() {
		return this.alto;
	}

	public final double getAlto() {
		return this.altoProperty().get();
	}

	public final void setAlto(final double alto) {
		this.altoProperty().set(alto);
	}

	public final DoubleProperty anchoProperty() {
		return this.ancho;
	}

	public final double getAncho() {
		return this.anchoProperty().get();
	}

	public final void setAncho(final double ancho) {
		this.anchoProperty().set(ancho);
	}

	public final DoubleProperty posXProperty() {
		return this.posX;
	}

	public final double getPosX() {
		return this.posXProperty().get();
	}

	public final void setPosX(final double posX) {
		this.posXProperty().set(posX);
	}

	public final DoubleProperty posYProperty() {
		return this.posY;
	}

	public final double getPosY() {
		return this.posYProperty().get();
	}

	public final void setPosY(final double posY) {
		this.posYProperty().set(posY);
	}

	public final DoubleProperty splitPosLeftProperty() {
		return this.splitPos;
	}

	public final double getSplitPosLeft() {
		return this.splitPosLeftProperty().get();
	}

	public final void setSplitPosLeft(final double splitPosLeft) {
		this.splitPosLeftProperty().set(splitPosLeft);
	}

}

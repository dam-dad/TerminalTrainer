package dad.javafx.terminaltrainer.informes;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Main {

	public static final String JRXML_FILE = "result.jrxml";
	public static final String PDF_FILE = "result.pdf";

	public static void vistaPrevia() throws JRException {

		// compila el informe
		JasperReport report = JasperCompileManager.compileReport("C:\\Users\\Aitor\\eclipse-workspaceDAD\\TerminalTrainer\\Trainer\\result.jrxml");

		// crea el mapa de parámetros para el informe (en este caso vacío)
		Map<String, Object> parameters = new HashMap<String, Object>();

		// generamos el informe (combinamos informe + datos)
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,
				new JRBeanCollectionDataSource((Collection<?>) ResultDataProvider.getResultChallenges()));

		// visualiza el informe generado
		JasperViewer.viewReport(jasperPrint);

	}

	public static void generarPdf() throws JRException, IOException {

		// compila el informe
		JasperReport report = JasperCompileManager.compileReport(Main.class.getResourceAsStream(JRXML_FILE));

		// mapa de parámetros para el informe
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("anyo", 2014); // no lo uso, pero se lo paso

		// generamos el informe (combinamos el informe compilado con los datos)
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,
				new JRBeanCollectionDataSource((Collection<?>) ResultDataProvider.getResultChallenges()));

		// exporta el informe a un fichero PDF
		JasperExportManager.exportReportToPdfFile(jasperPrint, PDF_FILE);

		// Abre el archivo PDF generado con el programa predeterminado del sistema
		Desktop.getDesktop().open(new File(PDF_FILE));
	}

	public static void main(String[] args) throws JRException, IOException {
		vistaPrevia();
		generarPdf();

	}

}

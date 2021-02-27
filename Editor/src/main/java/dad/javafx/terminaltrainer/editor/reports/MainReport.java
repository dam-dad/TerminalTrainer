package dad.javafx.terminaltrainer.editor.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.utils.JSONUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class MainReport {

	public static void vistaPrevia() throws JRException {

		// compila el informe
		JasperReport report = JasperCompileManager.compileReport(MainReport.class.getResourceAsStream("/reports/result.jrxml"));

		// crea el mapa de parámetros para el informe (en este caso vacío)
		Map<String, Object> parameters = new HashMap<String, Object>();

		// generamos el informe (combinamos informe + datos)
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,
				new JRBeanCollectionDataSource(ResultDataProvider.getChallenges()));

		// visualiza el informe generado
		JasperViewer.viewReport(jasperPrint);

	}

	public static void generarPdf(Challenge challenge) throws JRException, IOException {

		// compila el informe
		JasperReport report = JasperCompileManager.compileReport(MainReport.class.getResourceAsStream("/reports/result.jrxml"));

		// mapa de parámetros para el informe
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("os", challenge.getOs().toString());
		parameters.put("name", challenge.getName());
		parameters.put("description", challenge.getDescription());

		// generamos el informe (combinamos el informe compilado con los datos)
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(challenge.getGoals()));

		// exporta el informe a un fichero PDF
		JasperExportManager.exportReportToPdfFile(jasperPrint, "pdf/result.pdf");

		// Abre el archivo PDF generado con el programa predeterminado del sistema
		Desktop.getDesktop().open(new File("pdf/result.pdf"));
	}

	public static void main(String[] args) throws JRException, IOException {
		
		Challenge challenge = JSONUtils.fromJson(new File("../Trainer/challenges/PruebaTrainer.challenge"), Challenge.class);
		
		generarPdf(challenge);

	}

}

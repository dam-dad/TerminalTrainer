package dad.javafx.terminaltrainer.informes;


import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.ui.app.App;

public class ResultDataProvider {
	public static Challenge getResultChallenges(){
		Challenge challenge = new Challenge();
		/*challenge.setName(App.getController().getChallenge().getName());
		challenge.setDescription(App.getController().getChallenge().getDescription());*/
		challenge.setName("nombre de prueba");
		challenge.setDescription("descripción de prueba");
		return challenge;
	}
}

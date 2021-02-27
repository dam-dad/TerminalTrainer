package dad.javafx.terminaltrainer.editor.reports;

import java.util.ArrayList;
import java.util.List;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.ui.app.App;
import dad.javafx.terminaltrainer.editor.ui.controller.ChallengeController;

public class ResultDataProvider {
	public static List<Challenge> getChallenges() {
		List<Challenge> challenges = new ArrayList<>();
		Challenge challenge = new Challenge();
		App.getController().getChallengeController();
		challenge.setName(ChallengeController.getChallengeResult().getName());
		challenge.setDescription(ChallengeController.getChallengeResult().getDescription());
		challenge.setOs(ChallengeController.getChallengeResult().getOs());
		challenges.add(challenge);
		return challenges;
	}

}

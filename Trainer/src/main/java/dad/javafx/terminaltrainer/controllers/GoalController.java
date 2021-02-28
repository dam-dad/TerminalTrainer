package dad.javafx.terminaltrainer.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class GoalController implements Initializable {
	
	// Model
	private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public GoalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GoalTabView.fxml"));
		loader.setController(this);
		loader.load();
	}
	

	public ObjectProperty<Challenge> challengeProperty() {
		return challenge;
	}

}

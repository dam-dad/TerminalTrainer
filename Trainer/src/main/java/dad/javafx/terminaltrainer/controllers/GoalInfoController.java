package dad.javafx.terminaltrainer.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class GoalInfoController implements Initializable {

	@FXML
	private GridPane view;

	@FXML
	private JFXTextArea descriptionArea;

	@FXML
	private JFXCheckBox doneBox;

	public GoalInfoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InfoGoalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public GridPane getView() {
		return view;
	}

}

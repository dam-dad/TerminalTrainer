package dad.javafx.terminaltrainer.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.editor.ui.app.App;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class MainTrainerController implements Initializable {

	// Controllers
	GoalListController goalListcontroller = new GoalListController();
	GoalInfoController goalInfocontroller = new GoalInfoController();

	@FXML
	private BorderPane view;

	@FXML
	private SplitPane split;

	public MainTrainerController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TrainerMainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		split.getItems().addAll(goalListcontroller.getView(), goalInfocontroller.getView());

	}

	@FXML
	void OnDarkThemeAction(ActionEvent event) {

	}

	@FXML
	void OnLightThemeAction(ActionEvent event) {

	}

	@FXML
	void onDebugAction(ActionEvent event) {

	}

	@FXML
	void onLoadChallengeAction(ActionEvent event) {

	}

	@FXML
	void onPrintAction(ActionEvent event) {

	}

	public BorderPane getView() {
		return view;
	}

}

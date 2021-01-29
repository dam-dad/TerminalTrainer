package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GoalController implements Initializable {

	@FXML
    private GridPane view;

    @FXML
    private JFXButton btnAddGoal;

    @FXML
    private JFXButton btnRemoveGoal;

    @FXML
    private JFXRadioButton radioOSChallenge;

    @FXML
    private JFXRadioButton radioOSChallenge2;

    @FXML
    private JFXTextField textNameChallenge;

    @FXML
    private JFXTextArea textDescriptionChallengue;

    @FXML
    private JFXTreeTableView<?> tableGoals;

    @FXML
    void onAddGoalAction(ActionEvent event) {

    }

    @FXML
    void onRemoveGoalAction(ActionEvent event) {

    }
    	
    
    public GoalController() throws IOException {
		// TODO Auto-generated constructor stub
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GoalView.fxml"));
		loader.setController(this);
		loader.load();
	}
    
    public GridPane getView() {
		return view;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

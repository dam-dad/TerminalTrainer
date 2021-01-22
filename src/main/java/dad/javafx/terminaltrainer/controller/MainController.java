package dad.javafx.terminaltrainer.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MainController implements Initializable{
	
	@FXML
    private BorderPane view;

    @FXML
    private GridPane gridChallenge;

    @FXML
    private TextField textNameChallenge;

    @FXML
    private TextArea textDescriptionChallengue;

    @FXML
    private Button btnAddGoal;

    @FXML
    private Button btnRemoveGoal;

    @FXML
    private TableView<?> tableGoals;

    @FXML
    private RadioButton radioOSChallenge;

    @FXML
    private RadioButton radioOSChallenge2;

    @FXML
    private GridPane gridGoal;

    @FXML
    private TextField textDescriptionGoal;

    @FXML
    private ChoiceBox<?> choicheShell;

    @FXML
    private TextField textPWDGoal;

    @FXML
    private TextField textUserGoal;

    @FXML
    private Button btnAddCommand;

    @FXML
    private Button btnRemoveCommand;

    @FXML
    private Button btnAddTip;

    @FXML
    private Button btnRemoveTip;

    @FXML
    private ListView<?> listCommands;

    @FXML
    private ListView<?> listTips;

    @FXML
    void onAddCommandAction(ActionEvent event) {

    }

    @FXML
    void onAddGoalAction(ActionEvent event) {

    }

    @FXML
    void onAddTipAction(ActionEvent event) {

    }

    @FXML
    void onRemoveCommandAction(ActionEvent event) {

    }

    @FXML
    void onRemoveGoalAction(ActionEvent event) {

    }

    @FXML
    void onRemoveTipAction(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

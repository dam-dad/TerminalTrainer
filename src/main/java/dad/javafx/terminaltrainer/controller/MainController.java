package dad.javafx.terminaltrainer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.model.Challenge;
import dad.javafx.terminaltrainer.model.Goal;
import dad.javafx.terminaltrainer.model.Shell;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MainController implements Initializable{
	
	//model
	private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();
	private ObjectProperty<Goal> goals = new SimpleObjectProperty<>();
	
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
    private TableView<Goal> tableGoals;
    
    @FXML
    private TableColumn<Goal, String> descriptionColumn;

    @FXML
    private TableColumn<Goal, Shell> shellColumn;

    @FXML
    private TableColumn<Goal, String> pwdColumn;

    @FXML
    private TableColumn<Goal, String> userColumn;

    @FXML
    private RadioButton radioOSChallenge;

    @FXML
    private RadioButton radioOSChallenge2;

    @FXML
    private GridPane gridGoal;

    @FXML
    private TextField textDescriptionGoal;

    @FXML
    private ChoiceBox<Shell> choicheShell;

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
    private ListView<String> listCommands;

    @FXML
    private ListView<String> listTips;

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
    
    public MainController() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		challenge.addListener((o, ov, nv) -> onChallengeChanged(o, ov, nv));
		goals.addListener((o, ov, nv) -> onGoalsChanged(o, ov, nv));	
	}
	
	private void onGoalsChanged(ObservableValue<? extends Goal> o, Goal ov, Goal nv) {
		// TODO Auto-generated method stub
		
		if( ov != null ) {
			textDescriptionGoal.textProperty().unbindBidirectional(ov.descriptionProperty());
			textPWDGoal.textProperty().unbindBidirectional(ov.ruteProperty());
		}
		
		if( nv != null ) {
			textDescriptionGoal.textProperty().bindBidirectional(nv.descriptionProperty());
			textPWDGoal.textProperty().bindBidirectional(nv.ruteProperty());
		}
		
	}

	private void onChallengeChanged(ObservableValue<? extends Challenge> o, Challenge ov, Challenge nv) {
		
		if (ov != null) {
			textDescriptionChallengue.textProperty().unbindBidirectional(ov.descriptionProperty());
			textNameChallenge.textProperty().unbindBidirectional(ov.nameProperty());
			tableGoals.itemsProperty().unbindBidirectional(ov.goalsProperty());
			// TODO unbind properties
		}

		if (nv != null) {
			textDescriptionChallengue.textProperty().bindBidirectional(ov.descriptionProperty());
			textNameChallenge.textProperty().bindBidirectional(ov.nameProperty());
			tableGoals.itemsProperty().bindBidirectional(ov.goalsProperty());
			// TODO bind properties
		}
	}
	
	public BorderPane getView() {
		return this.view;
	}

}

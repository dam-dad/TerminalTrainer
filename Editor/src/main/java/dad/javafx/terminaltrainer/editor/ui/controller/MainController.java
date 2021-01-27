package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import dad.javafx.terminaltrainer.editor.model.Goal;
import dad.javafx.terminaltrainer.editor.model.Shell;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
	private ListProperty<Goal> goals = new SimpleListProperty<Goal>(FXCollections.observableArrayList());
	
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
    
    Challenge modeloChallenge = new Challenge();

    @FXML
    void onAddCommandAction(ActionEvent event) {

    }

    @FXML
    void onAddGoalAction(ActionEvent event) {
    	Goal goal = new Goal();
    	goal.setDescription("Descripción por defecto");
    	goal.setPath("C:\\Users");
    	Shell shell = null;
    	goal.setShell(shell.CMD);
    	goal.setUsername("Usuario");
    	goals.add(goal);
		tableGoals.getSelectionModel().selectLast();
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
		challenge.addListener((o, ov, nv) -> onChallengeChanged(o, ov, nv));
		goals.addListener((o, ov, nv) -> onGoalsChanged(o, ov, nv));
		
		//deshabilita el botón si la tabla está vacía
		btnRemoveGoal.disableProperty().bind(tableGoals.getSelectionModel().selectedItemProperty().isNull());
		
		//bindeo de columnas de la tabla goal
//		descriptionColumn.setCellValueFactory(v -> v.getValue().descriptionProperty());
//		shellColumn.setCellValueFactory(v -> v.getValue().shellProperty());
//		pwdColumn.setCellValueFactory(v -> v.getValue().pathProperty());
//		userColumn.setCellValueFactory(v -> v.getValue().usernameProperty());
		
	}
	
	private void onGoalsChanged(ObservableValue<? extends Goal> o, Goal ov, Goal nv) {
		
		if( ov != null ) {
			//bindeos de la tabla de goals
			
			
			textDescriptionGoal.textProperty().unbindBidirectional(ov.descriptionProperty());
			textPWDGoal.textProperty().unbindBidirectional(ov.pathProperty());
		}
		
		if( nv != null ) {
			
			textDescriptionGoal.textProperty().bindBidirectional(nv.descriptionProperty());
			textPWDGoal.textProperty().bindBidirectional(nv.pathProperty());
		}
		
	}

	private void onChallengeChanged(ObservableValue<? extends Challenge> o, Challenge ov, Challenge nv) {
		
		if (ov != null) {
			tableGoals.itemsProperty().unbindBidirectional(ov.goalsProperty());
			
			textDescriptionChallengue.textProperty().unbindBidirectional(ov.descriptionProperty());
			textNameChallenge.textProperty().unbindBidirectional(ov.nameProperty());
			tableGoals.itemsProperty().unbindBidirectional(ov.goalsProperty());
			// TODO unbind properties
		}

		if (nv != null) {
			//bindeo de la tabla goals
			tableGoals.itemsProperty().bindBidirectional(nv.goalsProperty());
			
			textDescriptionChallengue.textProperty().bindBidirectional(nv.descriptionProperty());
			textNameChallenge.textProperty().bindBidirectional(nv.nameProperty());
			tableGoals.itemsProperty().bindBidirectional(nv.goalsProperty());
			// TODO bind properties
		}
	}
	
	public BorderPane getView() {
		return this.view;
	}

}

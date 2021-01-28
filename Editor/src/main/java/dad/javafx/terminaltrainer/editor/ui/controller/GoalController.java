package dad.javafx.terminaltrainer.editor.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private TextField textDescription;

    @FXML
    private ComboBox<?> comboShell;

    @FXML
    private TextField textPWD;

    @FXML
    private TextField textUser;

    @FXML
    private ListView<?> ListCommands;

    @FXML
    private ListView<?> ListTips;

    @FXML
    private Button btnAddCommand;

    @FXML
    private Button btnRemoveCommand;

    @FXML
    private Button btnAddTip;

    @FXML
    private Button btnRemoveTip;

    @FXML
    void onAddCommandAction(ActionEvent event) {
    	
    }

    @FXML
    void onAddTipAction(ActionEvent event) {

    }

    @FXML
    void onRemoveCommandAction(ActionEvent event) {

    }

    @FXML
    void onRemoveTipAction(ActionEvent event) {
    	
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

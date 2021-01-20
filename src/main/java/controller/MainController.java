package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable{
	
	@FXML
    private BorderPane view;

    @FXML
    private TextField textName;

    @FXML
    private TextArea textDescription;

    @FXML
    private Button btnAddGoal;

    @FXML
    private Button btnRemoveGoal;

    @FXML
    private ListView<?> listGoals;

    @FXML
    void onAddGoalAction(ActionEvent event) {

    }

    @FXML
    void onRemoveGoalAction(ActionEvent event) {

    }

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

package dad.javafx.terminaltrainer.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class GoalListController implements Initializable{

	
	@FXML
    private GridPane view;

    @FXML
    private JFXListView<?> goalList;
	
	
    public GoalListController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GoalListView.fxml"));
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

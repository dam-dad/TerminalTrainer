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

import dad.javafx.terminaltrainer.editor.model.Shell;
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
    private JFXButton btnAddCommand;

    @FXML
    private JFXButton btnRemoveCommand;

    @FXML
    private JFXButton btnAddTip;

    @FXML
    private JFXButton btnRemoveTip;

    @FXML
    private JFXTextField textDescription;

    @FXML
    private JFXComboBox<Shell> comboShell;

    @FXML
    private JFXTextField textPWD;

    @FXML
    private JFXTextField textUser;

    @FXML
    private JFXListView<String> ListCommands;

    @FXML
    private JFXListView<String> ListTips;

    @FXML
    void onAddCommandAction(ActionEvent event) {

    }

    @FXML
    void onAddTippAction(ActionEvent event) {

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

package dad.javafx.terminaltrainer.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.javafx.terminaltrainer.editor.model.Challenge;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class ChallengeController implements Initializable {
	
	// Model
	private ObjectProperty<Challenge> challenge = new SimpleObjectProperty<>();
	
    @FXML
    private GridPane view;
	
	@FXML
    private JFXTextField textName;

    @FXML
    private JFXTextArea textDescription;

    @FXML
    private JFXTextField textOS;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		challenge.addListener((o, ov, nv) -> onChallengeChanged(o, ov, nv));
	}
	
	private void onChallengeChanged(ObservableValue<? extends Challenge> o, Challenge ov, Challenge nv) {
		// TODO Auto-generated method stub
		if (ov != null) {
			textDescription.textProperty().unbindBidirectional(ov.descriptionProperty());
			textName.textProperty().unbindBidirectional(ov.nameProperty());
		}

		if (nv != null) {
			textDescription.textProperty().bindBidirectional(nv.descriptionProperty());
			textName.textProperty().bindBidirectional(nv.nameProperty());
			if(nv.getOs() != null) {
				textOS.setText(nv.osProperty().toString());
			}
		}
	}

	public ChallengeController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChallengeTabView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	public GridPane getView() {
		return view;
	}

	public ObjectProperty<Challenge> challengeProperty() {
		return challenge;
	}

}

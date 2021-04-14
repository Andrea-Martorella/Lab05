package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtOutputCorrect;

    @FXML
    private TextArea txtOutputWrong;

    @FXML
    private Button btnReset;

    @FXML
    void doCalcola(ActionEvent event) {
    	String parola = txtInput.getText();
    	List<String> corretti = model.getCorretti(parola);
    	List<String> sbagliati = model.getSbagliati(parola);
    	for(String s : corretti) {
    		txtOutputCorrect.appendText(s+"\n");
    	}
    	for(String s : sbagliati) {
    		txtOutputWrong.appendText(s+"\n");
    	}
    	
    }

    @FXML
    void s(ActionEvent event) {
    	txtOutputCorrect.clear();
    	txtOutputWrong.clear();
    	txtInput.clear();
    	
    }

    @FXML
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutputCorrect != null : "fx:id=\"txtOutputCorrect\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutputWrong != null : "fx:id=\"txtOutputWrong\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Model model) {
    	this.model = model;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author mmusil
 */
public class FXMLDocumentController implements Initializable {

	@FXML
	private Label display;
	
	@FXML
	private Button buttonPoint;


	private int memory;
	private String operation;
	private boolean issetMemory;
	
	public FXMLDocumentController(){
		memory = 0;
		operation = "";
		issetMemory = false;
	}

	@FXML
	private void handleNumberButon(ActionEvent event) {
		String textPressed = ((Button)event.getSource()).getText();
		String oldNumber = display.getText();

		// Operation was selected, the second number is going to be inserted
		if(!getOperation().equals("") && !issetMemory()) {
			setMemory(Integer.parseInt(oldNumber));
			oldNumber = "";
		}
		// First digit, replace default zero with number
		else if(oldNumber.equals("0")) {
			oldNumber = "";
		}

		display.setText(oldNumber+textPressed);
	}

	@FXML
	private void handleResultButton(ActionEvent event) {
		// TODO result
	}

	@FXML
	private void handlePlusButton(ActionEvent event) {
		setOperation("+");
		buttonPoint.setDisable(false);
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleMinusButton(ActionEvent event) {
		setOperation("-");
		buttonPoint.setDisable(false);
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleMulButton(ActionEvent event) {
		setOperation("*");
		buttonPoint.setDisable(false);
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleDivButton(ActionEvent event) {
		setOperation("/");
		buttonPoint.setDisable(false);
		if (issetMemory() && !operation.equals("")) {
			// TODO result !!division by zero
		}
	}

	@FXML
	private void handleModButton(ActionEvent event) {
		setOperation("%");
		buttonPoint.setDisable(false);
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleFacButton(ActionEvent event) {
		setOperation("!");
		// TODO result
	}

	@FXML
	private void handleExpButton(ActionEvent event) {
		setOperation("exp");
		buttonPoint.setDisable(true);
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleRootButton(ActionEvent event) {
		setOperation("root");
		buttonPoint.setDisable(true);
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleClearButton(ActionEvent event) {
		clearMemory();
		setOperation("");
		display.setText("0");
		// TODO enable all keys (button point, operations,...)
	}

	@FXML
	private void handlePointButton(ActionEvent event) {
		// TODO
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	public void setMemory(int value){
		this.memory = value;
		this.issetMemory = true;
	}

	public int getMemory(){
		return this.memory;
	}

	public void clearMemory(){
		this.memory = 0;
		this.issetMemory = false;
	}

	public boolean issetMemory(){
		return this.issetMemory;
	}

	public void setOperation(String value){
		this.operation = value;
	}

	public String getOperation(){
		return this.operation;
	}
}

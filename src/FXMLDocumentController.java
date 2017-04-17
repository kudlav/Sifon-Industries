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
 * @author kudlav
 * @author Rengyr
 * @author AdamKuba
 */
public class FXMLDocumentController implements Initializable {

	@FXML
	private Label display;

	@FXML
	private Label display1;
	
	@FXML
	private Button buttonPoint;

	@FXML
	private Button mod;

	@FXML
	private Button idiv;

	@FXML
	private Button imul;

	@FXML
	private Button nroot;

	@FXML
	private Button exp;

	@FXML
	private Button fac;

	@FXML
	private Button sub;

	@FXML
	private Button add;

	@FXML
	private Button rslt;


	private double memory;
	private String operation;
	private boolean issetMemory;
	private int error;
	
	public FXMLDocumentController(){
		memory = 0;
		operation = "";
		issetMemory = false;
		Calc.controller = this;
	}

	@FXML
	private void handleNumberButton(ActionEvent event) {
		String textPressed = ((Button)event.getSource()).getText();
		String oldNumber = display.getText();

		if(this.error == 1){
			oldNumber = "";
			this.issetMemory = false;
			this.operation="";
			enableButtons();
			this.error = 0;
		}
		// First digit, replace default zero with number
		else if(oldNumber.equals("0")) {
			oldNumber = "";
		}

		display.setText(oldNumber+textPressed);
	}

	@FXML
	private void handleResultButton(ActionEvent event) {
		if (isDisplayEmpty()) return;
		String OP = getOperation();
		double result;
		switch (OP){
			case "+":  result = MathLib.add(getMemory(), Double.parseDouble(this.display.getText()));
				clearMemory();
				this.display.setText(String.valueOf(result));
				break;
			case "-":  result = MathLib.sub(getMemory(), Double.parseDouble(this.display.getText()));
				clearMemory();
				this.display.setText(String.valueOf(result));
				break;
			case "×":  result = MathLib.imul(getMemory(), Double.parseDouble(this.display.getText()));
				clearMemory();
				this.display.setText(String.valueOf(result));
				break;
			case "÷":  try{
				result = MathLib.idiv(getMemory(), Double.parseDouble(this.display.getText()));
				clearMemory();
				this.display.setText(String.valueOf(result));
				}catch (ArithmeticException AE){
					this.display.setText(AE.getMessage());
				this.error = 1;
				dissableButtons();
				}
				break;
			case "%":  result = MathLib.mod((int)getMemory(), Integer.parseInt(this.display.getText()));
				clearMemory();
				this.display.setText(String.valueOf(result));
				break;
			case "!":  result = MathLib.fac(Integer.parseInt(this.display.getText()));
				if (result==-1){
					this.display.setText(String.valueOf("Positive integers only"));
					this.error = 1;
					dissableButtons();
				}
				else{
					this.display.setText(String.valueOf((int)result));
					clearMemory();
				}
				break;
			case "exp":  result = MathLib.exp(Integer.parseInt(this.display.getText()), getMemory());
				clearMemory();
				this.display.setText(String.valueOf(result));
				break;
			case "root":  result = MathLib.nRoot((int)getMemory(), Double.parseDouble(this.display.getText()));

				if (result==-1){
					this.display.setText(String.valueOf("Positive numbers only"));
					this.error = 1;
					dissableButtons();
				}
				else{
					this.display.setText(String.valueOf(result));
					clearMemory();
				}
				break;
		}
		if (display.getText().contains(".")) disableNonDec();
		if (display.getText().isEmpty() || Double.parseDouble(display.getText())%1==0) enableButtons();
		display1.setText("");

	}
	
	public void keyPressed(char key){
		if(Character.isDigit(key)){
			handleNumberButton(new ActionEvent(new Button(Character.toString(key)),null));
		}
		switch (key) {
		case '+':
			if (add.isDisable()) return;
			handlePlusButton(null);
			break;
		case '-':
			if (sub.isDisable()) return;
			handleMinusButton(null);
			break;
		case '×':
			if (imul.isDisable()) return;
			handleMulButton(null);
			break;
		case '÷':
			if (idiv.isDisable()) return;
			handleDivButton(null);
			break;
		case '=':
			if (rslt.isDisable()) return;
			handleResultButton(null);
			break;
		case '.':
			if (buttonPoint.isDisable()) return;
			handlePointButton(null);
			break;
		case 'b':
			if (!display.getText().isEmpty()){
				display.setText(display.getText().substring(0, display.getText().length()-1));
				if (display.getText().isEmpty()) display.setText("0");
				else if (!display.getText().contains(".")) enableNonDec();
			}
			break;
		case 'c':
			handleClearButton(null);
			break;
		}
	}
	
	private void handleOperatinButton(String op){
		if (!getOperation().isEmpty()){
			handleResultButton(null);
			if (isDisplayEmpty()) return;
		}
		setOperation(op);
		setMemory(Double.parseDouble(display.getText()));
		setHistory();
		display.setText("");
		
	}

	@FXML
	private void handlePlusButton(ActionEvent event) {
		handleOperatinButton("+");
		buttonPoint.setDisable(false);
	}

	@FXML
	private void handleMinusButton(ActionEvent event) {
		// When true, it's not operation, but negation of number
		if ((!issetMemory() || !getOperation().isEmpty()) && (display.getText().equals("0") || display.getText().equals(""))){
			display.setText("-");
			fac.setDisable(true);
			return;
		}
		handleOperatinButton("-");
		buttonPoint.setDisable(false);
	}

	@FXML
	private void handleMulButton(ActionEvent event) {
		handleOperatinButton("×");
		buttonPoint.setDisable(false);
	}

	@FXML
	private void handleDivButton(ActionEvent event) {
		handleOperatinButton("÷");
		buttonPoint.setDisable(false);
	}

	@FXML
	private void handleModButton(ActionEvent event) {
		handleOperatinButton("%");
		buttonPoint.setDisable(true);
	}

	@FXML
	private void handleFacButton(ActionEvent event) {
		handleOperatinButton("!");
	}

	@FXML
	private void handleExpButton(ActionEvent event) {
		handleOperatinButton("exp");
		buttonPoint.setDisable(true);
	}

	@FXML
	private void handleRootButton(ActionEvent event) {
		handleOperatinButton("root");
		sub.setDisable(true);
	}

	@FXML
	private void handleClearButton(ActionEvent event) {
		clearMemory();
		setOperation("");
		display.setText("0");
		display1.setText("");
		enableButtons();
	}

	@FXML
	private void handlePointButton(ActionEvent event) {
		if (display.getText().isEmpty()) display.setText("0");
		if (Double.parseDouble(display.getText())%1==0) display.setText(String.valueOf((int)Double.parseDouble(display.getText())));
		disableNonDec();
		String text = display.getText()+".";
		display.setText(text);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	private void disableNonDec(){
		nroot.setDisable(true);
		fac.setDisable(true);
		mod.setDisable(true);
		buttonPoint.setDisable(true);
	}
	
	private void enableNonDec(){
		nroot.setDisable(false);
		fac.setDisable(false);
		mod.setDisable(false);
		buttonPoint.setDisable(false);
	}
	
	private void dissableButtons(){
		buttonPoint.setDisable(true);
		mod.setDisable(true);
		idiv.setDisable(true);
		imul.setDisable(true);
		nroot.setDisable(true);
		exp.setDisable(true);
		fac.setDisable(true);
		sub.setDisable(true);
		add.setDisable(true);
		rslt.setDisable(true);
	}

	private void enableButtons(){
		buttonPoint.setDisable(false);
		mod.setDisable(false);
		idiv.setDisable(false);
		imul.setDisable(false);
		nroot.setDisable(false);
		exp.setDisable(false);
		fac.setDisable(false);
		sub.setDisable(false);
		add.setDisable(false);
		rslt.setDisable(false);
	}


	/**
	 * Save int to memory. IssetMemory is set to true.
	 *
	 * @author kudlav
	 * @param value double to store
	 */
	private void setMemory(double value){
		this.memory = value;
		this.issetMemory = true;
	}

	/**
	 * Get value stored in memory.
	 *
	 * @author kudlav
	 * @return value stored in memory
	 */
	private double getMemory(){
		return this.memory;
	}

	/**
	 * Set zero value to memory. IssetMemory is set to false.
	 *
	 * @author kudlav
	 */
	private void clearMemory(){
		this.memory = 0;
		this.issetMemory = false;
	}

	/**
	 * Check if something is saved in memory.
	 *
	 * @author kudlav
	 * @return true if number was stored, false if nothing was save or after reset
	 */
	private boolean issetMemory(){
		return this.issetMemory;
	}

	/**
	 * Set operation selected by user.
	 *
	 * @author kudlav
	 * @param value string describing operation
	 */
	private void setOperation(String value){
		this.operation = value;
	}

	/**
	 * Get operation seleted by user.
	 *
	 * @author kudlav
	 * @return string of operation or empty string if nothing was selected
	 */
	private String getOperation(){
		return this.operation;
	}

	private void setHistory(){
		display1.setText(String.valueOf(getMemory())+" "+ getOperation()+" ");
	}
	
	/**
	 * Returns true if display is empty or only sign symbol
	 * 
	 * @author Rengyr
	 * @return true if display is empty or only sign symbol
	 */
	private boolean isDisplayEmpty(){
		return display.getText().isEmpty() || display.getText().equals("-");
	}
}

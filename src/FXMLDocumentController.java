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

		// Operation was selected, the second number is going to be inserted
		if(!getOperation().equals("") && !issetMemory()) {
			setMemory(Double.parseDouble(oldNumber));
			oldNumber = "";
			setHistory();
		}
		// First digit, replace default zero with number
		else if(oldNumber.equals("0")) {
			oldNumber = "";
		}

		display.setText(oldNumber+textPressed);
	}

	@FXML
	private void handleResultButton(ActionEvent event) {
		String OP = getOperation();
		double result;
		switch (OP){
			case "+":  result = MathLib.add(getMemory(), Integer.parseInt(this.display.getText()));
				setMemory(0);
				this.issetMemory = false;
				this.display.setText(String.valueOf(result));
				break;
			case "-":  result = MathLib.sub(getMemory(), Integer.parseInt(this.display.getText()));
				setMemory(0);
				this.issetMemory = false;
				this.display.setText(String.valueOf(result));
				break;
			case "*":  result = MathLib.imul(getMemory(), Integer.parseInt(this.display.getText()));
				setMemory(0);
				this.issetMemory = false;
				this.display.setText(String.valueOf(result));
				break;
			case "/":  try{
				result = MathLib.idiv(getMemory(), Integer.parseInt(this.display.getText()));
				setMemory(0);
				this.issetMemory = false;
				this.display.setText(String.valueOf(result));
				}catch (ArithmeticException AE){
					this.display.setText(AE.getMessage());
				this.error = 1;
				dissableButtons();
				}
				break;
			case "%":  result = MathLib.mod((int)getMemory(), Integer.parseInt(this.display.getText()));
				setMemory(0);
				this.issetMemory = false;
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
					setMemory(0);
					this.issetMemory = false;
				}
				break;
			case "exp":  result = MathLib.exp(Integer.parseInt(this.display.getText()), getMemory());
				setMemory(0);
				this.issetMemory = false;
				this.display.setText(String.valueOf(result));
				break;
			case "root":  result = MathLib.nRoot((int)getMemory(), Integer.parseInt(this.display.getText()));

				if (result==-1){
					this.display.setText(String.valueOf("Positive numbers only"));
					this.error = 1;
					dissableButtons();
				}
				else{
					this.display.setText(String.valueOf(result));
					setMemory(0);
					this.issetMemory = false;
				}
				break;
		}
		display1.setText("");

	}

	@FXML
	private void handlePlusButton(ActionEvent event) {
		setOperation("+");
		buttonPoint.setDisable(false);
		// Operation selected instead of "=", set result of previous operation as first number
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleMinusButton(ActionEvent event) {
		setOperation("-");
		buttonPoint.setDisable(false);
		// Operation selected instead of "=", set result of previous operation as first number
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleMulButton(ActionEvent event) {
		setOperation("*");
		buttonPoint.setDisable(false);
		// Operation selected instead of "=", set result of previous operation as first number
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleDivButton(ActionEvent event) {
		setOperation("/");
		buttonPoint.setDisable(false);
		// Operation selected instead of "=", set result of previous operation as first number
		if (issetMemory() && !operation.equals("")) {
			// TODO result !!division by zero
		}
	}

	@FXML
	private void handleModButton(ActionEvent event) {
		setOperation("%");
		buttonPoint.setDisable(false);
		// Operation selected instead of "=", set result of previous operation as first number
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
		// Operation selected instead of "=", set result of previous operation as first number
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleRootButton(ActionEvent event) {
		setOperation("root");
		buttonPoint.setDisable(true);
		// Operation selected instead of "=", set result of previous operation as first number
		if (issetMemory() && !operation.equals("")) {
			// TODO result
		}
	}

	@FXML
	private void handleClearButton(ActionEvent event) {
		clearMemory();
		setOperation("");
		display.setText("0");
		display1.setText("");
		// TODO enable all keys (button point, operations,...)
		enableButtons();
	}

	@FXML
	private void handlePointButton(ActionEvent event) {
		// TODO
		String text = display.getText()+".";
		display.setText(text);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	public void dissableButtons(){
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

	public void enableButtons(){
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
	public void setMemory(double value){
		this.memory = value;
		this.issetMemory = true;
	}

	/**
	 * Get value stored in memory.
	 *
	 * @author kudlav
	 * @return value stored in memory
	 */
	public double getMemory(){
		return this.memory;
	}

	/**
	 * Set zero value to memory. IssetMemory is set to false.
	 *
	 * @author kudlav
	 */
	public void clearMemory(){
		this.memory = 0;
		this.issetMemory = false;
	}

	/**
	 * Check if something is saved in memory.
	 *
	 * @author kudlav
	 * @return true if number was stored, false if nothing was save or after reset
	 */
	public boolean issetMemory(){
		return this.issetMemory;
	}

	/**
	 * Set operation selected by user.
	 *
	 * @author kudlav
	 * @param value string describing operation
	 */
	public void setOperation(String value){
		this.operation = value;
	}

	/**
	 * Get operation seleted by user.
	 *
	 * @author kudlav
	 * @return string of operation or empty string if nothing was selected
	 */
	public String getOperation(){
		return this.operation;
	}

	public void setHistory(){
		display1.setText(String.valueOf(getMemory())+" "+ getOperation()+" ");
		//display1.setText("history");
	}
}

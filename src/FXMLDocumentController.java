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
		// TODO enable all keys (button point, operations,...)
	}

	@FXML
	private void handlePointButton(ActionEvent event) {
		// TODO
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	/**
	 * Save int to memory. IssetMemory is set to true.
	 *
	 * @author kudlav
	 * @param value integer to store
	 */
	public void setMemory(int value){
		this.memory = value;
		this.issetMemory = true;
	}

	/**
	 * Get value stored in memory.
	 *
	 * @author kudlav
	 * @return value stored in memory
	 */
	public int getMemory(){
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
}

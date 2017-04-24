import java.math.BigDecimal;
import java.math.RoundingMode;

import java.net.URL;

import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for GUI.
 *
 * @author Rengyr
 * @author AdamKuba
 */
public class FXMLDocumentController implements Initializable {
	/** Number of  valid digits to show on display.*/
	private int outputRoundingNumber = 9;

	/** Map of Operations linked to their name. */
	private HashMap<String, Operation> ops = new HashMap<String, Operation>();
	/** Array of operands. */
	private BigDecimal[] numbers = new BigDecimal[2];
	private int numberPos = 0;
	private boolean signed = false;
	private int decimal = 0;

	/** Selected operation. */
	private Operation selectedOp;

	/** Label for lower display.  */
	@FXML
	private Label display;

	/** Label for upper display. */
	@FXML
	private Label display1;

	/** Button for point. */
	@FXML
	private Button buttonPoint;

	/** Button for modulo. */
	@FXML
	private Button mod;

	/** Button for division. */
	@FXML
	private Button idiv;

	/** Button for multiplication. */
	@FXML
	private Button imul;

	/** Button for nthRoot. */
	@FXML
	private Button nroot;

	/** Button for x^y.  */
	@FXML
	private Button exp;

	/** Button for factorial. */
	@FXML
	private Button fac;

	/** Button for subtraction. */
	@FXML
	private Button sub;

	/** Button for addition. */
	@FXML
	private Button add;

	/** Button for result */
	@FXML
	private Button rslt;

	/**
	 * Entry method for controller.
	 */
	public FXMLDocumentController(){
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = new BigDecimal(0);
		}
		Calc.controller = this;
	}


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ops.put("add", new Operation("add", "+", true, true, true, true, true, add){

			@Override
			public BigDecimal function() {
				return MathLib.add(numbers[0],numbers[1]);
			}

		});

		ops.put("sub", new Operation("sub", "-", true, true, true, true, true, sub){

			@Override
			public BigDecimal function() {
				return MathLib.sub(numbers[0],numbers[1]);
			}

		});

		ops.put("div", new Operation("div", "÷", true, true, true, true, true, idiv){

			@Override
			public BigDecimal function() {
				return MathLib.idiv(numbers[0], numbers[1]);
			}

			@Override
			public boolean isLegalNumber(BigDecimal number, int pos) {
				boolean zero = true;
				if(number.doubleValue()==0 && pos==1) zero = false;
				return super.isLegalNumber(number, pos) && zero;
			}
		});

		ops.put("mul", new Operation("mul", "×", true, true, true, true, true, imul){

			@Override
			public BigDecimal function() {
				return MathLib.imul(numbers[0], numbers[1]);
			}

		});

		ops.put("mod", new Operation("mod", "mod", true, true, false, true, false, mod){

			@Override
			public BigDecimal function() {
				return MathLib.mod(numbers[0].toBigInteger(), numbers[1].toBigInteger());
			}

		});

		ops.put("exp", new Operation("exp", "^", true, true, true, true, false, exp){

			@Override
			public BigDecimal function() {
				return MathLib.exp(numbers[1].toBigInteger(), numbers[0]);
			}
		});

		ops.put("fac", new Operation("fac", "!", false, false, false, true, true, fac){

			@Override
			public BigDecimal function() {
				return MathLib.fac(numbers[0].intValue());
			}

		});

		ops.put("nroot", new Operation("nroot", "root", true, true, false, true, true, nroot){

			@Override
			public BigDecimal function() {
				return MathLib.nRoot(numbers[0].toBigInteger(),numbers[1]);
			}

			@Override
			public boolean canBeSecondSigned() {
				return numbers[0].intValue()%2==1;
			}
		});
		updateDisplay();
		updateVisibility();
	}

	
	/**
	 * Handler for number buttons.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handleNumberButton(ActionEvent event) {
		String number = ((Button)event.getSource()).getText();
		BigDecimal num = new BigDecimal(number);
		if (decimal!=0){
			num = num.multiply(new BigDecimal("0.1").pow(decimal));
			decimal++;
		}
		if (decimal == 0){
			numbers[numberPos] = numbers[numberPos].multiply(new BigDecimal("10"));
		}
		if (!signed){
			numbers[numberPos] = numbers[numberPos].add(num);
		}else{
			numbers[numberPos] = numbers[numberPos].subtract(num);
		}


		updateDisplay();
		updateVisibility();
 	}

	/**
	 * Handler for result button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handleResultButton(ActionEvent event) {
		doOperation();
	}

	/**
	 * Handler for addition button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handlePlusButton(ActionEvent event) {
		setOperation("add");
	}

	/**
	 * Handler for subtraction button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handleMinusButton(ActionEvent event) {
		if (numberPos == 0 && numbers[0].doubleValue() == 0) {
			signed = !signed;
			numbers[0] = numbers[0].negate();
			updateDisplay();
			return;
		}else if  (numberPos == 1 && numbers[1].doubleValue() == 0) {
			signed = !signed;
			numbers[1] = numbers[1].negate();
			updateDisplay();
			return;
		}
		setOperation("sub");
	}

	/**
	 * Handler for multiplication button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handleMulButton(ActionEvent event) {
		setOperation("mul");
	}

	/**
	 * Handler for division button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handleDivButton(ActionEvent event) {
		setOperation("div");
	}

	/**
	 * Handler for modulo button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handleModButton(ActionEvent event) {
		setOperation("mod");
	}

	/**
	 * Handler for factorial button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handleFacButton(ActionEvent event) {
		setOperation("fac");
	}

	/**
	 * Handler for x^y button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handleExpButton(ActionEvent event) {
		setOperation("exp");
	}

	/**
	 * Handler for nthRoot button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handleRootButton(ActionEvent event) {
		setOperation("nroot");
	}

	/**
	 * Handler for clear button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handleClearButton(ActionEvent event) {
		selectedOp = null;
		numbers[0] = new BigDecimal(0);
		numbers[1] = new BigDecimal(0);
		numberPos = 0;
		signed = false;
		decimal = 0;
		updateDisplay();
		updateVisibility();
	}

	/**
	 * Handler for point button.
	 * 
	 * @param event ActionEvent for handler
	 */
	@FXML
	private void handlePointButton(ActionEvent event) {
		decimal = 1;
		updateDisplay();
		updateVisibility();
	}

	/**
	 * Set operation defined by argument.
	 * 
	 * @param name name of the operation
	 */
	private void setOperation(String name){
		if (selectedOp != null){
			doOperation();
		}
		selectedOp = ops.get(name);
		selectedOp.setNumber(0, numbers[0]);
		numberPos = 1;
		signed = false;
		decimal = 0;
		if (!selectedOp.isBinaryOp()){
			doOperation();
		}
		updateDisplay();
		updateVisibility();
	}

	/**
	 * Modify numbers[0] to reflect set operation and update accordingly other variables.
	 */
	private void doOperation(){
		selectedOp.setNumber(0, numbers[0]);
		selectedOp.setNumber(1, numbers[1]);
		numbers[0] = selectedOp.function();
		numbers[1] = new BigDecimal(0);
		selectedOp = null;
		numberPos = 0;
		if (numbers[0].doubleValue() < 0) signed = true;
		else signed = false;
		if (numbers[0].doubleValue() % 1 != 0) {
			decimal = getNumberOfDecimalPlaces(numbers[0])+1;
		}
		else decimal = 0;
		updateDisplay();
		updateVisibility();
	}

	/**
	 * Handler for keys.
	 * 
	 * @param key char representing key pressed
	 */
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
		case '*':
			if (imul.isDisable()) return;
			handleMulButton(null);
			break;
		case '/':
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
			backspace();
			break;
		case 'c':
			handleClearButton(null);
			break;
		}
	}

	/**
	 * Change button enable/disable according to selected operation and numbers.
	 */
	private void updateVisibility(){
		for (Operation op : ops.values()) {
			op.enable();
		}
		if (numberPos == 0){
			buttonPoint.setDisable(false);
			rslt.setDisable(true);
			sub.setDisable(false);
			for (Operation op : ops.values()) {
				if (!op.isLegalNumber(numbers[0], 0)){
					op.disable();
					continue;
				}
				op.enable();
			}
		}
		else if (numberPos == 1 && selectedOp != null){
			rslt.setDisable(false);
			if (!selectedOp.canBeSecondDecimal()){
				buttonPoint.setDisable(true);
			}else{
				buttonPoint.setDisable(false);
			}
			if (!selectedOp.canBeSecondSigned() && numbers[1].compareTo(new BigDecimal(0))==0){
				sub.setDisable(true);
			}else{
				sub.setDisable(false);
			}
			if(!selectedOp.isLegalNumber(numbers[1], 1)){
				rslt.setDisable(true);
				for (Operation op : ops.values()) {
					op.disable();
				}
			}
		}
		if(decimal!=0){
			buttonPoint.setDisable(true);
		}
	}

	/**
	 * Update display to reflect operation and numbers.
	 */
	private void updateDisplay(){
		String zeroes = generateZeroes(getTrailingZeroes(numbers[numberPos]));
		if (getNumberOfDecimalPlaces(numbers[numberPos])==0 && decimal > 0){
			zeroes = "."+zeroes;
		}
		if (numberPos == 0){
			int scale = outputRoundingNumber-numbers[0].precision()+numbers[0].scale();
			BigDecimal output1 = numbers[0].setScale(scale, RoundingMode.HALF_UP);
			if (numbers[0].doubleValue()>Math.pow(10, outputRoundingNumber) && decimal > 0){
				zeroes = "";
			}
			else if (getNumberOfDecimalPlaces(output1)<getNumberOfDecimalPlaces(numbers[0])){
				zeroes = zeroes + "...";
			}
			display.setText(output1.stripTrailingZeros().toEngineeringString()+zeroes);
			display1.setText("");
		}else{
			String uppershort = "";
			int scale = outputRoundingNumber-numbers[1].precision()+numbers[1].scale();
			BigDecimal output1 = numbers[1].setScale(scale, RoundingMode.HALF_UP);
			if (numbers[1].doubleValue()>Math.pow(10, outputRoundingNumber)){
				zeroes = "";
			}
			else if (getNumberOfDecimalPlaces(output1)<getNumberOfDecimalPlaces(numbers[1])){
				zeroes = zeroes + "...";
			}
			scale = outputRoundingNumber-numbers[0].precision()+numbers[0].scale();
			BigDecimal output2 = numbers[0].setScale(scale, RoundingMode.HALF_UP);
			if (!(numbers[0].doubleValue()>Math.pow(10, outputRoundingNumber)) && getNumberOfDecimalPlaces(output2)<getNumberOfDecimalPlaces(numbers[0])){
				uppershort = "...";
			}
			display.setText(output1.stripTrailingZeros().toEngineeringString()+zeroes);
			display1.setText(output2.stripTrailingZeros().toEngineeringString()+uppershort+" "+selectedOp.getVisual()+" ");
		}
	}

	/**
	 * Generate string of zeroes.
	 * 
	 * @param num number of zeroes
	 * @return string with zeroes
	 */
	private String generateZeroes(int num){
		String zeroes = "";
		for (int i = 0; i < num; i++) {
			zeroes += "0";
		}
		return zeroes;
	}

	/**
	 * Delete last digit of number shown on display.
	 */
	private void backspace(){
		if(numbers[numberPos].equals("0")) return;
		if (decimal == 1){
			decimal--;
		}else if (decimal > 1){
			numbers[numberPos] = numbers[numberPos].multiply(new BigDecimal("10").pow(decimal-2));
			numbers[numberPos] = new BigDecimal(numbers[numberPos].toBigInteger());
			numbers[numberPos] = numbers[numberPos].multiply(new BigDecimal("0.1").pow(decimal-2));
			decimal--;
		}else if (decimal == 0){
			numbers[numberPos] = numbers[numberPos].multiply(new BigDecimal("0.1"));
			numbers[numberPos] = new BigDecimal(numbers[numberPos].toBigInteger());
		}
		updateDisplay();
		updateVisibility();
	}

	/**
	 * Return number of decimal digits.
	 * 
	 * @param num number from which get number of decimal digits
	 * @return number of decimal digits
	 */
	private int getNumberOfDecimalPlaces(BigDecimal num) {
	    String string = num.stripTrailingZeros().toPlainString();
	    int index = string.indexOf(".");
	    return index < 0 ? 0 : string.length() - index - 1;
	}

	/**
	 * Get number of trailing zeroes in number.
	 * 
	 * @param num number from which get number of trailing zeroes
	 * @return number of trailing zeroes
	 */
	private int getTrailingZeroes(BigDecimal num){
		return decimal-getNumberOfDecimalPlaces(num)-1;
	}

	/**
	 * Class for operation that hold which numbers are permitted and help managing button availability.
	 * 
	 * @author Rengyr
	 */
	private abstract class Operation{
		protected BigDecimal numbers[];
		protected String name;
		protected String visual;
		protected boolean binaryOp;
		protected boolean FSigned;
		protected boolean FDecimal;
		protected boolean SSigned;
		protected boolean SDecimal;
		protected Button button;

		/**
		 * Constructor of Operation.
		 * 
		 * @param name name of operation
		 * @param visual visual representation of operation
		 * @param binaryOp is operation binary, unary for false
		 * @param firstSigned can be first operand signed number
		 * @param firstDecimal can be first operand decimal number
		 * @param secondSigned can be second operand signed number
		 * @param secondDecimal can be second operand decimal number
		 * @param but button that is linked to the operation
		 */
		public Operation(String name, String visual, boolean binaryOp, boolean firstSigned, boolean firstDecimal, boolean secondSigned, boolean secondDecimal, Button but){
			if (binaryOp){
				numbers = new BigDecimal[2];
				this.binaryOp = true;
			}else{
				numbers = new BigDecimal[1];
				this.binaryOp = false;
			}
			this.name = name;
			this.visual = visual;
			this.FSigned = firstSigned;
			this.FDecimal = firstDecimal;
			this.SSigned = secondSigned;
			this.SDecimal = secondDecimal;
			this.button = but;
		}

		/**
		 * Method that calculate operation itself.
		 * 
		 * @return result of operation
		 */
		abstract public BigDecimal function();

		/**
		 * Get visual representation of operation.
		 * 
		 * @return string that represents operation
		 */
		public String getVisual(){
			return visual;
		}

		/**
		 * Set number on place specified by arguments.
		 * 
		 * @param pos position to which set number (0 or 1)
		 * @param value number which should be set
		 */
		public void setNumber(int pos, BigDecimal value){
			if (pos >= 0 && pos < numbers.length){
				numbers[pos] = value;
			}
		}

		/**
		 * Is it binary operation.
		 * 
		 * @return true if operation is binary operation
		 */
		public boolean isBinaryOp(){
			return binaryOp;
		}

		/**
		 * Check if number is legal for operation.
		 * 
		 * @param number number to test
		 * @param pos which operand is number
		 * @return true of operation is legal
		 */
		public boolean isLegalNumber(BigDecimal number, int pos){
			if (pos == 0){
				if (!FSigned && number.doubleValue() < 0) return false;
				else if (!FDecimal && number.doubleValue() % 1 != 0) return false;
			}else{
				if (!SSigned && number.doubleValue() < 0) return false;
				else if (!SDecimal && number.doubleValue() % 1 != 0) return false;
			}
			return true;
		}

		/**
		 * Disable button associated to operation.
		 */
		public void disable(){
			button.setDisable(true);
		}

		/**
		 * Enable button associated to operation.
		 */
		public void enable(){
			button.setDisable(false);
		}

		/**
		 * Can be second operand decimal number
		 * @return true if second operand can be decimal number
		 */
		public boolean canBeSecondSigned(){
			return SSigned;
		}

		/**
		 * Can be second operand signed number
		 * @return true if second operand can be signed number
		 */
		public boolean canBeSecondDecimal(){
			return SDecimal;
		}
	}
}
/*** End of FXMLDocumentController.java file ***/
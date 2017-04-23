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
 *
 * @author Rengyr
 * @author AdamKuba
 */
public class FXMLDocumentController implements Initializable {
	private int outputRoundingNumber = 9;

	private HashMap<String, Operation> ops = new HashMap<String, Operation>();
	private BigDecimal[] numbers = new BigDecimal[2];
	private int numberPos = 0;
	private boolean signed = false;
	private int decimal = 0;

	private Operation selectedOp;

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

	@FXML
	private void handleResultButton(ActionEvent event) {
		doOperation();
	}

	@FXML
	private void handlePlusButton(ActionEvent event) {
		setOperation("add");
	}

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

	@FXML
	private void handleMulButton(ActionEvent event) {
		setOperation("mul");
	}

	@FXML
	private void handleDivButton(ActionEvent event) {
		setOperation("div");
	}

	@FXML
	private void handleModButton(ActionEvent event) {
		setOperation("mod");
	}

	@FXML
	private void handleFacButton(ActionEvent event) {
		setOperation("fac");
	}

	@FXML
	private void handleExpButton(ActionEvent event) {
		setOperation("exp");
	}

	@FXML
	private void handleRootButton(ActionEvent event) {
		setOperation("nroot");
	}

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

	@FXML
	private void handlePointButton(ActionEvent event) {
		decimal = 1;
		updateDisplay();
		updateVisibility();
	}

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

	private String generateZeroes(int num){
		String zeroes = "";
		for (int i = 0; i < num; i++) {
			zeroes += "0";
		}
		return zeroes;
	}

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

	private int getNumberOfDecimalPlaces(BigDecimal num) {
	    String string = num.stripTrailingZeros().toPlainString();
	    int index = string.indexOf(".");
	    return index < 0 ? 0 : string.length() - index - 1;
	}

	private int getTrailingZeroes(BigDecimal num){
		return decimal-getNumberOfDecimalPlaces(num)-1;
	}

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

		abstract public BigDecimal function();

		public String getVisual(){
			return visual;
		}

		public void setNumber(int pos, BigDecimal value){
			if (pos >= 0 && pos < numbers.length){
				numbers[pos] = value;
			}
		}

		public boolean isBinaryOp(){
			return binaryOp;
		}

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

		public void disable(){
			button.setDisable(true);
		}

		public void enable(){
			button.setDisable(false);
		}

		public boolean canBeSecondSigned(){
			return SSigned;
		}

		public boolean canBeSecondDecimal(){
			return SDecimal;
		}
	}
}

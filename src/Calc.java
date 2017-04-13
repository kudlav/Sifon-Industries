import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 *
 * @author kudlav
 * @author AdamKuba
 */
public class Calc extends Application {
	
	public static FXMLDocumentController controller;

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

		Scene scene = new Scene(root);
		

		stage.setScene(scene);
		stage.show();

		scene.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.NUMPAD1 || e.getCode() == KeyCode.DIGIT1) {
		        controller.keyPressed('1');
		    }else if (e.getCode() == KeyCode.NUMPAD2 || e.getCode() == KeyCode.DIGIT2) {
		        controller.keyPressed('2');
		    }else if (e.getCode() == KeyCode.NUMPAD3 || e.getCode() == KeyCode.DIGIT3) {
		        controller.keyPressed('3');
		    }else if (e.getCode() == KeyCode.NUMPAD4 || e.getCode() == KeyCode.DIGIT4) {
		        controller.keyPressed('4');
		    }else if (e.getCode() == KeyCode.NUMPAD5 || e.getCode() == KeyCode.DIGIT5) {
		        controller.keyPressed('5');
		    }else if (e.getCode() == KeyCode.NUMPAD6 || e.getCode() == KeyCode.DIGIT6) {
		        controller.keyPressed('6');
		    }else if (e.getCode() == KeyCode.NUMPAD7 || e.getCode() == KeyCode.DIGIT7) {
		        controller.keyPressed('7');
		    }else if (e.getCode() == KeyCode.NUMPAD8 || e.getCode() == KeyCode.DIGIT8) {
		        controller.keyPressed('8');
		    }else if (e.getCode() == KeyCode.NUMPAD9 || e.getCode() == KeyCode.DIGIT9) {
		        controller.keyPressed('9');
		    }else if (e.getCode() == KeyCode.NUMPAD0 || e.getCode() == KeyCode.DIGIT0) {
		        controller.keyPressed('0');
		    }else if (e.getCode() == KeyCode.ADD || e.getCode() == KeyCode.PLUS) {
		        controller.keyPressed('+');
		    }else if (e.getCode() == KeyCode.SUBTRACT || e.getCode() == KeyCode.MINUS) {
		        controller.keyPressed('-');
		    }else if (e.getCode() == KeyCode.MULTIPLY) {
		        controller.keyPressed('*');
		    }else if (e.getCode() == KeyCode.DIVIDE || e.getCode() == KeyCode.SLASH) {
		        controller.keyPressed('/');
		    }else if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.EQUALS) {
		        controller.keyPressed('=');
		    }else if (e.getCode() == KeyCode.PERIOD || e.getCode() == KeyCode.COMMA || e.getCode() == KeyCode.DECIMAL) {
		        controller.keyPressed('.');
		    }else if (e.getCode() == KeyCode.BACK_SPACE) {
		        controller.keyPressed('b');
		    }else if (e.getCode() == KeyCode.ESCAPE || e.getCode() == KeyCode.DELETE) {
		        controller.keyPressed('c');
		    }
		});
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	

}

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
 * @author Moose
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label digit;
    
    @FXML
    private void handleNumberButon(ActionEvent event) {
        String text = ((Button)event.getSource()).getText();
        System.out.print(text);
        // TODO calling a newDigitHandler
    }

    @FXML
    private void handleResultButton(ActionEvent event) {
        // TODO
    }

    @FXML
    private void handlePlusButton(ActionEvent event) {
        // TODO
    }

    @FXML
    private void handleMinusButton(ActionEvent event) {
        // TODO
    }

    @FXML
    private void handleMulButton(ActionEvent event) {
        // TODO
    }

    @FXML
    private void handleDivButton(ActionEvent event) {
        // TODO
    }

    @FXML
    private void handleModButton(ActionEvent event) {
        // TODO
    }

    @FXML
    private void handleClearButton(ActionEvent event) {
        // TODO
    }

    @FXML
    private void handleFacButton(ActionEvent event) {
        // TODO
    }

    @FXML
    private void handleExpButton(ActionEvent event) {
        // TODO
    }

    @FXML
    private void handleRootButton(ActionEvent event) {
        // TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

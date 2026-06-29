package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import static org.unibl.etf.logger.MyLogger.logger;

import java.io.IOException;
import java.time.Year;
import java.util.logging.Level;

import javafx.scene.Parent;
import org.unibl.etf.client.rest.*;
import org.unibl.etf.dto.*;
import org.unibl.etf.model.*;

public class RegistrationController {

	@FXML
    private TextField firstNameField;
	@FXML
    private TextField lastNameField;
	@FXML
    private TextField addressField;
	@FXML
    private TextField phoneNumberField;
	@FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private TextField idField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField manufacturerField;
    @FXML
    private TextField yearOfProductionField;
    
    @FXML
    private Button registerButton;


    @FXML
    private void switchToLoginScene() {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneLogin.fxml"));
        	Parent root = loader.load();
        	Stage stage = (Stage) registerButton.getScene().getWindow();
        	
        	Scene scene = new Scene(root);
        	stage.setScene(scene);
        	stage.setTitle("Login");
        	stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", e);
        }
    } 
    
    @FXML
    private void loginHandler() {
    	String fName = firstNameField.getText();
    	String lName = lastNameField.getText();
    	
    	String address = addressField.getText();
    	String phone = phoneNumberField.getText();
    	String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        String id = idField.getText();
        String model = modelField.getText();
        String manufacturer = manufacturerField.getText();

        if (username.isEmpty() || password.isEmpty() || fName.isEmpty() || lName.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showAlert("Error", "Please enter your details.");
            return;
        }
        
        else if(id.isEmpty() || model.isEmpty() || manufacturer.isEmpty()) {
        	 showAlert("Error", "Please enter vehicle information.");
             return;
        }
        
        String yearText = yearOfProductionField.getText();
        if (yearText.isEmpty()) {
            showAlert("Error", "Please enter the year of manufacture.");
            return;
        }

        int yearOfProduction;
        try {
            yearOfProduction = Integer.parseInt(yearText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Year of manufacture must be a number.");
            return;
        }

        if (yearOfProduction <= 0 || yearOfProduction > Year.now().getValue()) {
            showAlert("Error", "The year of manufacture is not valid.");
            return;
        } 
        
        Vehicle vehicle = new Vehicle(id, model, manufacturer, yearOfProduction);
        
        RegistrationDTO registrationDto = new RegistrationDTO(fName, lName, vehicle, address, phone, email, username, password);
        try {
        	if (RegistrationRESTController.registerRequest(registrationDto)) {
            	// The user sent the application request to the repairer -> waiting for the repairer's approval
        		switchToLoginScene();
            } else {
                showAlert("Unsuccessful login", "The entered data is not correct.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", e);
            showAlert("Error", "There was a problem with the server.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


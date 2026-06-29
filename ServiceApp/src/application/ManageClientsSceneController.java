package application;

import static org.unibl.etf.logger.MyLogger.logger;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import org.unibl.etf.model.Client;
import org.unibl.etf.service.rest.ClientRESTController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManageClientsSceneController {
	@FXML
	private Button back;
	@FXML
	private Button approveButton;
	@FXML
	private Button blockButton;
	@FXML
	private Button deleteButton;
	@FXML
	private TableView<Client> clientsTable;
	@FXML
	private TableColumn<Client, String> firstNameCol;
	@FXML
	private TableColumn<Client, String> lastNameCol;
	@FXML
	private TableColumn<Client, String> phoneCol;
	@FXML
	private TableColumn<Client, String> emailCol;
	@FXML
	private TableColumn<Client, String> usernameCol;
	@FXML
	private TableColumn<Client, String> statusCol;

	
	@FXML
    private void switchToMainScene() {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneMain.fxml"));
        	Parent root = loader.load();
        	Stage stage = (Stage) back.getScene().getWindow();
        	
        	Scene scene = new Scene(root);
        	stage.setScene(scene);
        	stage.setTitle("Main screen");
        	stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", e);
        }
    }
	
	@FXML
	public void initialize() {
	    firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	    lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	    phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
	    emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
	    usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
	    statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

	    loadClients();
	}
	
	private void loadClients() {
	    List<Client> clients = ClientRESTController.getAllClients();
	    clientsTable.getItems().setAll(clients);
	}

	@FXML
	private void approveClient() {
	    Client selected = clientsTable.getSelectionModel().getSelectedItem();
	    if (selected != null) {
	        boolean ok = ClientRESTController.approveClientAccount(selected.getId());
	        if (ok) {
	            loadClients();
	        }
	    }
	}

	@FXML
	private void blockClient() {
	    Client selected = clientsTable.getSelectionModel().getSelectedItem();
	    if (selected != null) {
	        boolean ok = ClientRESTController.blockClientAccount(selected.getId());
	        if (ok) {
	            loadClients();
	        }
	    }
	}

	@FXML
	private void deleteClient() {
	    Client selected = clientsTable.getSelectionModel().getSelectedItem();
	    if (selected != null) {
	        boolean ok = ClientRESTController.removeClient(selected.getId());
	        if (ok) {
	            loadClients();
	        }
	    }
	}


	
}

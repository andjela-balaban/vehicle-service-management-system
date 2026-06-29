package application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.unibl.etf.model.Appointment;
import org.unibl.etf.model.Client;
import org.unibl.etf.service.rest.AppintmentRESTController;
import org.unibl.etf.service.rest.ClientRESTController;

import static org.unibl.etf.logger.MyLogger.logger;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class ManageAppointmentsSceneController {

    @FXML 
    private TableView<Appointment> appointmentsTable;
    @FXML
    private TableColumn<Appointment, String> dateTimeCol;
    @FXML
    private TableColumn<Appointment, String> serviceTypeCol;
    @FXML
    private TableColumn<Appointment, String> usernameCol;
    @FXML 
    private TableColumn<Appointment, String> statusCol;
    @FXML
    private TableColumn<Appointment, String> commentCol;
    @FXML
    private Button editButton;
    @FXML
    private Button back;

    
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
        // column mapping
        dateTimeCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDateTimeFormatted()));

        serviceTypeCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getServiceType().toString()));

        usernameCol.setCellValueFactory(cellData -> {
            int clientId = cellData.getValue().getClinetId();
            Client client = ClientRESTController.getClientById(clientId); 
            String username = (client != null) ? client.getUsername() : "Unknown";
            return new javafx.beans.property.SimpleStringProperty(username);
        });

        statusCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus().toString()));

        commentCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getComment()));

        // filling in the table
        refreshAppointments();

        editButton.setOnAction(e -> {
            Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    openDialog(selected, "Appointment change");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Error opening dialog.").showAndWait();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "You must select an appointment to modify.").showAndWait();
            }
        });
    }

    private void refreshAppointments() {
        List<Appointment> appointments = AppintmentRESTController.getAllAppointments();
        if (appointments != null) {
            appointmentsTable.setItems(FXCollections.observableArrayList(appointments));
        }
    }
    
    private void openDialog(Appointment appointment, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditAppointmentDialog.fxml"));
        Parent root = loader.load();

        EditAppointmentDialogController controller = loader.getController();
        controller.setAppointment(appointment);

        Stage dialogStage = new Stage();
        dialogStage.setTitle(title);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(appointmentsTable.getScene().getWindow());
        dialogStage.setScene(new Scene(root));
        dialogStage.showAndWait();

        Appointment updated = controller.getAppointment();
        if (updated != null) {
            int index = appointmentsTable.getItems().indexOf(appointment);
            if (index >= 0) {
                appointmentsTable.getItems().set(index, updated); 
            }
        }
    }

    
}


package application;

import static org.unibl.etf.logger.MyLogger.logger;

import java.io.IOException;
import java.util.logging.Level;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.unibl.etf.model.Item;
import org.unibl.etf.model.Order;
import org.unibl.etf.supplierapp.server.SupplierClient;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OrdersSceneController {

    @FXML
    private TextField orderIdField;
    @FXML
    private TextField itemField;
    @FXML
    private TextField quantityField;
    @FXML
    private Button nextOrderButton;
    @FXML
    private Button approveButton;
    @FXML
    private Button rejectButton;
    @FXML
    private Button back;

    private SupplierClient client;
    private Order currentOrder;

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
    private void initialize() {
        this.client = LoginSceneController.getSupplierClient();
        orderIdField.setEditable(false);
        itemField.setEditable(false);
        quantityField.setEditable(false);

        nextOrderButton.setOnAction(e -> loadNextOrder());
        approveButton.setOnAction(e -> approveOrder());
        rejectButton.setOnAction(e -> rejectOrder());
    }

    private void loadNextOrder() {
        try {
            currentOrder = client.getNextOrder();
            if (currentOrder != null) {
                orderIdField.setText(String.valueOf(currentOrder.getId()));
                Item item = currentOrder.getItem();
                itemField.setText(item != null ? item.getTitle() : "-");
                quantityField.setText(String.valueOf(currentOrder.getQuantity()));
            } else {
                clearFields();
                showNoOrdersAlert();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", ex);
        }
    }

    private void approveOrder() {
        if (currentOrder == null) return;
        try {
            boolean success = client.approveOrder(currentOrder.getId());
            if (success) {
                clearFields();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", ex);
        }
    }

    private void rejectOrder() {
        if (currentOrder == null) return;
        try {
            boolean success = client.rejectOrder(currentOrder.getId());
            if (success) {
                clearFields();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Exception: ", ex);
        }
    }

    private void clearFields() {
        orderIdField.clear();
        itemField.clear();
        quantityField.clear();
        currentOrder = null;
    }

    private void showNoOrdersAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Orders");
        alert.setHeaderText(null);
        alert.setContentText("No more orders in queue.");
        alert.showAndWait();
    }
}

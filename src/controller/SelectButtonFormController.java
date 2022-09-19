package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SelectButtonFormController {

    public AnchorPane anchorSelectButtonId;

    public void SystemReportOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../views/SystemReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window   = (Stage) anchorSelectButtonId.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void logInManageItemOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../views/ManageItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) anchorSelectButtonId.getScene().getWindow();
        window.setScene(new Scene(load));

    }

    public void btnSelectLogOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window   = (Stage) anchorSelectButtonId.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}

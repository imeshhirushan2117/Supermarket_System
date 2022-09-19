package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public AnchorPane anchorLogId;

    public void logInCashierOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../views/CashierLoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window   = (Stage) anchorLogId.getScene().getWindow();
        window.setScene(new Scene(load));

    }

    public void logInAdministratorOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../views/AdministratorLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window   = (Stage) anchorLogId.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}

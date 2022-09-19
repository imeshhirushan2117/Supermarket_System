package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CashierLoginFormController {

    public JFXTextField txtLogNameId;
    public JFXPasswordField txtLogPasswordId;
    public Label lblAttemptId;
    public AnchorPane anchorSignInId;

    public void cashierSignInOnAction(ActionEvent actionEvent) throws IOException {

        if (txtLogNameId.getText().equals("") & txtLogPasswordId.getText().equals("")) {

            URL resource = getClass().getResource("../views/SignInCashierForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) anchorSignInId.getScene().getWindow();
            window.setScene(new Scene(load));

        } else {
            lblAttemptId.setVisible(true);
        }
    }

    public void btnBackLogInId(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) anchorSignInId.getScene().getWindow();
        window.setScene(new Scene(load));

    }

    public void txtUserEnterOnAction(ActionEvent actionEvent) {
        txtLogPasswordId.requestFocus();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) anchorSignInId.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}

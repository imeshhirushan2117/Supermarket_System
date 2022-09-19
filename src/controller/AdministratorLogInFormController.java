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

public class AdministratorLogInFormController {

    public AnchorPane anchorAdministratorId;
    public JFXTextField txtLogNameId;
    public JFXPasswordField txtLogPasswordId;
    public Label lblAttemptId;

    public void btnBackLogInId(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../views/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) anchorAdministratorId.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void administratorSignInOnAction(ActionEvent actionEvent) throws IOException {

        if (txtLogNameId.getText().equals("") & txtLogPasswordId.getText().equals("")) {

            URL resource = getClass().getResource("../views/SelectButtonForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) anchorAdministratorId.getScene().getWindow();
            window.setScene(new Scene(load));

        } else {
            lblAttemptId.setVisible(true);
        }

    }

    public void txtUserEnterOnAction(ActionEvent actionEvent) {
        txtLogPasswordId.requestFocus();
    }
}

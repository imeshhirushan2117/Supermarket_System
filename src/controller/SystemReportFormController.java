package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SystemReportFormController {

    public AnchorPane anchorReportId;

    public void btnBackSelectReportOnAction (ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/SelectButtonForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window   = (Stage) anchorReportId .getScene().getWindow();
        window.setScene(new Scene(load));
    }
}

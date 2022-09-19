package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Customer;

import java.sql.SQLException;

public class addCustomerFormController {
    public JFXButton btnCancelId;

    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerTitleId;
    public JFXTextField txtCustomerNameId;
    public JFXTextField txtCustomerAddressId;
    public JFXTextField txtCustomerCityId;
    public JFXTextField txtProvinceId;
    public JFXTextField txtPostCodeId;

    public void btnCancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelId.getScene().getWindow();
        stage.close();

    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c = new Customer(
                txtCustomerId.getText(),
                txtCustomerTitleId.getText(),
                txtCustomerNameId.getText(),
                txtCustomerAddressId.getText(),
                txtCustomerCityId.getText(),
                txtProvinceId.getText(),
                txtPostCodeId.getText()
        );
        if (new CustomerController().saveCustomer(c)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
            Stage stage = (Stage) btnCancelId.getScene().getWindow();
            stage.close();
        } else {
           new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
    }
}

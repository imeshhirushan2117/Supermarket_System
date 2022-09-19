package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.tm.CartTM;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import static controller.CustomerOrderFormController.parentObList;

public class ManageCustomerOrderFormController {

    public JFXButton btnCancelId;
    public AnchorPane anchorManageId;
    public JFXComboBox cmbOrderId;

    static ObservableList<CartTM> observableList = FXCollections.observableArrayList();
    public TableView tblGetDetails;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colNetTotal;
    public JFXTextField txtQty;
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtNetTotal;
    public JFXTextField txtDiscount;

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../views/SignInCashierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window   = (Stage) anchorManageId.getScene().getWindow();
        window.setScene(new Scene(load));

    }
    /*public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colNetTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        Bindings.bindContentBidirectional(observableList, CustomerOrderFormController.getOrderId ());

        tblGetDetails.setItems(observableList);

        try {
            loadItemIds();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new OrderController().getAllItemIds();
        cmbOrderId.getItems().addAll(itemIds);
    }

    public void btnConfimEditOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CartTM c1 = new CartTM(
                txtItemCode.getText(),
                txtDescription.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtNetTotal.getText()),
                Integer.parseInt(txtDiscount.getText()),
                Double.parseDouble(txtNetTotal.getText())
        );
        if(new OrderController().updateOrder(c1)){
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "TryAgain..").show();
        }
    }

    public void cancelOrderOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancelId.getScene().getWindow();
        stage.close();
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void addDataOnAction(MouseEvent mouseEvent) {
        CartTM cart = null;
        try {
            cart = (CartTM) tblGetDetails.getSelectionModel().getSelectedItem();
            txtItemCode.setText(cart.getCode());
            txtDescription.setText(cart.getDescription());
            txtQty.setText("" + cart.getQty());
            txtDiscount.setText("" + cart.getDiscount());
            txtNetTotal.setText("" + cart.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


}

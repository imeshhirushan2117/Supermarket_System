package controller;

import bd.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Item;
import views.tm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageItemFormController {
    public AnchorPane anchorManageItemId;
    public JFXTextField txtManageItemCodeId;
    public JFXTextField txtManageDescriptionId;
    public JFXTextField txtManagePackSizeId;
    public JFXTextField txtManageUnitPriceId;
    public JFXTextField txtManageQtyId;
    public TableView<ItemTM> tblManageId;
    public TableColumn colItemCodeId;
    public TableColumn colDescriptionId;
    public TableColumn colPackSizeId;
    public TableColumn colUnitPriceId;
    public TableColumn tblQtyHandId;
    public TableColumn tblDiscountId;
    public JFXTextField lblDiscountId;
    public JFXButton btnEditId;
    public JFXButton btnAddItemId;
    public JFXButton btnClearFiledId;
    public JFXButton btnDeleteItemId;
    int SelectedRow = -1;

    public void btnBackManageItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/SelectButtonForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) anchorManageItemId.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnCashierLogOutOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../views/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) anchorManageItemId.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void AddItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        String itemCode = txtManageItemCodeId.getText();
        String description = txtManageDescriptionId.getText();
        String packetSize = txtManagePackSizeId.getText();
        double unitPrize = Double.parseDouble(txtManageUnitPriceId.getText());
        int qtyOnHand = Integer.parseInt(txtManageQtyId.getText());
        int discount = Integer.parseInt(lblDiscountId.getText());

        ItemTM i1 = new ItemTM(
                itemCode,
                description,
                packetSize,
                unitPrize,
                qtyOnHand,
                discount

        );
        getItemList().add(i1);
        tblManageId.setItems(getItemList());

        Item i = new Item(txtManageItemCodeId.getText(), txtManageDescriptionId.getText(), txtManagePackSizeId.getText(),
                Double.parseDouble(txtManageUnitPriceId.getText()),
                Integer.parseInt(txtManageQtyId.getText()), Integer.parseInt(lblDiscountId.getText()));

        if (new ItemController().saveItem(i)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved...").show();
            showItem();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }
        clearFieId();
    }

    public void clearFieId() {
        txtManageItemCodeId.clear();
        txtManageDescriptionId.clear();
        txtManageUnitPriceId.clear();
        txtManagePackSizeId.clear();
        txtManageQtyId.clear();
        tblManageId.requestFocus();
        lblDiscountId.clear();

        btnAddItemId.setDisable(false);
        btnEditId.setDisable(true);
        btnDeleteItemId.setDisable(true);
        txtManageItemCodeId.setEditable(true);
    }

    public void initialize() {
        try {
            showItem();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnEditId.setDisable(true);
        btnDeleteItemId.setDisable(true);

        tblManageId.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            SelectedRow = (int) newValue;
            if (SelectedRow == -1) {
                btnAddItemId.setDisable(false);
                btnEditId.setDisable(true);
                btnDeleteItemId.setDisable(true);
                txtManageItemCodeId.setEditable(true);
            } else {
                btnAddItemId.setDisable(true);
                btnEditId.setDisable(false);
                btnDeleteItemId.setDisable(false);
                txtManageItemCodeId.setEditable(false);
            }
        });
    }


    public void clearFiledOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        clearFieId();
        showItem();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ItemTM item = tblManageId.getSelectionModel().getSelectedItem();
        String itemCode = item.getItemCode();

        if (new ItemController().deleteItem(itemCode)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete Selected Item").show();
            showItem();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clearFieId();

    }

    public ObservableList<ItemTM> getItemList() throws SQLException, ClassNotFoundException {

        ObservableList<ItemTM> obList = FXCollections.observableArrayList();

        Connection con = DbConnection.getInstance().getConnection();

        String query = "SELECT * FROM Item";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            obList.add(new ItemTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getInt(6)
            ));
        }
        return obList;
    }

    public void showItem() throws SQLException, ClassNotFoundException {

        ObservableList<ItemTM> list = getItemList();

        colItemCodeId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescriptionId.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSizeId.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPriceId.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblQtyHandId.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblDiscountId.setCellValueFactory(new PropertyValueFactory<>("discount"));

        tblManageId.setItems(list);
    }

    public void btnEditOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ItemTM i1 = new ItemTM(
                txtManageItemCodeId.getText(),
                txtManageDescriptionId.getText(),
                txtManagePackSizeId.getText(),
                Double.parseDouble(txtManageUnitPriceId.getText()),
                Integer.parseInt(txtManageQtyId.getText()),
                Integer.parseInt(lblDiscountId.getText()));

        if (new ItemController().update(i1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update..").show();
            showItem();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void handleMouseOnAction(MouseEvent mouseEvent) {

        ItemTM itemTM = null;
        try {
            ItemTM item = tblManageId.getSelectionModel().getSelectedItem();
            txtManageItemCodeId.setText(item.getItemCode());
            txtManageUnitPriceId.setText("" + item.getUnitPrice());
            txtManageDescriptionId.setText(item.getDescription());
            txtManageQtyId.setText("" + item.getQtyOnHand());
            lblDiscountId.setText("" + item.getDiscount());
            txtManagePackSizeId.setText("" + item.getPackSize());
        } catch (Exception e) {

        }
    }
}

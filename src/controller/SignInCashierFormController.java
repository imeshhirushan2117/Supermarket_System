package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Item;
import views.tm.CartTM;
import views.tm.ItemTM;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class SignInCashierFormController {


    public AnchorPane anchorSignInCustomerId;
    public Label lblDateId;
    public Label lblTimeId;
    public JFXComboBox cmbItemId;
    public JFXTextField txtDescriptionId;
    public JFXTextField txtPackSizeId;
    public JFXTextField txtUnitePriceId;
    public JFXTextField txtQtyOnHandId;
    public JFXTextField txtQtyId;
    public JFXTextField txtDiscountId;
    public TableView <CartTM> tblCashierId;
    public TableColumn colItemCodeId;
    public TableColumn colDescriptionId;
    public TableColumn colQtyId;
    public TableColumn ColUnitPriceId;
    public TableColumn colDiscountId;
    public TableColumn colTotalId;
    public Label lblTotalAmountId;
    public JFXButton btnOderId;

    public void btnCashierLogOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window   = (Stage) anchorSignInCustomerId .getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void localTime() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
            lblTimeId.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    public void localDate() {

        SimpleDateFormat DateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        lblDateId.setText(DateFormatter.format(date));
    }

    public void initialize(){
        localDate();
        localTime();

        try {
            lodeItemIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbItemId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        colItemCodeId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescriptionId.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDiscountId.setCellValueFactory(new PropertyValueFactory<>("discount"));
        ColUnitPriceId.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyId.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalId.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblCashierId.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
    }

    public static   ObservableList< CartTM > obList = FXCollections.observableArrayList();
    private int cartSelectedRowForRemove = -1;

    public static ObservableList getCart(){
        return obList;
    }


    public void btnAddToCadOnAction(ActionEvent actionEvent) {

        String description = txtDescriptionId.getText();
        int packetSize = Integer.parseInt(txtPackSizeId.getText());
        double unitPrice = Double.parseDouble(txtUnitePriceId.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHandId.getText());
        int qtyForCustomer = Integer.parseInt(txtQtyId.getText());
        int discount = Integer.parseInt(txtDiscountId.getText());
        double total = qtyForCustomer * unitPrice - qtyForCustomer * unitPrice * discount / 100;

        if(qtyOnHand< qtyForCustomer){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }

        CartTM tm = new CartTM(
                (String) cmbItemId.getValue(),
                description,
                qtyForCustomer,
                unitPrice,
                discount,
                total
        );

        int rowNumber = isExists(tm);

        if (rowNumber==-1) {
            obList.add(tm);

        }else {
            CartTM temp = obList.get(rowNumber);
            CartTM newTm = new CartTM(
                    temp.getCode(),
                    temp.getDescription(),
                    temp.getQty()+ qtyForCustomer,
                    unitPrice,
                    temp.getDiscount(),
                    total+ temp.getTotal()

            );

        if (qtyOnHand <temp.getQty()+qtyForCustomer){
            new Alert(Alert.AlertType.WARNING,"Invalid Qty...").show();
            return ;
        }
            obList.remove(rowNumber);
            obList.add(newTm);
        }

        tblCashierId.setItems(obList);
        calculateCost();

    }

    private int isExists(CartTM tm){
        for (int i = 0; i < obList.size(); i++){
            if (tm.getCode().equals(obList.get(i).getCode())){
                return i;
            }
        }
        return -1;
    }

    void calculateCost(){

        double ttl = 0;
        for (CartTM tm:obList
        ) {
            ttl+= tm.getTotal();
        }
        String formatTotal = new DecimalFormat("0.00").format(ttl);

        lblTotalAmountId.setText(formatTotal+" /=");
        clearFieId();
    }

    private void lodeItemIds() throws SQLException, ClassNotFoundException {
        List <String> itemIds = new ItemController().getAllItemIds();
        cmbItemId.getItems().addAll(itemIds);
    }

    private void setItemData (String itemCode) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(itemCode);

        if (i1 == null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..");
        }else{
            txtDescriptionId.setText(i1.getDescription());
            txtUnitePriceId.setText(String.valueOf(i1.getUnitPrice()));
            txtPackSizeId.setText(String.valueOf(i1.getPackSize()));
            txtQtyOnHandId.setText(String.valueOf(i1.getQtyOnHand()));
            txtDiscountId.setText(String.valueOf(i1.getDiscount()));
        }
    }

    public void btnCloseOderOnAction(ActionEvent actionEvent) {
        clearFieId();
        lblTotalAmountId.setText("");
        obList.clear();

    }

    public void clearFieId() {
        txtDiscountId.clear();
        txtDescriptionId.clear();
        txtUnitePriceId.clear();
        txtPackSizeId.clear();
        txtQtyOnHandId.clear();
        txtQtyId.clear();
        cmbItemId.setValue("");
        cmbItemId.requestFocus();

        tblCashierId.getSelectionModel().clearSelection();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        if (cartSelectedRowForRemove == -1){
            lblTotalAmountId.setText("");
            new Alert(Alert.AlertType.WARNING,"PLese Select a row..").show();
        } else {
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblCashierId.refresh();
        }
        clearFieId();
    }

    public void btnOderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CustomerOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void btnManageCustomerOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../views/ManageCustomerOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) anchorSignInCustomerId.getScene().getWindow();
        window.setScene(new Scene(load));

    }
}

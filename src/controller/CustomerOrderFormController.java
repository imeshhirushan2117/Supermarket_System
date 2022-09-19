package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
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
import model.Customer;
import model.ItemDetails;
import model.Order;
import views.tm.CartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerOrderFormController {
    public AnchorPane anchorOderId;
    public JFXComboBox <String> cmbCustomerId;
    public JFXButton btnCancelId;
    public JFXTextField txtCustomerTitleId;
    public JFXTextField txtCustomerNameId;
    public JFXTextField txtCustomerAddressId;
    public TableView <CartTM> tblCustomerId;
    public TableColumn colItemCodeId;
    public TableColumn colDescriptionId;
    public TableColumn colQtyId;
    public TableColumn colTotalId;
    public TableColumn colDiscountId;
    public Label lblOrderId;
    public Label lblTimeId;
    public Label lblDateId;
    public JFXButton btnConformId;
    public JFXTextField txtAmount;
    public JFXTextField txtSaveAmount;
    public JFXTextField txtAmountPaid;
    public JFXTextField txtBalance;
    double netPrice;

    static ObservableList<CartTM> parentObList = FXCollections.observableArrayList();

    //static  ObservableList<CartTM> obList = FXCollections.observableArrayList();


    public void addCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/addCustomerForm.fxml ");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Stage stage = (Stage) btnCancelId.getScene().getWindow();
        stage.close();
    }

    public void btnConformOderId(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

       /* Stage stage = (Stage) btnConformId.getScene().getWindow();
        stage.close();*/

        ArrayList<ItemDetails> items = new ArrayList<>();
        for (CartTM tempTm : parentObList
        ) {
            items.add(
                    new ItemDetails(
                            tempTm.getCode(),
                            lblOrderId.getText(),
                            tempTm.getQty(),
                            tempTm.getDiscount()
                    ));
        }

        Order order = new Order(
                lblOrderId.getText(),
                lblDateId.getText(),
                cmbCustomerId.getValue(),
                items
        );
        if (new OrderController().placeOrder(order)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            Stage stage = (Stage) btnCancelId.getScene().getWindow();
            stage.close();

        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Try Again").show();
        }


    }

    public void initialize() {

        colItemCodeId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescriptionId.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQtyId.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalId.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDiscountId.setCellValueFactory(new PropertyValueFactory<>("discount"));

        tblCustomerId.setItems(parentObList);

        localTime();
        localDate();
        setOrderId ();

        try {
            lodeCustomerId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {
                setCustomerData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        Bindings.bindContentBidirectional(parentObList,SignInCashierFormController.getCart());

        for (CartTM tm: tblCustomerId.getItems()){
            netPrice+=tm.getTotal();
        }txtAmount.setText(new DecimalFormat("0.00").format(netPrice)+" /=");

        double unit =0.00;
        double main =0;
        int qty=0;
        for (CartTM tm : tblCustomerId.getItems()){
            qty += tm.getQty();
            unit += tm.getUnitPrice();
            main+=qty * unit;

        }
        txtSaveAmount.setText(new DecimalFormat("0.00").format(main-netPrice)+" /=");

    }

    private void lodeCustomerId() throws SQLException, ClassNotFoundException {
        List <String> id = new CustomerController().getAllCustomerId();
        cmbCustomerId.getItems().addAll(id);
    }

    private  void setCustomerData(String id) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().passCustomerDetails(id);
        if (c1 == null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set...").show();
        } else {
            txtCustomerTitleId.setText(c1.getCustomerTitle());
            txtCustomerNameId.setText(c1.getCustomerName());
            txtCustomerAddressId.setText(c1.getCustomerAddress());
        }
    }

    private  void setOrderId (){
        try {
            lblOrderId.setText(new OrderController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void localTime() {

        Timeline clock = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
            lblTimeId.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    public void localDate() {

        SimpleDateFormat DateFormatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        lblDateId.setText(DateFormatter.format(date));
    }

/*Refresh ComboBox*/
    public void btnRefreshOnAction(ActionEvent actionEvent) {

        cmbCustomerId.getItems().clear();
        try {
            lodeCustomerId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void txtAmountPaidOnAction(ActionEvent actionEvent) {

        int AmountPaidText = Integer.parseInt(txtAmountPaid.getText());
        txtBalance.setText(new DecimalFormat("0.00").format(AmountPaidText - netPrice)+" /=");
    }
}

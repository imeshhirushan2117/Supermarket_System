package controller;

import bd.DbConnection;
import javafx.collections.ObservableList;
import model.ItemDetails;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    /*public static ObservableList getCart(){
        return parentObList;}*/

    public String getOrderId () throws SQLException, ClassNotFoundException {
        ResultSet rst = (ResultSet) DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM `order` ORDER BY orderId DESC LIMIT 1 ").executeQuery();
        if (rst.next()){
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId+1 ;

            if (tempId <9){
                return "O-00" + tempId;

            } else if (tempId < 99){
                return "O-0" + tempId;

            } else {
                return "O-" +tempId;
            }
        }else {
            return "O-001";
        }
    }

    public boolean placeOrder (Order order) throws SQLException, ClassNotFoundException {
        Connection con = null;

        try {

            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = DbConnection.getInstance().getConnection().
                    prepareStatement("INSERT INTO `order` Values (?,?,?)");
            stm.setObject(1, order.getOrderId());
            stm.setObject(2, order.getOrderDate());
            stm.setObject(3, order.getOrderCustomerId()
            );

            if (stm.executeUpdate() > 0) {

                if (saveOrderDetails(order.getOrderId(), order.getItems())) {
                    con.commit();
                    return true;

                } else {
                    con.rollback();
                    return false;
                }

            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException e ) {
            e.printStackTrace();

        } catch (ClassNotFoundException e){
            e.printStackTrace();

        } finally {
            {
                con.setAutoCommit(true);
            }
        }
        return false;
    }

    public boolean saveOrderDetails (String orderId , ArrayList <ItemDetails> items ) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp : items
        ) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().
                    prepareStatement("INSERT INTO `order detail` VALUE  (?,?,?,?)");
            stm.setObject(1, temp.getItemCode());
            stm.setObject(2, orderId);
            stm.setObject(3, temp.getQty());
            stm.setObject(4, temp.getDiscount());

            if (stm.executeUpdate() > 0) {
                if (updateQty(temp.getItemCode(), temp.getQty())) {

                } else {
                    return false;
                }
            } else {
                return false ;
            }
        }
        return true ;
}
    public boolean updateQty (String itemCode , int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE Item SET QtyOnHand = (QtyOnHand-'"+ qty + "')WHERE itemCode= '" + itemCode + "'");

        return stm.executeUpdate() > 0;

    }
}

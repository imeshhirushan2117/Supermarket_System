package controller;

import bd.DbConnection;
import model.Item;
import views.tm.ItemTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService {

    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException, SQLException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> items = new ArrayList<>();
        while (rst.next()) {
            items.add(
                    rst.getString(1)
            );
        }
        return items;
    }

    @Override
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Item VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,i.getItemCode());
        stm.setObject(2,i.getDescription());
        stm.setObject(3,i.getPackSize());
        stm.setObject(4,i.getUnitPrice());
        stm.setObject(5,i.getQtyOnHand());
        stm.setObject(6,i.getDiscount());

        return stm.executeUpdate() > 0 ;
    }

    @Override
    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item").executeQuery();
        List<String>ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    @Override
    public Item getItem(String Id) throws SQLException, ClassNotFoundException {
       ResultSet rst =  DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item WHERE ItemCode='"+ Id + "'").executeQuery();
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getInt(6)
            );
        }else {
            return null;
        }
    }

    @Override
    public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {

        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE ItemCode = '" + itemCode + "'").executeUpdate() > 0;
    }

    @Override
    public boolean update(ItemTM item) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE Item SET Discription=?,PackSize=?,UnitPrice=?,QtyOnHand =?, discount=? WHERE ItemCode=? ");
        stm.setObject(1,item.getDescription());
        stm.setObject(2,item.getPackSize());
        stm.setObject(3,item.getUnitPrice());
        stm.setObject(4,item.getQtyOnHand());
        stm.setObject(5,item.getDiscount());
        stm.setObject(6,item.getItemCode());

        return stm.executeUpdate() > 0;
    }
}

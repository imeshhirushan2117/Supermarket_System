package controller;

import model.Item;
import views.tm.ItemTM;

import java.sql.SQLException;
import java.util.List;

public interface ItemService {

    boolean saveItem(Item i) throws SQLException, ClassNotFoundException;
    public List <String> getAllItemIds() throws SQLException, ClassNotFoundException;
    public Item getItem (String Id) throws SQLException, ClassNotFoundException;
    public boolean deleteItem (String itemCode) throws SQLException, ClassNotFoundException;
    public boolean update(ItemTM item) throws SQLException, ClassNotFoundException;
}
//abstract interface



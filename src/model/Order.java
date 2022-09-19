package model;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String orderDate;
    private String orderCustomerId;
    private ArrayList<ItemDetails> items;

    public Order() {
    }

    public Order(String orderId, String orderDate, String orderCustomerId, ArrayList<ItemDetails> items) {
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setOrderCustomerId(orderCustomerId);
        this.setItems(items);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderCustomerId() {
        return orderCustomerId;
    }

    public void setOrderCustomerId(String orderCustomerId) {
        this.orderCustomerId = orderCustomerId;
    }

    public ArrayList<ItemDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetails> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderCustomerId='" + orderCustomerId + '\'' +
                ", items=" + items +
                '}';
    }
}

package model;

public class ItemDetails {
    private String itemCode;
    private String orderId;
    private int qty;
    private int discount;

    public ItemDetails() {
    }

    public ItemDetails(String itemCode, String orderId, int qty, int discount) {
        this.setItemCode(itemCode);
        this.setOrderId(orderId);
        this.setQty(qty);
        this.setDiscount(discount);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemCode='" + itemCode + '\'' +
                ", orderId='" + orderId + '\'' +
                ", qty=" + qty +
                ", discount=" + discount +
                '}';
    }
}

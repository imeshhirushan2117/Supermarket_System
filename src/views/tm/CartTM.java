package views.tm;

public class CartTM {
    private String code;
    private String description;
    private int qty;
    private double unitPrice;
    private int discount;
    private double total;

    public CartTM() {
    }

    public CartTM(String code, String description, int qty, double unitPrice, int discount, double total) {
        this.setCode(code);
        this.setDescription(description);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
        this.setDiscount(discount);
        this.setTotal(total);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}

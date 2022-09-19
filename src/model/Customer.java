package model;

public class Customer {
    private String customerId;
    private String customerTitle;
    private String customerName;
    private String customerAddress;
    private String customerCity;
    private String province;
    private String postalCode;

    public Customer() {
    }

    public Customer(String customerId, String customerTitle, String customerName, String customerAddress, String customerCity, String province, String postalCode) {
        this.setCustomerId(customerId);
        this.setCustomerTitle(customerTitle);
        this.setCustomerName(customerName);
        this.setCustomerAddress(customerAddress);
        this.setCustomerCity(customerCity);
        this.setProvince(province);
        this.setPostalCode(postalCode);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerTitle='" + customerTitle + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}

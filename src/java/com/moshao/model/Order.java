package com.moshao.model;

import java.sql.Timestamp;

public class Order extends Product {

    private int orderId;
    private int userid;
    private int productid;
    private int quantity;
    private Timestamp orderDate;
    private String customerEmail;

    public Order() {
    }

    public Order(int orderId, int userid, int productid, int quantity, Timestamp orderDate) {
        super();
        this.orderId = orderId;
        this.userid = userid;
        this.productid = productid;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public Order(int userid, int productid, int quantity, Timestamp orderDate) {
        super();
        this.userid = userid;
        this.quantity = quantity;
        this.productid = productid;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
}

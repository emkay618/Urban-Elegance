package com.moshao.model;

public class Cart extends Product {

    private int quantity;

    public Cart() {
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public int getProductId()
    {
        return super.getProductId();
    }
}

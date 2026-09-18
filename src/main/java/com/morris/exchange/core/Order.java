package com.morris.exchange.core;

public class Order {

    private final long orderId;
    private final Side side;
    private final long price;
    private final long quantity;
    private long remainingQuantity;

    Order prev;
    Order next;
    PriceLevel level;

    public Order(long orderId, Side side, long price, long quantity) {
        this.orderId = orderId;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.remainingQuantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public Side getSide() {
        return side;
    }

    public long getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getRemainingQuantity() {
        return remainingQuantity;
    }

    public void fill(long qty){
        if(qty <= 0 || qty > remainingQuantity){
            throw new IllegalArgumentException("invalid fill qty:" + qty);

        }
        remainingQuantity -= qty;
    }
}

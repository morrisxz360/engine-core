package com.morris.exchange.core;

public class PriceLevel {

    private final long price;
    private long totalQuantity;

    private Order head;
    private Order tail;

    public PriceLevel(long price) {
        this.price = price;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public long getPrice() {
        return price;
    }

    public Order getHead() {
        return head;
    }

    public boolean isEmpty() {
        return head == null;
    }

   public void addOrder(Order order){
        if(order.getRemainingQuantity() <= 0){
            throw new IllegalArgumentException("order has nothing");
        }
   }
}

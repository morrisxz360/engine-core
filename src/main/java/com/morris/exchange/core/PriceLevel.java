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

    public void addOrder(Order order) {
        if (order.getRemainingQuantity() <= 0) {
            throw new IllegalArgumentException("order has nothing");
        }
        if (head == null) {
            head = order;
            tail = order;
        } else {
            tail.next = order;
            order.prev = tail;
            tail = order;
        }
        order.level = this;
        totalQuantity += order.getRemainingQuantity();
    }

    public void removeOrder(Order order) {
        if (order.level != this) {
            throw new IllegalArgumentException("order is not in this level");
        }
        if (order.prev != null) {
            order.prev.next = order.next;
        } else {
            head = order.next;
        }
        if (order.next != null) {
            order.next.prev = order.prev;
        } else {
            tail = order.prev;
        }
        order.prev = null;
        order.next = null;
        order.level = null;
        totalQuantity -= order.getRemainingQuantity();
    }

    public void reduce(long qty) {
        if (qty <= 0 || qty > totalQuantity) {
            throw new IllegalArgumentException("invalid reduce qty:" + qty);
        }
        totalQuantity -= qty;
    }
}

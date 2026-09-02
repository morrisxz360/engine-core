package com.morris.exchange.core;

public class PriceLevel {

    private final long price;
    private long totalQuantity;

    public PriceLevel(long price) {
        this.price = price;
    }

    public void add(long qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("qty must be positive:" + qty);
        }
        totalQuantity += qty;
    }

    public void reduce(long qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("qty must be positive" + qty);
        }
        if (qty > totalQuantity) {
            throw new IllegalArgumentException("qty exceeds total:" + qty + ">" + totalQuantity);
        }

        totalQuantity -= qty;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public long getPrice() {
        return price;
    }
}

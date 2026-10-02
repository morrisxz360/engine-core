package com.morris.exchange.core;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OrderBookSide {

    private final Side side;
    private final TreeMap<Long, PriceLevel> levels;
    private final Map<Long, Order> ordersById = new HashMap<>();

    public OrderBookSide(Side side) {
        this.side = side;
        if (side == Side.BUY) {
            this.levels = new TreeMap<>(Comparator.reverseOrder());
        } else {
            this.levels = new TreeMap<>();
        }
    }

    public void addOrder(Order order) {
        if (order.getSide() != side) {
            throw new IllegalArgumentException(("wrong side:" + order.getSide()));
        }
        if (ordersById.containsKey(order.getOrderId())) {
            throw new IllegalArgumentException("duplicate orderId:" + order.getOrderId());
        }
        PriceLevel level = levels.get(order.getPrice());
        if (level == null) {
            level = new PriceLevel(order.getPrice());
            levels.put(order.getPrice(), level);
        }
        level.addOrder(order);
        ordersById.put(order.getOrderId(), order);

    }

    public boolean cancelOrder(long orderId) {
        Order order = ordersById.remove(orderId);
        if (order == null) {
            return false;
        }
        PriceLevel level = order.level;
        level.removeOrder(order);
        if (level.isEmpty()) {
            levels.remove(level.getPrice());
        }
        return true;
    }

    public PriceLevel bestLevel() {
        Map.Entry<Long, PriceLevel> first = levels.firstEntry();
        return first == null ? null : first.getValue();
    }


}

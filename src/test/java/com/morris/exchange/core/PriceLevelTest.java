package com.morris.exchange.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriceLevelTest {

    @Test
    void addOrderIncreaseTotal() {
        PriceLevel level = new PriceLevel(6_500_050L);
        Order o1 = new Order(1L, Side.SELL, 6_500_050L, 100);
        level.addOrder(o1);
        assertEquals(100, level.getTotalQuantity());
        assertSame(o1, level.getHead());
    }

    @Test
    void ordersKeepArrivalOrder() {
        PriceLevel level = new PriceLevel(6_500_050L);
        Order o1 = new Order(1, Side.SELL, 6_500_050L, 100);
        Order o2 = new Order(2L, Side.SELL, 6_500_050L, 200);
        level.addOrder(o1);
        level.addOrder(o2);
        assertSame(o1, level.getHead());
        assertSame(o2, o1.next);
        assertSame(o1, o2.prev);
    }

    @Test
    void removeMiddleOrder() {

        PriceLevel level = new PriceLevel(6_500_050L);
        Order o1 = new Order(1, Side.BUY, 6_500_050L, 100);
        Order o2 = new Order(2, Side.BUY, 6_500_050L, 200);
        Order o3 = new Order(3, Side.BUY, 6_500_050L, 20);
        level.addOrder(o1);
        level.addOrder(o2);
        level.addOrder(o3);
        level.removeOrder(o2);
        assertSame(o3, o1.next);
        assertSame(o1, o3.prev);
        assertEquals(120, level.getTotalQuantity());
    }

    @Test
    void removeOnlyOrderLeavesLevelEmpty() {
        PriceLevel level = new PriceLevel(6_500_050L);
        Order o1 = new Order(1, Side.SELL, 6_500_050L, 120);
        level.addOrder(o1);
        level.removeOrder(o1);
        assertTrue(level.isEmpty());
        assertEquals(0,level.getTotalQuantity());
        
    }
}

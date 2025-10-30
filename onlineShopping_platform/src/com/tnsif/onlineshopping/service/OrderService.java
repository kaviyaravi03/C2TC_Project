package com.tnsif.onlineshopping.service;

import com.tnsif.onlineshopping.entity.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static List<Order> orders = new ArrayList<>();

    public void placeOrder(Customer customer, List<ProductQuantityPair> productList) {
        Order order = new Order(customer, productList);
        orders.add(order);
        customer.getOrders().add(order);
        customer.getShoppingCart().clearCart();
        System.out.println("Order placed successfully!");
    }

    public void updateOrderStatus(int orderId, String status) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(status);
                System.out.println("Order status updated!");
                return;
            }
        }
        System.out.println("Order not found!");
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}
//Test commit
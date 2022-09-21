package com.bootcoding.restaurant.dao.order;

import com.bootcoding.restaurant.common.OrderStatus;
import com.bootcoding.restaurant.dao.entity.Order;
import com.bootcoding.restaurant.dao.entity.OrderItem;

import java.util.List;

/**
 * @author bootcoding on 18/09/22
 * @project software_development_training
 */
public interface OrderDao {

    public void createTable();
    public OrderStatus createOrder(Order order, List<OrderItem> menuItems);
    public void updateOrderStatus(long orderId, OrderStatus orderStatus);
}

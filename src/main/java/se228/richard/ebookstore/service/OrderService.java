package se228.richard.ebookstore.service;

import se228.richard.ebookstore.entity.Statistics;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Message placeNewOrder(int cartid);
    List<Order> fetchOrderByUserid(int userid);
    List<Order> fetchAllOrder();
    List<Statistics> analyzeSingleUserPurchase(int userid, Date from, Date to);
    List<Statistics> analyzeUser(Date from, Date to);
}

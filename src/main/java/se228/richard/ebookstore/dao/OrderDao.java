package se228.richard.ebookstore.dao;

import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    void createNewOrder(int userid, String username, int bookid, String bookname, double bookprice, int booknumber, Date orderdate);
    List<Order> fetchOrderByUserid(int userid);
    List<Order> fetchAllOrder();
}

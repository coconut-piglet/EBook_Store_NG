package se228.richard.ebookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se228.richard.ebookstore.dao.OrderDao;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.Order;
import se228.richard.ebookstore.repository.OrderRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public void createNewOrder(int userid, String username, int bookid, String bookname, double bookprice, int booknumber, Date orderdate) {
        Order neworder = new Order(userid,username,bookid,bookname,bookprice,booknumber,orderdate);
        orderRepo.save(neworder);
    }

    @Override
    public List<Order> fetchOrderByUserid(int userid) {
        return orderRepo.findAllByUserid(userid);
    }

    @Override
    public List<Order> fetchAllOrder() {
        return orderRepo.findAll();
    }
}

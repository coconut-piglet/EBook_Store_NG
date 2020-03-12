package se228.richard.ebookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se228.richard.ebookstore.dao.BookDao;
import se228.richard.ebookstore.dao.OrderDao;
import se228.richard.ebookstore.dao.ShoppingCartDao;
import se228.richard.ebookstore.entity.*;
import se228.richard.ebookstore.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public Message placeNewOrder(int cartid) {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByCartid(cartid);
        User user = shoppingCart.getUser();
        Book book = shoppingCart.getBook();
        int bookstock = book.getBookstock();
        int booksales = book.getBooksales();
        int cartnumber = shoppingCart.getCartnumber();
        bookstock = bookstock - cartnumber;
        booksales = booksales + cartnumber;
        if (bookstock < 0) {
            return new Message("FAIL", "");
        }
        book.setBookstock(bookstock);
        book.setBooksales(booksales);
        bookDao.modifyBook(book);
        orderDao.createNewOrder(user.getUserid(),user.getUsername(),book.getBookid(),book.getBookname(),book.getBookprice(),shoppingCart.getCartnumber(),new Date());
        shoppingCartDao.removeShoppingCart(cartid);
        return new Message("SUCCESS","");
    }

    @Override
    public List<Order> fetchOrderByUserid(int userid) {
        return orderDao.fetchOrderByUserid(userid);
    }

    @Override
    public List<Order> fetchAllOrder() {
        return orderDao.fetchAllOrder();
    }

    @Override
    public List<Statistics> analyzeSingleUserPurchase(int userid, Date from, Date to) {
        List<Order> notmyorder;
        if (userid == 0) {
            notmyorder = orderDao.fetchAllOrder();
        }
        else {
            notmyorder = orderDao.fetchOrderByUserid(userid);
        }
        List<Order> myorder = new ArrayList<>();
        for (int i = 0;i < notmyorder.size(); i++) {
            Date hisDate = notmyorder.get(i).getOrderdate();
            int flag1 = hisDate.compareTo(from);
            int flag2 = hisDate.compareTo(to);
            if (flag1 >= 0 && flag2 <= 0) {
                myorder.add(notmyorder.get(i));
            }
        }
        List<Statistics> result = new ArrayList<>();
        if (myorder.size() > 0) {
            Statistics bookStatistics = new Statistics(
                    myorder.get(0).getBookid(),
                    myorder.get(0).getBookname(),
                    myorder.get(0).getBooknumber(),
                    myorder.get(0).getBooknumber() * myorder.get(0).getBookprice()
            );
            result.add(bookStatistics);
        }
        for (int i = 1; i < myorder.size(); i++) {
            int dbookid = myorder.get(i).getBookid();
            String dbookname = myorder.get(i).getBookname();
            int dbooknumber = myorder.get(i).getBooknumber();
            double bookprice = myorder.get(i).getBookprice();
            double dprice = dbooknumber * bookprice;
            for (int j = 0;j < result.size(); j++) {
                if (result.get(j).getId() == dbookid) {
                    int oldnumber = result.get(j).getNumber();
                    double oldsale = result.get(j).getTotal();
                    oldnumber += dbooknumber;
                    oldsale += dprice;
                    result.get(j).setNumber(oldnumber);
                    result.get(j).setTotal(oldsale);
                    break;
                }
                if (j == result.size() - 1) {
                    Statistics newBS = new Statistics(dbookid, dbookname, dbooknumber, dprice);
                    result.add(newBS);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public List<Statistics> analyzeUser(Date from, Date to) {
        List<Order> notmyorder = orderDao.fetchAllOrder();
        List<Order> myorder = new ArrayList<>();
        for (int i = 0;i < notmyorder.size(); i++) {
            Date hisDate = notmyorder.get(i).getOrderdate();
            int flag1 = hisDate.compareTo(from);
            int flag2 = hisDate.compareTo(to);
            if (flag1 >= 0 && flag2 <= 0) {
                myorder.add(notmyorder.get(i));
            }
        }
        List<Statistics> result = new ArrayList<>();
        if (myorder.size() > 0) {
            Statistics bookStatistics = new Statistics(
                    myorder.get(0).getUserid(),
                    myorder.get(0).getUsername(),
                    1,
                    myorder.get(0).getBooknumber() * myorder.get(0).getBookprice()
            );
            result.add(bookStatistics);
        }
        for (int i = 1; i < myorder.size(); i++) {
            int duserid = myorder.get(i).getUserid();
            String dusername = myorder.get(i).getUsername();
            int dbooknumber = myorder.get(i).getBooknumber();
            double bookprice = myorder.get(i).getBookprice();
            double dprice = dbooknumber * bookprice;
            for (int j = 0;j < result.size(); j++) {
                if (result.get(j).getId() == duserid) {
                    int oldnumber = result.get(j).getNumber();
                    double oldsale = result.get(j).getTotal();
                    oldnumber += 1;
                    oldsale += dprice;
                    result.get(j).setNumber(oldnumber);
                    result.get(j).setTotal(oldsale);
                    break;
                }
                if (j == result.size() - 1) {
                    Statistics newBS = new Statistics(duserid, dusername, 1, dprice);
                    result.add(newBS);
                    break;
                }
            }
        }
        return result;
    }
}

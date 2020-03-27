package se228.richard.ebookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se228.richard.ebookstore.dao.BookDao;
import se228.richard.ebookstore.dao.OrderDao;
import se228.richard.ebookstore.dao.ShoppingCartDao;
import se228.richard.ebookstore.dao.UserDao;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.ShoppingCart;
import se228.richard.ebookstore.entity.User;
import se228.richard.ebookstore.service.OrderConsumerService;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderConsumerServiceImpl implements OrderConsumerService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    public boolean purchase(int cartid, String time) {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByCartid(cartid);
        if (shoppingCart == null) {
            return false;
        }
        else {
            Book book = shoppingCart.getBook();
            if (book == null) return false;
            if (book.getBookstock() == 0) return false;
            User user = shoppingCart.getUser();
            int cartnumber = shoppingCart.getCartnumber();
            int bookstock = book.getBookstock();
            if (cartnumber < bookstock) return false;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition parsePosition = new ParsePosition(0);
            Date date = simpleDateFormat.parse(time, parsePosition);
            orderDao.createNewOrder(user.getUserid(),user.getUsername(),book.getBookid(),book.getBookname(),book.getBookprice(),shoppingCart.getCartnumber(), date);
            List<ShoppingCart> shoppingCartList = user.getShoppingcarts();
            shoppingCartList.remove(shoppingCart);
            user.setShoppingcarts(shoppingCartList);
            userDao.modifyUser(user);
            List<ShoppingCart> shoppingCartListAlt = book.getShoppingcarts();
            shoppingCartListAlt.remove(shoppingCart);
            book.setShoppingcarts(shoppingCartListAlt);
            book.setBookstock(bookstock - cartnumber);
            book.setBooksales(book.getBooksales() + cartnumber);
            bookDao.modifyBook(book);
            shoppingCartDao.removeShoppingCart(cartid);
            return true;
        }
    }

}

package se228.richard.ebookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se228.richard.ebookstore.dao.BookDao;
import se228.richard.ebookstore.dao.ShoppingCartDao;
import se228.richard.ebookstore.dao.UserDao;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.ShoppingCart;
import se228.richard.ebookstore.entity.User;
import se228.richard.ebookstore.service.ShoppingCartService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public Message createNewCart(int userid, int bookid) {
        User user = userDao.findUserByUserid(userid);
        Book book = bookDao.findBookByBookid(bookid);
        if (user != null && book != null) {
            ShoppingCart phantom = shoppingCartDao.getShoppingCartByUserAndBook(user, book);
            if (phantom == null) {
                ShoppingCart newShoppingCart = new ShoppingCart(user, book, 1);
                shoppingCartDao.updateShoppingCart(newShoppingCart);
                return new Message("SUCCESS","");
            }
            else {
                return new Message("FAIL","duplicated");
            }
        }
        else {
            return new Message("FAIL","");
        }
    }

    @Override
    public Message modifyShoppingCart(int cartid, int cartnumber) {
        if (cartnumber == 0) {
            return new Message("FAIL","0 is not allowed");
        }
        ShoppingCart oldShoppingCart = shoppingCartDao.getShoppingCartByCartid(cartid);
        if (oldShoppingCart != null) {
            oldShoppingCart.setCartnumber(cartnumber);
            shoppingCartDao.updateShoppingCart(oldShoppingCart);
            return new Message("SUCCESS","");
        }
        else {
            return new Message("FAIL","");
        }
    }

    @Override
    public Message removeShoppingCart(int cartid) {
        try {
            shoppingCartDao.removeShoppingCart(cartid);
        } catch (Exception excp) {
            return new Message("ERROR",excp.getMessage());
        }
        return new Message("SUCCESS","");
    }

    @Override
    public List<ShoppingCart> fetchShoppingCart(int userid) {
        List<ShoppingCart> retlist = new ArrayList<>();
        List<ShoppingCart> rawList = userDao.findUserByUserid(userid).getShoppingcarts();
        for (ShoppingCart shoppingCart : rawList) {
            Book rawbook = shoppingCart.getBook();
            Book retbook = new Book(rawbook.getBookisbn(),rawbook.getBookname(),rawbook.getBookauthor(),rawbook.getBookprice(),rawbook.getBookstock(),0,0);
            ShoppingCart retshoppingcart = new ShoppingCart(shoppingCart.getCartid(),null,retbook,shoppingCart.getCartnumber());
            retlist.add(retshoppingcart);
        }
        return retlist;
    }

}

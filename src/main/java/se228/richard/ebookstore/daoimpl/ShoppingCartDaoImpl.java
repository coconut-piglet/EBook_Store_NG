package se228.richard.ebookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se228.richard.ebookstore.dao.ShoppingCartDao;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.ShoppingCart;
import se228.richard.ebookstore.entity.User;
import se228.richard.ebookstore.repository.ShoppingCartRepo;

import java.util.List;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Override
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public void removeShoppingCart(int cartid) {
        shoppingCartRepo.deleteById(cartid);
    }

    @Override
    public ShoppingCart getShoppingCartByUserAndBook(User user, Book book) {
        return shoppingCartRepo.findShoppingCartByUserAndBook(user, book);
    }

    @Override
    public ShoppingCart getShoppingCartByCartid(int cartid) {
        return shoppingCartRepo.findShoppingCartByCartid(cartid);
    }

}

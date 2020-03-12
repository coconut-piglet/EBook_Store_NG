package se228.richard.ebookstore.dao;

import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.ShoppingCart;
import se228.richard.ebookstore.entity.User;

import java.util.List;

public interface ShoppingCartDao {
    void updateShoppingCart(ShoppingCart shoppingCart);
    void removeShoppingCart(int cartid);
    ShoppingCart getShoppingCartByUserAndBook(User user, Book book);
    ShoppingCart getShoppingCartByCartid(int cartid);
}

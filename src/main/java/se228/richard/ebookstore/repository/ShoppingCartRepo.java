package se228.richard.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.ShoppingCart;
import se228.richard.ebookstore.entity.User;

import java.util.List;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Integer> {
    List<ShoppingCart> findShoppingCartsByUser(User user);
    ShoppingCart findShoppingCartByCartid(int cartid);
    ShoppingCart findShoppingCartByUserAndBook(User user, Book book);
}

package se228.richard.ebookstore.service;

import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    Message createNewCart(int userid, int bookid);
    Message modifyShoppingCart(int cartid, int cartnumber);
    Message removeShoppingCart(int cartid);
    List<ShoppingCart> fetchShoppingCart(int userid);
}

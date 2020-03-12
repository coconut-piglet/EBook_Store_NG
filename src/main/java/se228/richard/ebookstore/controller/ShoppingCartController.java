package se228.richard.ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.ShoppingCart;
import se228.richard.ebookstore.service.ShoppingCartService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping(value = "/addbook",method = RequestMethod.GET)
    public Message addBookToCart(@RequestParam(value = "userid",required = true) String Suserid, @RequestParam(value = "bookid",required = true) String Sbookid){
        int userid = Integer.parseInt(Suserid);
        int bookid = Integer.parseInt(Sbookid);
        return shoppingCartService.createNewCart(userid, bookid);
    }

    @RequestMapping(value = "/modifycart",method = RequestMethod.GET)
    public Message modifyCart(@RequestParam(value = "cartid",required = true) String Scartid, @RequestParam(value = "cartnumber",required = true) String Scartnumber) {
        int cartid = Integer.parseInt(Scartid);
        int cartnumber = Integer.parseInt(Scartnumber);
        return shoppingCartService.modifyShoppingCart(cartid, cartnumber);
    }

    @RequestMapping(value = "/deletecart",method = RequestMethod.GET)
    public Message deleteCart(@RequestParam(value = "cartid",required = true) String Scartid) {
        int cartid = Integer.parseInt(Scartid);
        return shoppingCartService.removeShoppingCart(cartid);
    }

    @RequestMapping(value = "/fetchcart",method = RequestMethod.GET)
    public List<ShoppingCart> fetchCart(@RequestParam(value = "userid",required = true) String Suserid) {
        int userid = Integer.parseInt(Suserid);
        return shoppingCartService.fetchShoppingCart(userid);
    }
}

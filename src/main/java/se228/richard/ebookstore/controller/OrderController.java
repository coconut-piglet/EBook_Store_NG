package se228.richard.ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se228.richard.ebookstore.entity.Statistics;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.Order;
import se228.richard.ebookstore.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/checkout",method = RequestMethod.GET)
    public Message checkout(@RequestParam(value = "cartid",required = true) String Scartid) {
        int cartid = Integer.parseInt(Scartid);
        return orderService.placeNewOrder(cartid);
    }

    @RequestMapping(value = "/fetchorders",method = RequestMethod.GET)
    public List<Order> fetchOrders(@RequestParam(value = "userid",required = true) String Suserid) {
        int userid = Integer.parseInt(Suserid);
        return orderService.fetchOrderByUserid(userid);
    }

    @RequestMapping(value = "/fetchallorder", method = RequestMethod.GET)
    public List<Order> fetchAllOrders() {
        return orderService.fetchAllOrder();
    }

    @RequestMapping(value = "/analyzemypurchase", method = RequestMethod.GET)
    public List<Statistics> countPurchase(@RequestParam("userid") int userid, @RequestParam("from") String from, @RequestParam("to") String to) {
        System.out.println(from);
        System.out.println(to);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dfrom = sdf.parse(from);
            Date dto = sdf.parse(to);
            System.out.println(dfrom);
            System.out.println(dto);
            return orderService.analyzeSingleUserPurchase(userid, dfrom, dto);
        }catch (Exception excp) {
            return null;
        }
    }

    @RequestMapping(value = "/analyzeuser", method = RequestMethod.GET)
    public List<Statistics> countUserPurchase(@RequestParam("from") String from, @RequestParam("to") String to) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dfrom = sdf.parse(from);
            Date dto = sdf.parse(to);
            return orderService.analyzeUser(dfrom,dto);
        }catch (Exception excp) {
            return null;
        }
    }

}

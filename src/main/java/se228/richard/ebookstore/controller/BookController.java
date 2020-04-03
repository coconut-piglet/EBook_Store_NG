package se228.richard.ebookstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.service.BookService;
import se228.richard.ebookstore.service.WishListService;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Scope("singleton")
@RequestMapping("/api")
public class BookController {

    //Legacy Code
    //@Autowired
    //private BookService bookService;

    private final static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping(value = "/insertwishlist",method = RequestMethod.GET)
    public Message insertWishList() throws RemoteException {
        WishListService wishListService = applicationContext.getBean(WishListService.class);
        wishListService.addBooksToWishList("BOOK_A", "BOOK_B", "BOOK_C");
        Assert.isTrue(wishListService.getWishList().size() == 3, "First adding should work just fine");
        logger.info("BOOK_A, BOOK_B, BOOK_C have been added to wishlist");
        try {
            wishListService.addBooksToWishList("BOOK_D", "BOOK_WITH_A_LONG_NAME");
        } catch (RuntimeException e) {
            logger.info("v--- The following exception is expect because 'BOOK_WITH_A_LONG_NAME' is too " +
                    "big for the DB ---v");
            logger.error(e.getMessage());
        }

        for (String book : wishListService.getWishList()) {
            logger.info("So far, " + book + " have been added to wishlist");
        }
        logger.info("You shouldn't see BOOK_D or BOOK_WITH_A_LONG_NAME. BOOK_WITH_A_LONG_NAME violated DB constraints, " +
                "and BOOK_D was rolled back in the same TX");
        Assert.isTrue(wishListService.getWishList().size() == 3,
                "'BOOK_WITH_A_LONG_NAMEl' should have triggered a rollback");

        try {
            wishListService.addBooksToWishList("BOOK_E", null);
        } catch (RuntimeException e) {
            logger.info("v--- The following exception is expect because null is not " +
                    "valid for the DB ---v");
            logger.error(e.getMessage());
        }

        for (String book : wishListService.getWishList()) {
            logger.info("So far, " + book + " have been added to wishlist");
        }
        logger.info("You shouldn't see BOOK_E or null. null violated DB constraints, and " +
                "BOOK_E was rolled back in the same TX");
        Assert.isTrue(wishListService.getWishList().size() == 3,
                "'null' should have triggered a rollback");
        return new Message("SUCCESS", "Wishlist updated");
    }

    @RequestMapping(value = "/fetchlibrary",method = RequestMethod.GET)
    public List<Book> fetchLibrary() throws RemoteException {
        BookService bookService = applicationContext.getBean(BookService.class);
        // Print the service created and myself
        System.out.println("本次使用的service：" + bookService);
        System.out.println("本次使用的controller：" + this);
        return bookService.fetchLibrary();
    }

    @RequestMapping(value = "/fetchdetail",method = RequestMethod.GET)
    public BookDetail fetchBookDetail(@RequestParam("bookid") int bookid) throws RemoteException {
        BookService bookService = applicationContext.getBean(BookService.class);
        // Print the service created and myself
        System.out.println("本次使用的service：" + bookService);
        System.out.println("本次使用的controller：" + this);
        return bookService.fetchBookDetail(bookid);
    }

    @RequestMapping(value = "/fetchcover", method = RequestMethod.GET)
    public void fetchBookCover(@RequestParam("bookid") int bookid, final HttpServletResponse response) throws Exception {
        BookService bookService = applicationContext.getBean(BookService.class);
        // Print the service created and myself
        System.out.println("本次使用的service：" + bookService);
        System.out.println("本次使用的controller：" + this);
        byte[] ret = bookService.fetchBookCover(bookid);
        if (ret != null) {
            response.setContentType("image/png");
            response.setCharacterEncoding("UTF-8");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(ret);
            outputStream.flush();
        }
    }

    @RequestMapping(value = "/fetchdesc", method = RequestMethod.GET)
    public Message fetchBookDesc(@RequestParam("bookid") int bookid) throws RemoteException {
        BookService bookService = applicationContext.getBean(BookService.class);
        // Print the service created and myself
        System.out.println("本次使用的service：" + bookService);
        System.out.println("本次使用的controller：" + this);
        return bookService.fetchBookDesc(bookid);
    }

    @RequestMapping(value = "/addview", method = RequestMethod.GET)
    public Message addBookView(@RequestParam("bookid") int bookid) throws RemoteException {
        BookService bookService = applicationContext.getBean(BookService.class);
        // Print the service created and myself
        System.out.println("本次使用的service：" + bookService);
        System.out.println("本次使用的controller：" + this);
        return bookService.addBookView(bookid);
    }
}

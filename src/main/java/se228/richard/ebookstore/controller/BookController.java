package se228.richard.ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.service.BookService;

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

    @Autowired
    WebApplicationContext applicationContext;

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

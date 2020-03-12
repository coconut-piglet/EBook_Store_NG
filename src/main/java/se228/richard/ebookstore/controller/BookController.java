package se228.richard.ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.service.BookService;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/fetchlibrary",method = RequestMethod.GET)
    public List<Book> fetchLibrary() {
        return bookService.fetchLibrary();
    }

    @RequestMapping(value = "/fetchdetail",method = RequestMethod.GET)
    public BookDetail fetchBookDetail(@RequestParam("bookid") int bookid) {
        return bookService.fetchBookDetail(bookid);
    }

    @RequestMapping(value = "/fetchcover", method = RequestMethod.GET)
    public void fetchBookCover(@RequestParam("bookid") int bookid, final HttpServletResponse response) throws Exception {
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
    public Message fetchBookDesc(@RequestParam("bookid") int bookid) {
        return bookService.fetchBookDesc(bookid);
    }

    @RequestMapping(value = "/addview", method = RequestMethod.GET)
    public Message addBookView(@RequestParam("bookid") int bookid) {
        return bookService.addBookView(bookid);
    }
}

package se228.richard.ebookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import se228.richard.ebookstore.dao.BookDao;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> fetchLibrary() {
        List<Book> rawlist = bookDao.fetchLibrary();
        List<Book> retlist = new ArrayList<>();
        for (Book book : rawlist) {
            Book simplebook = new Book(book.getBookid(),book.getBookisbn(),book.getBookname(),book.getBookauthor(),book.getBookprice(),book.getBookstock(),book.getBooksales(),book.getBookviews());
            retlist.add(simplebook);
        }
        return retlist;
    }

    @Override
    public BookDetail fetchBookDetail(int bookid) {
        return bookDao.fetchBookDetailByBookid(bookid);
    }

    @Override
    public byte[] fetchBookCover(int bookid) {
        try {
            byte[] bookcover = bookDao.fetchBookDetailByBookid(bookid).bookcover;
            return bookcover;
        } catch (Exception excp) {
            return null;
        }
    }

    @Override
    public Message fetchBookDesc(int bookid) {
        try {
            String bookdesc = bookDao.fetchBookDetailByBookid(bookid).bookdescription;
            return new Message("SUCCESS", bookdesc);
        } catch (Exception excp) {
            return new Message("ERROR", excp.getMessage());
        }
    }

    @Override
    public Message addBookView(int bookid) {
        Book original = bookDao.findBookByBookid(bookid);
        if (original == null) {
            return new Message("FAIL", "");
        }
        int newviews = original.getBookviews();
        newviews += 1;
        original.setBookviews(newviews);
        try {
            bookDao.modifyBook(original);
            return new Message("SUCCESS", "");
        } catch (Exception excp) {
            return new Message("ERROR", excp.getMessage());
        }
    }

}

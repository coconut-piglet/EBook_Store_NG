package se228.richard.ebookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import se228.richard.ebookstore.dao.BookDao;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.service.BookService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public class BookServiceImpl extends UnicastRemoteObject implements BookService {

    private Map<String, Book> fakeLibrary;

    public BookServiceImpl() throws RemoteException {
        fakeLibrary = new HashMap<String, Book>();
        Book book = new Book(1,"9789875669116","Steve Jobs","Walter Isaacson",90.86,100,0,0);
        fakeLibrary.put("Steve Jobs", book);
    }

    @Autowired
    private BookDao bookDao;

    @Override
    public Book fetchBookData(String bookname) throws RemoteException {
        Book book = fakeLibrary.get(bookname);
        return book;
    }

    @Override
    public List<Book> fetchLibrary() throws RemoteException {
        List<Book> rawlist = bookDao.fetchLibrary();
        List<Book> retlist = new ArrayList<>();
        for (Book book : rawlist) {
            Book simplebook = new Book(book.getBookid(),book.getBookisbn(),book.getBookname(),book.getBookauthor(),book.getBookprice(),book.getBookstock(),book.getBooksales(),book.getBookviews());
            retlist.add(simplebook);
        }
        return retlist;
    }

    @Override
    public BookDetail fetchBookDetail(int bookid) throws RemoteException {
        return bookDao.fetchBookDetailByBookid(bookid);
    }

    @Override
    public byte[] fetchBookCover(int bookid) throws RemoteException {
        try {
            byte[] bookcover = bookDao.fetchBookDetailByBookid(bookid).bookcover;
            return bookcover;
        } catch (Exception excp) {
            return null;
        }
    }

    @Override
    public Message fetchBookDesc(int bookid) throws RemoteException {
        try {
            String bookdesc = bookDao.fetchBookDetailByBookid(bookid).bookdescription;
            return new Message("SUCCESS", bookdesc);
        } catch (Exception excp) {
            return new Message("ERROR", excp.getMessage());
        }
    }

    @Override
    public Message addBookView(int bookid) throws RemoteException {
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

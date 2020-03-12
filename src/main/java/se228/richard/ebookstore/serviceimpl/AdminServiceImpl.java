package se228.richard.ebookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se228.richard.ebookstore.dao.BookDao;
import se228.richard.ebookstore.dao.UserDao;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.User;
import se228.richard.ebookstore.service.AdminService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public Message modifyBookDetail(BookDetail newBookDetail) {
        try {
            bookDao.modifyBookDetail(newBookDetail);
        } catch (Exception excp) {
            return new Message("ERROR", excp.getMessage());
        }
        return new Message("SUCCESS", "");
    }

    @Override
    public void deleteBookDetail(int bookid) {
        bookDao.deleteBookDetail(bookid);
    }

    @Override
    public Message deleteBook(int bookid) {
        try {
            bookDao.deleteBook(bookid);
        } catch (Exception excp) {
            return new Message("ERROR", excp.getMessage());
        }
        return new Message("SUCCESS", "");
    }

    @Override
    public Message addBook(String bookisbn, String bookname, String bookauthor, double bookprice, int bookstock) {
        List<Book> phantom = bookDao.findBookByBookisbn(bookisbn);
        if (phantom.size() == 0) {
            Book newbook = new Book(bookisbn, bookname, bookauthor, bookprice, bookstock, 0, 0);
            try {
                bookDao.modifyBook(newbook);
            } catch (Exception excp) {
                return new Message("ERROR", excp.getMessage());
            }
            List<Book> retbook = bookDao.findBookByBookisbn(bookisbn);
            String retid = null;
            for (Book book : retbook) {
                retid = String.valueOf(book.getBookid());
            }
            return new Message("SUCCESS", retid);
        }
        else {
            return new Message("FAIL", "isbn already exists");
        }
    }

    @Override
    public Message modifyBook(int bookid, String bookisbn, String bookname, String bookauthor, double bookprice, int bookstock) {
        Book original = bookDao.findBookByBookid(bookid);
        if (original == null) {
            return new Message("FAIL", "book does not exists");
        }
        List<Book> phantom = bookDao.findBookByBookisbn(bookisbn);
        if (phantom.size() == 1) {
            for (Book book : phantom) {
                if (book.getBookisbn() != original.getBookisbn()) {
                    return new Message("FAIL", "isbn already exists");
                }
            }
        }
        original.setBookisbn(bookisbn);
        original.setBookname(bookname);
        original.setBookauthor(bookauthor);
        original.setBookprice(bookprice);
        original.setBookstock(bookstock);
        try {
            bookDao.modifyBook(original);
        } catch (Exception excp) {
            return new Message("ERROR", "");
        }
        return new Message("SUCCESS", "");
    }

    @Override
    public List<User> fetchUserList() {
        List<User> rawlist = userDao.fetchUserList();
        List<User> retlist = new ArrayList<>();
        for (User user : rawlist) {
            User simpleuser = new User(user.getUserid(),user.getUsername(), user.getUseremail(), "", user.getUserstatus());
            retlist.add(simpleuser);
        }
        return retlist;
    }

    @Override
    public Message activateUser(int userid) {
        User oldUserStatus = userDao.findUserByUserid(userid);
        if (oldUserStatus == null) {
            return new Message("ERROR", "user does not exist");
        }
        oldUserStatus.setUserstatus(1);
        userDao.modifyUser(oldUserStatus);
        return new Message("SUCCESS", "");
    }

    @Override
    public Message disableUser(int userid) {
        User oldUserStatus = userDao.findUserByUserid(userid);
        if (oldUserStatus == null) {
            return new Message("ERROR", "user does not exist");
        }
        oldUserStatus.setUserstatus(0);
        userDao.modifyUser(oldUserStatus);
        return new Message("SUCCESS", "");
    }

}

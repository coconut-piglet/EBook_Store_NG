package se228.richard.ebookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se228.richard.ebookstore.dao.BookDao;
import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.repository.BookDetailRepo;
import se228.richard.ebookstore.repository.BookRepo;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookDetailRepo bookDetailRepo;

    @Override
    public List<Book> fetchLibrary() {
        return bookRepo.findAll();
    }

    @Override
    public Book findBookByBookid(int bookid) {
        return bookRepo.findBookByBookid(bookid);
    }

    @Override
    public List<Book> findBookByBookisbn(String bookisbn) {
        return bookRepo.findAllByBookisbn(bookisbn);
    }

    @Override
    public BookDetail fetchBookDetailByBookid(int bookid) {
        List<BookDetail> found = bookDetailRepo.findAllByBookid(bookid);
        if (found.size() > 0) {
            return found.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public void modifyBookDetail(BookDetail newBookDetail) {
        bookDetailRepo.save(newBookDetail);
    }

    @Override
    public void modifyBook(Book newbook) {
        bookRepo.save(newbook);
    }

    @Override
    public void deleteBook(int bookid) {
        List<BookDetail> todrop = bookDetailRepo.findAllByBookid(bookid);
        if (todrop .size() > 0) {
            bookDetailRepo.deleteAllByBookid(bookid);
        }
        bookRepo.deleteById(bookid);
    }

    @Override
    public void deleteBookDetail(int bookid) {
        List<BookDetail> todrop = bookDetailRepo.findAllByBookid(bookid);
        if (todrop.size() > 0) {
            bookDetailRepo.deleteAllByBookid(bookid);
        }
    }
}

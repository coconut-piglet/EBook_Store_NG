package se228.richard.ebookstore.dao;

import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;

import java.util.List;

public interface BookDao {
    List<Book> fetchLibrary();
    Book findBookByBookid(int bookid);
    List<Book> findBookByBookisbn(String bookisbn);
    BookDetail fetchBookDetailByBookid(int bookid);
    void modifyBookDetail(BookDetail newBookDetail);
    void modifyBook(Book newbook);
    void deleteBook(int bookid);
    void deleteBookDetail(int bookid);
}

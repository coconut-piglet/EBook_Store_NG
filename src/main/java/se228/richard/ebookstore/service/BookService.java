package se228.richard.ebookstore.service;

import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.entity.Message;

import java.util.List;

public interface BookService {
    List<Book> fetchLibrary();
    BookDetail fetchBookDetail(int bookid);
    byte[] fetchBookCover(int bookid);
    Message fetchBookDesc(int bookid);
    Message addBookView(int bookid);
}

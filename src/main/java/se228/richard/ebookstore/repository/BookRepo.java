package se228.richard.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se228.richard.ebookstore.entity.Book;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findAll();
    Book findBookByBookid(int bookid);
    List<Book> findAllByBookisbn(String bookisbn);
}

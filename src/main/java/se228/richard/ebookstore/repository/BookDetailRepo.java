package se228.richard.ebookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se228.richard.ebookstore.entity.BookDetail;

import java.util.List;

public interface BookDetailRepo extends MongoRepository<BookDetail, String> {
    List<BookDetail> findAllByBookid(int bookid);
    List<BookDetail> deleteAllByBookid(int bookid);
}

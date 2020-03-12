package se228.richard.ebookstore.service;

import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.entity.Message;
import se228.richard.ebookstore.entity.User;

import java.util.List;

public interface AdminService {
    List<User> fetchUserList();
    Message activateUser(int userid);
    Message disableUser(int userid);
    Message modifyBookDetail(BookDetail newBookDetail);
    void deleteBookDetail(int bookid);
    Message deleteBook(int bookid);
    Message addBook(String bookisbn, String bookname, String bookauthor, double bookprice, int bookstock);
    Message modifyBook(int bookid,String bookisbn, String bookname, String bookauthor, double bookprice, int bookstock);
}

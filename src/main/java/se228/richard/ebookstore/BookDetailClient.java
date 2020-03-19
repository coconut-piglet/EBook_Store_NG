package se228.richard.ebookstore;

import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.service.BookService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class BookDetailClient {
    
    public static void main(String[] args) throws RemoteException, NamingException {
        Context namingContext = new InitialContext();
        
        String url = "rmi://localhost/book_detail_server";
        BookService bookService = (BookService) namingContext.lookup(url);
        
        String bookname = "Steve Jobs";
        Book book = bookService.fetchBookData(bookname);
        System.out.println("Got the following book detail:");
        System.out.println("NAME: " + book.getBookname());
        System.out.println("ISBN: " + book.getBookisbn());
        System.out.println("AUTHOR: " + book.getBookauthor());
        System.out.println("PRICE: " + book.getBookprice());
    }
    
}

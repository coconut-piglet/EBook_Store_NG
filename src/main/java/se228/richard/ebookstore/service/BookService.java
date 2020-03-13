package se228.richard.ebookstore.service;

import se228.richard.ebookstore.entity.Book;
import se228.richard.ebookstore.entity.BookDetail;
import se228.richard.ebookstore.entity.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BookService extends Remote {
    List<Book> fetchLibrary() throws RemoteException;
    BookDetail fetchBookDetail(int bookid) throws RemoteException;
    byte[] fetchBookCover(int bookid) throws RemoteException;
    Message fetchBookDesc(int bookid) throws RemoteException;
    Message addBookView(int bookid) throws RemoteException;
    // New function for RMI purpose
    Book fetchBookData(String bookname) throws RemoteException;
}

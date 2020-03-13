package se228.richard.ebookstore;

import se228.richard.ebookstore.serviceimpl.BookServiceImpl;
import se228.richard.ebookstore.service.BookService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class BookDetailServer {

    public static void main(String[] args) throws RemoteException, NamingException {
        System.out.println("Constructing BookDetailServer implementation...");
        BookServiceImpl bookServiceImpl = new BookServiceImpl();

        System.out.println("Binding BookDetailServer implementation to registry...");
        Context namingContext = new InitialContext();
        namingContext.bind("rmi:bookDetailServer_6", bookServiceImpl);

        System.out.println("Waiting for invocations from clients...");
    }

}

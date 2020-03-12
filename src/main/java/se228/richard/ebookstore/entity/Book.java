package se228.richard.ebookstore.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "book", schema = "e-book-store", catalog = "")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bookid")
public class Book implements Serializable {
    private int bookid;
    private String bookisbn;
    private String bookname;
    private String bookauthor;
    private double bookprice;
    private int bookstock;
    private int booksales;
    private int bookviews;
    private List<ShoppingCart> shoppingcarts;
    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "bookid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    @Basic
    @Column(name = "bookisbn")
    public String getBookisbn() {
        return bookisbn;
    }

    public void setBookisbn(String bookisbn) {
        this.bookisbn = bookisbn;
    }

    @Basic
    @Column(name = "bookname")
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    @Basic
    @Column(name = "bookauthor")
    public String getBookauthor() {
        return bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        this.bookauthor = bookauthor;
    }

    @Basic
    @Column(name = "bookprice")
    public double getBookprice() {
        return bookprice;
    }

    public void setBookprice(double bookprice) {
        this.bookprice = bookprice;
    }

    @Basic
    @Column(name = "bookstock")
    public int getBookstock() {
        return bookstock;
    }

    public void setBookstock(int bookstock) {
        this.bookstock = bookstock;
    }

    @Basic
    @Column(name = "booksales")
    public int getBooksales() {
        return booksales;
    }

    public void setBooksales(int booksales) {
        this.booksales = booksales;
    }

    @Basic
    @Column(name = "bookviews")
    public int getBookviews() { return bookviews; }

    public void setBookviews(int bookviews) { this.bookviews = bookviews; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        if (bookid != that.bookid) return false;
        if (bookisbn != null ? !bookisbn.equals(that.bookisbn) : that.bookisbn != null) return false;
        if (bookname != null ? !bookname.equals(that.bookname) : that.bookname != null) return false;
        if (bookauthor != null ? !bookauthor.equals(that.bookauthor) : that.bookauthor != null) return false;
        if (bookprice != that.bookprice) return false;
        if (bookstock != that.bookstock) return false;
        if (booksales != that.booksales) return false;
        if (bookviews != that.bookviews) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = bookid;
        result = 31 * result + (bookisbn != null ? bookisbn.hashCode() : 0);
        result = 31 * result + (bookname != null ? bookname.hashCode() : 0);
        result = 31 * result + (bookauthor != null ? bookauthor.hashCode() : 0);
        result = 31 * result + Integer.parseInt(new java.text.DecimalFormat("0").format(bookprice));
        result = 31 * result + bookstock;
        result = 31 * result + booksales;
        result = 31 * result + bookviews;
        return result;
    }

    @OneToMany(targetEntity = ShoppingCart.class, mappedBy = "book", cascade = CascadeType.ALL)
    public List<ShoppingCart> getShoppingcarts() {
        return shoppingcarts;
    }

    public void setShoppingcarts(List<ShoppingCart> shoppingcarts) {
        this.shoppingcarts = shoppingcarts;
    }

    public Book() {
    }

    public Book(int bookid, String bookisbn, String bookname, String bookauthor, double bookprice, int bookstock, int booksales, int bookviews) {
        this.bookid = bookid;
        this.bookisbn = bookisbn;
        this.bookname = bookname;
        this.bookauthor = bookauthor;
        this.bookprice = bookprice;
        this.bookstock = bookstock;
        this.booksales = booksales;
        this.bookviews = bookviews;
    }

    public Book(String bookisbn, String bookname, String bookauthor, double bookprice, int bookstock, int booksales, int bookviews) {
        this.bookisbn = bookisbn;
        this.bookname = bookname;
        this.bookauthor = bookauthor;
        this.bookprice = bookprice;
        this.bookstock = bookstock;
        this.booksales = booksales;
        this.bookviews = bookviews;
    }
}

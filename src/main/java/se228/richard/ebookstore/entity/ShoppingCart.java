package se228.richard.ebookstore.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shoppingcart", schema = "e-book-store", catalog = "")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cartid")
public class ShoppingCart implements Serializable {
    private int cartid;
    private User user;
    private Book book;
    private int cartnumber;
    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "cartid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "bookid", referencedColumnName = "bookid")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Basic
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "cartnumber")
    public int getCartnumber() {
        return cartnumber;
    }

    public void setCartnumber(int cartnumber) {
        this.cartnumber = cartnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        if (cartid != that.cartid) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        if (cartnumber != that.cartnumber) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cartid;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + cartnumber;
        return result;
    }

    public ShoppingCart() {
    }

    public ShoppingCart(int cartid, User user, Book book, int cartnumber) {
        this.cartid = cartid;
        this.user = user;
        this.book = book;
        this.cartnumber = cartnumber;
    }

    public ShoppingCart(User user, Book book, int cartnumber) {
        this.user = user;
        this.book = book;
        this.cartnumber = cartnumber;
    }
}

package se228.richard.ebookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders", schema = "e-book-store", catalog = "")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderid")
public class Order implements Serializable {
    private int orderid;
    private int userid;
    private String username;
    private int bookid;
    private String bookname;
    private double bookprice;
    private int booknumber;
    private Date orderdate;

    @Id
    @Column(name = "orderid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "bookid")
    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
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
    @Column(name = "bookprice")
    public double getBookprice() {
        return bookprice;
    }

    public void setBookprice(double bookprice) {
        this.bookprice = bookprice;
    }

    @Basic
    @Column(name = "booknumber")
    public int getBooknumber() {
        return booknumber;
    }

    public void setBooknumber(int booknumber) {
        this.booknumber = booknumber;
    }

    @Basic
    @Column(name = "orderdate")
    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        if (orderid != that.orderid) return false;
        if (userid != that.userid) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (bookid != that.bookid) return false;
        if (bookname != null ? !bookname.equals(that.bookname) : that.bookname != null) return false;
        if (bookprice != that.bookprice) return false;
        if (booknumber != that.booknumber) return false;
        if (orderdate.compareTo(that.orderdate) != 0) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = orderid;
        result = 31 * result + userid;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + bookid;
        result = 31 * result + (bookname != null ? bookname.hashCode() : 0);
        result = 31 * result + Integer.parseInt(new java.text.DecimalFormat("0").format(bookprice));
        result = 31 * result + booknumber;
        result = 31 * result + (orderdate != null ? orderdate.hashCode() : 0);
        return result;
    }

    public Order() {
    }

    public Order(int userid, String username, int bookid, String bookname, double bookprice, int booknumber, Date orderdate) {
        this.userid = userid;
        this.username = username;
        this.bookid = bookid;
        this.bookname = bookname;
        this.bookprice = bookprice;
        this.booknumber = booknumber;
        this.orderdate = orderdate;
    }
}

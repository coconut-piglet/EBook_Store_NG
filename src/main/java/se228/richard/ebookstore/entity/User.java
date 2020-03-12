package se228.richard.ebookstore.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user",schema = "e-book-store", catalog = "")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userid")
public class User implements Serializable {
    private int userid;
    private String username;
    private String useremail;
    private String userpwd;
    private int userstatus;
    private List<ShoppingCart> shoppingcarts;
    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "useremail")
    public String getUseremail() {
        return useremail;
    }
    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    @Basic
    @Column(name = "userpwd")
    public String getUserpwd() {
        return userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    @Basic
    @Column(name = "userstatus")
    public int getUserstatus() {
        return userstatus;
    }
    public void setUserstatus(int userstatus) {
        this.userstatus = userstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        if (userid != that.userid) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (useremail != null ? !useremail.equals(that.useremail) : that.useremail != null) return false;
        if (userpwd != null ? !userpwd.equals(that.userpwd) : that.userpwd != null) return false;
        if (userstatus != that.userstatus) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (useremail != null ? useremail.hashCode() : 0);
        result = 31 * result + (userpwd != null ? userpwd.hashCode() : 0);
        result = 31 * result + userstatus;
        return result;
    }

    @OneToMany(targetEntity = ShoppingCart.class, mappedBy = "user", cascade = CascadeType.ALL)
    public List<ShoppingCart> getShoppingcarts() {
        return shoppingcarts;
    }

    public void setShoppingcarts(List<ShoppingCart> shoppingcarts) {
        this.shoppingcarts = shoppingcarts;
    }

    public User() {
    }

    public User(int userid, String username, String useremail, String userpwd, int userstatus) {
        this.userid = userid;
        this.username = username;
        this.useremail = useremail;
        this.userpwd = userpwd;
        this.userstatus = userstatus;
    }

    public User(String username, String useremail, String userpwd, int userstatus) {
        this.username = username;
        this.useremail = useremail;
        this.userpwd = userpwd;
        this.userstatus = userstatus;
    }
}

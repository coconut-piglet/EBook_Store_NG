package se228.richard.ebookstore.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin", schema = "e-book-store", catalog = "")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "adminid")

public class Admin implements Serializable {
    private int adminid;
    private String adminname;
    private String adminpwd;

    @Id
    @Column(name = "adminid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    @Basic
    @Column(name = "adminname")
    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    @Basic
    @Column(name = "adminpwd")
    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin that = (Admin) o;
        if (adminid != that.adminid) return false;
        if (adminname != null ? !adminname.equals(that.adminname) : that.adminname != null) return false;
        if (adminpwd != null ? !adminpwd.equals(that.adminpwd) : that.adminpwd != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = adminid;
        result = 31 * result + (adminname != null ? adminname.hashCode() : 0);
        result = 31 * result + (adminpwd != null ? adminpwd.hashCode() : 0);
        return result;
    }

    public Admin() {
    }

    public Admin(String adminname, String adminpwd) {
        this.adminname = adminname;
        this.adminpwd = adminpwd;
    }
}

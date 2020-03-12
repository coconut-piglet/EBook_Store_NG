package se228.richard.ebookstore.entity;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "bookdetail")
public class BookDetail {

    @Id
    public String id;

    public int bookid;

    public byte[] bookcover;

    public String bookdescription;

    public BookDetail() {
    }

    public BookDetail(int bookid, byte[] bookcover, String bookdescription) {
        this.bookid = bookid;
        this.bookcover = bookcover;
        this.bookdescription = bookdescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public byte[] getBookcover() {
        return bookcover;
    }

    public void setBookcover(byte[] bookcover) {
        this.bookcover = bookcover;
    }

    public String getBookdescription() {
        return bookdescription;
    }

    public void setBookdescription(String bookdescription) {
        this.bookdescription = bookdescription;
    }
}

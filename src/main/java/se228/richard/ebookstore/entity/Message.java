package se228.richard.ebookstore.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
    private String msgtype;
    private String msgcontent;

    @Id
    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    @Basic
    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public Message() {
    }

    public Message(String msgtype, String msgcontent) {
        this.msgtype = msgtype;
        this.msgcontent = msgcontent;
    }
}

package se228.richard.ebookstore.entity;

public class OutputMessage {

    private String sender;
    private String time;
    private String content;

    public OutputMessage(String sender, String time, String content) {
        this.sender = sender;
        this.time = time;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

}
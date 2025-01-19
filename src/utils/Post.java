package utils;

import users.Employee;
import users.User;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Post implements Serializable, Comparable<Post> {
    private Date date;
    private String text;
    private Employee author;
    private Employee recipient;

    public Post(){
        this.date = new Date();
    }
    public Post(String text, Employee author){
        this();
        this.text = text;
        this.author = author;
    }

    public Post(String text, Employee author, Employee recipient){
        this();
        this.text = text;
        this.author = author;
        this.recipient = recipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(Employee author) {
        this.author = author;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(Employee recipient) {
        this.recipient = recipient;
    }

    @Override
    public int compareTo(Post o) {
        return -1 * this.date.compareTo(o.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author,text,date);
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass());
        Post p = (Post) obj;
        return author.equals(p.author) && recipient.equals(p.recipient) && text.equals(p.text);
    }

    public String toString(){
        return "🐱‍💻: " + (author==null?"SYSTEM": author) + "    [" + date.toLocaleString() + "]" + "\n\t" + text;
    }
}

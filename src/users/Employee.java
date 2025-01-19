package users;

import database.Data;
import utils.Post;
import utils.Request;
import enums.School;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;


public abstract class Employee extends User{
    private double salary;
    private Vector<Post> messages;

    public Employee() {
        super();
    }

    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Employee(String firstName, String lastName, double salary){
        super(firstName, lastName);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Vector<Post> getMessages() {
        return messages;
    }

    public void setMessages(Vector<Post> messages) {
        this.messages = messages;
    }



    public void sendMessage(Employee recipient, String message) {
        if (recipient == null || message == null || message.isBlank()) {
            throw new IllegalArgumentException("Recipient or message cannot be null or empty.");
        }

        Post post = new Post(message, this, recipient);
        Data.INSTANCE.getMessages().add(post);
    }

    public void readMessages() {
        List<Post> receivedMessages = getMessages();

        if (receivedMessages.isEmpty()) {
            System.out.println("No messages for " + getFirstName() + " " + getLastName());
        } else {
            System.out.println("Messages for " + getFirstName() + " " + getLastName() + ":");
            receivedMessages.forEach(System.out::println);
        }
    }
    
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return Objects.equals(this.getSalary(), other.getSalary()) && Objects.equals(this.getFirstName(), other.getFirstName()) &&
                Objects.equals(this.getLastName(), other.getLastName());
    }
    

}

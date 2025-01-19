package users;

import database.Data;
import utils.Post;
import utils.Request;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Dean extends Employee {

    public Dean() {
        super();
    }

    public Dean(String firstName, String lastName) {
        super(firstName, lastName);
    }


    public List<Request> getRequests() {
        List<Request> receivedComplaints = Data.INSTANCE.getRequests().stream()
                .filter(req -> req.getRecipient().equals(this))
                .collect(Collectors.toList());

        return receivedComplaints;
    }

    public void signRequest(Request request) {
        request.setSigned(true);
    }

    public void rejectRequest(Request request) {
        if (!(request.getAuthor() instanceof Employee author)) {
            throw new IllegalArgumentException("Only employees can send requests.");
        }

        Data.INSTANCE.getRequests().remove(request);
        Data.INSTANCE.getMessages().add(new Post("Your request: \"" + request.getText() + "\" was rejected.", this, author));
    }

    public void redirectRequest(Request request, Manager manager) {
        if (request == null || manager == null) {
            throw new IllegalArgumentException("Request or Manager cannot be null.");
        }
        request.setRecipient(manager);
    }

}
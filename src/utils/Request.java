package utils;

import users.Employee;
import users.User;

public class Request extends Post{
    private boolean signed;
    public Request() {
        super();
    }
    public Request(String content, Employee author, Employee recipient) {
        super(content, author, recipient);
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public boolean isSigned() {
        return signed;
    }

}

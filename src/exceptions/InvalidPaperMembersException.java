package exceptions;

public class InvalidPaperMembersException extends Exception{
    public InvalidPaperMembersException(String errorMessage) {
        super(errorMessage);
    }
}

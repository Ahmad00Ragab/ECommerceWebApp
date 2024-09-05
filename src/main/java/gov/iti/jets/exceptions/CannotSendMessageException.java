package gov.iti.jets.exceptions;

public class CannotSendMessageException extends RuntimeException{
   public CannotSendMessageException(){
        super("Can't send message to this Email Address");
    }
}

package exceptions;

public class AlreadyDoneException extends Exception {
    public AlreadyDoneException() {
        System.out.println("Has been already transferred");
    }
}

package exception;

public class NotYetException extends Exception {
    private static String errorCode = "10003";
    private static String errorMsg = "还没到你";

    public NotYetException() {
        super(errorMsg);
    }

    public NotYetException(String errorMsg ) {
        super(errorMsg);
    }
}

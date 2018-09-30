package exception;

public class IsGangException extends Exception {
    private static String errorCode = "10007";
    private static String errorMsg = "这是杠";

    public IsGangException() {
        super(errorMsg);
    }

    public IsGangException(String errorMsg ) {
        super(errorMsg);
    }
}

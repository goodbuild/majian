package exception;

public class IsPengException extends Exception {
    private static String errorCode = "10006";
    private static String errorMsg = "不能碰";

    public IsPengException() {
        super(errorMsg);
    }

    public IsPengException(String errorMsg ) {
        super(errorMsg);
    }
}

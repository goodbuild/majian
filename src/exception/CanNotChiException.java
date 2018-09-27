package exception;

public class CanNotChiException extends Exception {
    private static String errorCode = "10004";
    private static String errorMsg = "不能吃";

    public CanNotChiException() {
        super(errorMsg);
    }

    public CanNotChiException(String errorMsg ) {
        super(errorMsg);
    }
}

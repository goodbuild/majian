package exception;

public class CanNotWinException extends Exception {
    private static String errorCode = "10004";
    private static String errorMsg = "不能赢";

    public CanNotWinException() {
        super(errorMsg);
    }

    public CanNotWinException(String errorMsg ) {
        super(errorMsg);
    }
}

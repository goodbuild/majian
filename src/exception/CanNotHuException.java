package exception;

public class CanNotHuException extends Exception {
    private static String errorCode = "10005";
    private static String errorMsg = "不能赢";

    public CanNotHuException() {
        super(errorMsg);
    }

    public CanNotHuException(String errorMsg ) {
        super(errorMsg);
    }
}

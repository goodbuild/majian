package exception;

/**
 * @Title: 人数不足异常
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午2:38
 * @Version 1.0.0
 */
public class InsufficientPlayerException extends Exception {
    private static String errorCode = "10001";
    private static String errorMsg = "人数不足";

    public InsufficientPlayerException() {
        super(errorMsg);
    }
}

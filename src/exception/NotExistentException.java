package exception;

/**
 * @Title: 人数不足异常
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午2:38
 * @Version 1.0.0
 */
public class NotExistentException extends Exception {
    private static String errorCode = "10002";
    private static String errorMsg = "不存在该麻将";

    public NotExistentException() {
        super(errorMsg);
    }

    public NotExistentException(String errorMsg ) {
        super(errorMsg);
    }
}

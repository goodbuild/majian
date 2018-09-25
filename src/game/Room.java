package game;

/**
 * @Title: Room
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午2:00
 * @Version 1.0.0
 */
public interface Room {
    public void addGamePlayer(GamePlayer gamePlayer) throws Exception;

    public void delGamePlayer(int weizhi) throws Exception;

    public void jieshang();
}

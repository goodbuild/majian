package game;

import enums.RoomStatusEnum;
import game.GamePlayer;
import game.MaJiang;

import java.util.List;

/**
 * @Title: 房间功能接口
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午2:00
 * @Version 1.0.0
 */
public interface Room {
    public void addGamePlayer(GamePlayer gamePlayer) throws Exception;

    public void delGamePlayer(int weizhi) throws Exception;

    public void jieshang() throws Exception;

    public void setCurrPlayer(GamePlayer currGamePlayer);

    public void initCurrGamePlayer(int index) throws Exception;

    public void faPai(List<MaJiang> maJiangs) throws Exception;

    public void in(List<MaJiang> maJiangs, boolean isFromBegin);

    public GamePlayer getCurrPlayer();

    public GamePlayer getPrevPlayer();

    public String getRoomNum();

    public int getFixedPlayerNum();

    public List<GamePlayer> getPlayers();

    public RoomStatusEnum getRoomStatus();

    public void setRoomStatus(RoomStatusEnum roomStatusEnum);

    public int getInitMaJiangNum();

    public int getMaxNumberByGame();

    public GamePlayer nextGamePlayer();
}

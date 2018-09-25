package game;

import exception.InsufficientPlayerException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Title: 普通麻将牌桌类
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午1:10
 * @Version 1.0.0
 */
public class BRPG implements Rules, Room{
    private List<MaJiang> MaJiangs = new ArrayList<MaJiang>();
    private List<GamePlayer> players = new ArrayList<GamePlayer>();
    private String roomNum;
    private final int playerNum = 4; //
    private final int initMaJiangum= 12; //
    private RoomStatus roomStatus = RoomStatus.WaitPlayer;

    public BRPG() {
        initMaJiang();
    }

    private void initMaJiang() {
        MaJiangs.addAll(Utils.initFeiZiMaJiang());
        MaJiangs.addAll(Utils.initZiMaJiang());
    }


    @Override
    public void faPai() throws Exception {
        // 乱序
        switch (roomStatus) {
            case WaitPlayer: throw new InsufficientPlayerException();
            case Palying: throw new Exception(roomStatus.name());
            case WaitFaPai:
            case End:
            default:
                Collections.shuffle(MaJiangs); break;
        }

        // 发牌
        for (int i= 0; i< initMaJiangum; i++) {
            for (int j = 1; j <= playerNum; j++) {
                GamePlayer gamePlayer = players.get(j-1);
                gamePlayer.in(MaJiangs.get(i * j));
            }
        }

        // MaJiangs.remove(0);

        for (GamePlayer gamePlayer : players) {
            gamePlayer.sort();
        }

        this.roomStatus = RoomStatus.Palying;
    }

    @Override
    public boolean peng() {
        return false;
    }

    @Override
    public boolean gang() {
        return false;
    }

    @Override
    public boolean hu() {
        return false;
    }

    @Override
    public boolean zimo() {
        return false;
    }

    @Override
    public boolean chi() {
        return false;
    }

    @Override
    public void addGamePlayer(GamePlayer gamePlayer) throws Exception {
        if (!this.roomStatus.equals(RoomStatus.WaitPlayer)) {
            throw new Exception(this.roomStatus.getName());
        }

        this.players.add(gamePlayer);

        if (this.players.size() == playerNum) {
            this.roomStatus = RoomStatus.WaitFaPai;
        }
    }

    @Override
    public void delGamePlayer(int weizhi) throws Exception {
        if (!roomStatus.equals(RoomStatus.Palying)) {
            throw new Exception(roomStatus.name());
        }

        this.players.remove(weizhi);

        this.roomStatus = RoomStatus.WaitPlayer;
    }

    @Override
    public void jieshang() {

    }


    @Override
    public String toString() {
        return players.toString();
    }
}

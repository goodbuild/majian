package game2.baseMajiang;

import game2.GamePlayer;
import game2.Room;
import game2.enums.RoomStatusEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseRoomImpl implements Room {
    protected List<GamePlayer> players = new ArrayList<GamePlayer>();
    protected RoomStatusEnum roomStatus = RoomStatusEnum.WaitPlayer;
    protected String roomNum;
    protected int playerNum;

    private GamePlayer currPlayer;
    private GamePlayer prevPlayer;

    public BaseRoomImpl(int playerNum) {
        this.playerNum = playerNum;
        this.roomNum = UUID.randomUUID().toString();
    }

    @Override
    public void addGamePlayer(GamePlayer gamePlayer) throws Exception {
        if (!this.roomStatus.equals(RoomStatusEnum.WaitPlayer)) {
            throw new Exception(this.roomStatus.getName());
        }

        this.players.add(gamePlayer);

        if (this.players.size() == playerNum) {
            this.roomStatus = RoomStatusEnum.WaitFaPai;
        }

    }

    @Override
    public void delGamePlayer(int weizhi) throws Exception {
        if (!roomStatus.equals(RoomStatusEnum.Palying)) {
            throw new Exception(roomStatus.name());
        }

        this.players.remove(weizhi);

        this.roomStatus = RoomStatusEnum.WaitPlayer;
    }

    @Override
    public void jieshang() throws Exception {
        // TODO
        if (!roomStatus.equals(RoomStatusEnum.End)) {
            throw new Exception(roomStatus.name());
        }

    }

    @Override
    public void setCurrGamePlayer(GamePlayer gamePlayer) {
        this.prevPlayer = this.currPlayer;
        this.currPlayer = gamePlayer;
    }

    @Override
    public void initCurrGamePlayer(int index) {
        currPlayer = this.players.get(index / this.players.size());
        this.prevPlayer = null;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public GamePlayer getCurrPlayer() {
        return currPlayer;
    }

    public GamePlayer getPrevPlayer() {
        return prevPlayer;
    }
}

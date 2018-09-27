package game.baseMajiang;

import exception.InsufficientPlayerException;
import game.GamePlayer;
import game.MaJiang;
import game.Room;
import enums.RoomStatusEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class BaseRoomImpl implements Room {
    private List<GamePlayer> players = new ArrayList<GamePlayer>();
    private RoomStatusEnum roomStatus = RoomStatusEnum.WaitPlayerJoin;
    private String roomNum;
    private int fixedPlayerNum;
    private int initMaJiangNum;
    private int maxNumberByGame;

    private GamePlayer currPlayer;
    private GamePlayer prevPlayer;
    private int numberByGame = 0;

    public BaseRoomImpl(int fixedPlayerNum,int initMaJiangNum, int maxNumberByGame) {
        this.fixedPlayerNum = fixedPlayerNum;
        this.initMaJiangNum = initMaJiangNum;
        this.maxNumberByGame = maxNumberByGame;
        this.roomNum = UUID.randomUUID().toString();
    }

    @Override
    public void addGamePlayer(GamePlayer gamePlayer) throws Exception {
        if (!this.roomStatus.equals(RoomStatusEnum.WaitPlayerJoin)) {
            throw new Exception(this.roomStatus.getName());
        }

        this.players.add(gamePlayer);

        if (this.players.size() == fixedPlayerNum) {
            this.roomStatus = RoomStatusEnum.WaitFaPai;
        }

    }

    @Override
    public void delGamePlayer(int weizhi) throws Exception {
        if (!roomStatus.equals(RoomStatusEnum.Playing)) {
            throw new Exception(roomStatus.name());
        }

        this.players.remove(weizhi);

        this.roomStatus = RoomStatusEnum.WaitPlayerJoin;
    }

    @Override
    public void jieshang() throws Exception {
        // TODO
        if (!roomStatus.equals(RoomStatusEnum.Win)) {
            throw new Exception(roomStatus.name());
        }

    }

    @Override
    public void setCurrPlayer(GamePlayer gamePlayer) {
        this.prevPlayer = this.currPlayer;
        this.currPlayer = gamePlayer;
    }

    @Override
    public void initCurrGamePlayer(int index) throws Exception {
        if (!RoomStatusEnum.WaitFaPai.equals(roomStatus)) {
            throw new Exception("还不能轮庄");
        }
        currPlayer = this.players.get(index / this.players.size());
        this.prevPlayer = null;
    }

    @Override
    public void faPai(List<MaJiang> maJiangs) throws Exception {
        if (currPlayer == null) {
            throw new Exception("还没指定庄家");
        }

        // 乱序
        switch (this.roomStatus) {
            case WaitPlayerJoin: throw new InsufficientPlayerException();
            case Playing: throw new Exception(roomStatus.name());
            case WaitFaPai:
            case Win:
            default:
                Collections.shuffle(maJiangs); break;
        }

        // 发牌
        for (int i = 0; i< initMaJiangNum; i++) {
            for (int j = 1; j <= fixedPlayerNum; j++) {
                GamePlayer gamePlayer = players.get(j-1);
                _faPai(maJiangs, gamePlayer);
            }
        }

        // 庄家再多拿张牌
        _faPai(maJiangs, getCurrPlayer());

        for (GamePlayer gamePlayer : players) {
            gamePlayer.sortAndInit();
        }

        this.roomStatus = RoomStatusEnum.Playing;

    }

    @Override
    public void in(List<MaJiang> maJiangs, boolean isFromBegin) {
        int index = isFromBegin ? 0 : maJiangs.size() - 1;
        GamePlayer gamePlayer = getCurrPlayer();

        MaJiang maJiang = maJiangs.get(index);

        gamePlayer.in(maJiang);
        maJiangs.remove(index);
    }

    private void _faPai(List<MaJiang> maJiangs, GamePlayer gamePlayer) {
        gamePlayer.in(maJiangs.get(0));
        maJiangs.remove(0);
    }

    @Override
    public String getRoomNum() {
        return roomNum;
    }

    @Override
    public int getFixedPlayerNum() {
        return fixedPlayerNum;
    }

    @Override
    public GamePlayer getCurrPlayer() {
        return currPlayer;
    }

    @Override
    public GamePlayer getPrevPlayer() {
        return prevPlayer;
    }

    @Override
    public List<GamePlayer> getPlayers() {
        return players;
    }

    @Override
    public RoomStatusEnum getRoomStatus() {
        return roomStatus;
    }

    @Override
    public void setRoomStatus(RoomStatusEnum roomStatusEnum) {
        this.roomStatus = roomStatusEnum;
    }

    @Override
    public int getInitMaJiangNum() {
        return initMaJiangNum;
    }

    public int getMaxNumberByGame() {
        return maxNumberByGame;
    }

    @Override
    public GamePlayer nextGamePlayer() {
        int currPlayerIndex = players.indexOf(currPlayer);
        GamePlayer nextGamePlayer = players.get(( currPlayerIndex+ 1) % fixedPlayerNum);
        setCurrPlayer(nextGamePlayer);
        return nextGamePlayer;
    }
}

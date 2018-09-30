package game;

import config.Config;
import enums.RoomStatusEnum;
import exception.CanNotChiException;
import exception.NotExistentException;
import exception.NotYetException;
import enums.PlayRuleEnum;

import java.util.*;

/**
 * @Title: 普通麻将牌桌类
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午1:10
 * @Version 1.0.0
 */
public abstract class Play {
    // 这个改为依赖关系更合理 BaseRoomImpl
    // Play 添加对应的方法
    private List<MaJiang> maJiangs = new ArrayList<>();
    private Map<PlayRuleEnum, Boolean> playRuleEnums = new HashMap<>();
    private Room room;
    private Rules rules;

    private int playerNum; //

    /**
     * 初始房价和麻将规则
     * @param room
     * @param rules
     */
    public Play(Room room, Rules rules) {
        this.room = room;
        this.rules = rules;
        this.playerNum = room.getFixedPlayerNum();

        maJiangs = initMaJiang();
        playRuleEnums = initRules();
    }

    /**
     * 定义初始总麻将数
     * @return
     */
    public abstract List<MaJiang> initMaJiang();

    /**
     * 定义可以玩的玩法
     * @return
     */
    public abstract Map<PlayRuleEnum, Boolean> initRules();

    /**
     * 定义玩法扣分
     * @param playRuleEnum
     * @return
     */
    protected abstract int scoring(PlayRuleEnum playRuleEnum);

    /**
     * 定庄家
     */
    protected abstract int initCurrGamePlayerByStartAndEnd(boolean isStart);

    /**
     * 计算分数
     * @param type
     */
    private void _scoring(PlayRuleEnum type) {
        Boolean isSupport = playRuleEnums.get(type);

        if (!isSupport) {
            return;
        }

        int score = scoring(type);
        GamePlayer currPlay = room.getCurrPlayer();
        GamePlayer prevPlayer = room.getPrevPlayer();

        // 主要分为都扣还是一对一扣，两种
        switch (type) {
            case anGang:
            case mingGang:
            case zimo:
                // 每家都扣
                for (GamePlayer gamePlayer : room.getPlayers()) {
                    if(!gamePlayer.equals(currPlay)) {
                        gamePlayer.setScores(-score);
                        currPlay.setScores(score);
                    }
                }
                break;
            default:
                // 一对一
                currPlay.setScores(score);
                prevPlayer.setScores(-score);
        }

    }

    public void addGamePlayer(GamePlayer gamePlayer) throws Exception {
        room.addGamePlayer(gamePlayer);
    }

    public void delGamePlayer(int index) throws Exception {
        room.delGamePlayer(index);
    }

    public void jieshang() throws Exception {
        room.jieshang();
    }

    public void initCurrGamePlayer(boolean isStart) throws Exception {
        int index = initCurrGamePlayerByStartAndEnd(isStart);
        room.initCurrGamePlayer(index);
    }

    /**
     * 一盘结束
     */
    private void win() throws Exception {
        initCurrGamePlayer(false);
        playerNum++;
        room.setRoomStatus(RoomStatusEnum.Win);

        for (GamePlayer gamePlayer:
             room.getPlayers()) {
            gamePlayer.cleanMaJiangs();
        }

        if (playerNum >= room.getMaxNumberByGame()) {
            jieshang();
        }
    }

    public void peng(GamePlayer currGamePalyer, MaJiang maJiang) throws NotExistentException {
        rules.peng(currGamePalyer, maJiang);
        room.setCurrPlayer(currGamePalyer);

        // 计分
        _scoring(PlayRuleEnum.peng);
    }

    public void gang(GamePlayer currGamePalyer, MaJiang maJiang) throws NotExistentException {
        rules.gang(currGamePalyer, maJiang);
        room.setCurrPlayer(currGamePalyer);

        _scoring(PlayRuleEnum.mingGang);
    }

    public void hu(GamePlayer currGamePalyer, MaJiang maJiang) throws Exception {
        rules.hu(currGamePalyer, maJiang);
        room.setCurrPlayer(currGamePalyer);

        _scoring(PlayRuleEnum.hu);

        win();
    }

    public void zimo(GamePlayer currGamePalyer, MaJiang maJiang) throws Exception {
        GamePlayer gamePlayer = room.getCurrPlayer();
        if (!gamePlayer.equals(currGamePalyer)) {
            throw new NotYetException();
        }
        rules.zimo(currGamePalyer, maJiang);

        _scoring(PlayRuleEnum.zimo);

        win();
    }

    public void chi(GamePlayer currGamePalyer, MaJiang maJiang) throws Exception {
        GamePlayer gamePlayer = room.getCurrPlayer();
        if (!gamePlayer.equals(currGamePalyer)) {
            throw new NotYetException();
        }

        // 吃到时还可以多个玩法
        rules.chi(gamePlayer, maJiang);

        _scoring(PlayRuleEnum.chi);
    }

    public void in() throws CanNotChiException {
        room.in(maJiangs, true);
        System.out.println(room.getCurrPlayer());
    }

    public void out(GamePlayer currGamePalyer, MaJiang maJiang) throws Exception {
        GamePlayer gamePlayer = room.getCurrPlayer();
        // TODO 删除日志
        System.out.println(gamePlayer.getUserId() + " : " + maJiang.toString());
        if (!currGamePalyer.equals(gamePlayer)) {
            throw new NotYetException();
        }
        gamePlayer.out(maJiang);

        check(maJiang);

        room.nextGamePlayer();

        // TODO 删除日志
        gamePlayer.toString();
    }

    public void faPai() throws Exception {
        initCurrGamePlayer(true);
        room.faPai(maJiangs);
    }

    public Map<GamePlayer, List<PlayRuleEnum>> check(MaJiang maJiang) {
        Map<GamePlayer, List<PlayRuleEnum>> playerMap = new HashMap<>();
        for (GamePlayer gamePlayer: room.getPlayers()) {
            List<PlayRuleEnum> playRuleEnum = new ArrayList<>();

            Integer number = gamePlayer.getMaJiangMap().get(maJiang);

            if (number == null) {
                continue;
            }
            //检查 碰
            if (number > Config.NUM_PENG) {
                playRuleEnum.add(PlayRuleEnum.peng);
                room.setRoomStatus(RoomStatusEnum.Wait);
            }

            //检查 杠
            if (number > Config.NUM_GANG) {
                playRuleEnum.add(PlayRuleEnum.gang);
                room.setRoomStatus(RoomStatusEnum.Wait);
            }

            int winIndex = gamePlayer.getWinList().indexOf(maJiang);
            //检查 糊 / 自摸
            if (winIndex > -1) {
                boolean isMyself = gamePlayer.equals(room.getCurrPlayer());
                if (isMyself) {
                    playRuleEnum.add(PlayRuleEnum.zimo);
                } else {
                    playRuleEnum.add(PlayRuleEnum.hu);
                }
            }

            //TODO 检查吃
        }
        return playerMap;
    }

    public int getCurrGamePlayerIndex() {
        return room.getPlayers().indexOf(room.getCurrPlayer());
    }

    public GamePlayer getCurrPlayer() {
        return room.getCurrPlayer();
    }

    @Override
    public String toString() {
        return room.getPlayers().toString();
    }
}

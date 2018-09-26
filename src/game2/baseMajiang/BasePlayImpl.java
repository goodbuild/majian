package game2.baseMajiang;

import exception.InsufficientPlayerException;
import game2.GamePlayer;
import game2.MaJiang;
import game2.Play;
import game2.enums.PlayRuleEnum;
import game2.enums.RoomStatusEnum;
import game2.util.Utils;

import java.util.*;

/**
 * @Title: 普通麻将牌桌类
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午1:10
 * @Version 1.0.0
 */
public class BasePlayImpl extends BaseRoomImpl implements Play {
    private List<MaJiang> maJiangs = new ArrayList<>();
    private Map<PlayRuleEnum, Boolean> playRuleEnums = new HashMap<>();
    private final static int playerNum = 4; //
    private final static int initMaJiangNum = 12; //

    public BasePlayImpl() {
        super(playerNum);
        initMaJiang();
    }

    @Override
    public void initMaJiang() {
        maJiangs.addAll(Utils.initFeiZiMaJiang());
        maJiangs.addAll(Utils.initZiMaJiang());
    }

    @Override
    public void initRules() {
        playRuleEnums.put(PlayRuleEnum.peng, true);
        playRuleEnums.put(PlayRuleEnum.gang, true);
        playRuleEnums.put(PlayRuleEnum.zimo, true);
        playRuleEnums.put(PlayRuleEnum.hu, false);
        playRuleEnums.put(PlayRuleEnum.chi, false);
    }

    public void faPai() throws Exception {
        if (getCurrPlayer() == null) {
            throw new Exception("还没指定庄家");
        }

        // 乱序
        switch (this.roomStatus) {
            case WaitPlayer: throw new InsufficientPlayerException();
            case Palying: throw new Exception(roomStatus.name());
            case WaitFaPai:
            case End:
            default:
                Collections.shuffle(maJiangs); break;
        }

        // 发牌1
        /*
        for (int i = 0; i< initMaJiangNum; i++) {
            for (int j = 1; j <= playerNum; j++) {
                GamePlayer gamePlayer = players.get(j-1);
                gamePlayer.in(maJiangs.get(i * j));
            }
        }

        */

        // 发牌2
        for (int i = 0; i< initMaJiangNum; i++) {
            for (int j = 1; j <= playerNum; j++) {
                GamePlayer gamePlayer = players.get(j-1);
                _faPai(gamePlayer);
            }
        }

        // 庄家再多拿张牌
        _faPai(getCurrPlayer());

        for (GamePlayer gamePlayer : players) {
            gamePlayer.sortAndInit();
        }

        this.roomStatus = RoomStatusEnum.Palying;
    }

    private void _faPai(GamePlayer gamePlayer) {
        gamePlayer.in(maJiangs.get(0));
        maJiangs.remove(0);
    }

    @Override
    public String toString() {
        return players.toString();
    }
}

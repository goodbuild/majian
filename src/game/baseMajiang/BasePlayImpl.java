package game.baseMajiang;

import enums.PlayRuleEnum;
import game.MaJiang;
import game.Play;
import util.Utils;

import java.util.*;

/**
 * @Title: 普通麻将牌桌类
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午1:10
 * @Version 1.0.0
 */
public class BasePlayImpl extends Play {
    private final static int playerNum = 4; //
    private final static int initMaJiangNum = 12; //

    public BasePlayImpl(int maxNumberByGame){
        super(new BaseRoomImpl(playerNum, initMaJiangNum, maxNumberByGame),
                new BaseRulesImpl());
    }

    @Override
    public List<MaJiang> initMaJiang() {
        List<MaJiang> list = new ArrayList<>();
        list.addAll(Utils.initNoWordMaJiang());
        list.addAll(Utils.initZiMaJiang());

        return list;

    }

    @Override
    public Map<PlayRuleEnum, Boolean> initRules() {
        Map<PlayRuleEnum, Boolean> playRuleEnums = new HashMap<>();
        playRuleEnums.put(PlayRuleEnum.peng, true);
        playRuleEnums.put(PlayRuleEnum.mingGang, true);
        playRuleEnums.put(PlayRuleEnum.zimo, true);
//        playRuleEnums.put(PlayRuleEnum.hu, false);
//        playRuleEnums.put(PlayRuleEnum.chi, false);

        return playRuleEnums;
    }

    @Override
    protected int scoring(PlayRuleEnum playRuleEnum) {
        switch (playRuleEnum) {
            case hu:
                return 1;
            case gang:
                return 2;
            case anGang:
                return 2;
            case mingGang:
                return 3;

             default:
                 return 0;
        }
    }

    @Override
    public int initCurrGamePlayerByStartAndEnd(boolean isStart) {
        return isStart ? 0 : getCurrGamePlayerIndex();
    }
}

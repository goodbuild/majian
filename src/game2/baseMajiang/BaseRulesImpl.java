package game2.baseMajiang;

import exception.NotExistentException;
import game2.GamePlayer;
import game2.MaJiang;
import game2.Rules;

import java.util.Map;

public class BaseRulesImpl implements Rules {
    private final int Invalid = -99;

    @Override
    public void peng(GamePlayer currGamePalyer, MaJiang maJiang) throws NotExistentException {
        removeMaJiang(currGamePalyer, maJiang, 2);
    }

    @Override
    public void gang(GamePlayer currGamePalyer, MaJiang maJiang) throws NotExistentException {
        removeMaJiang(currGamePalyer, maJiang, 3);
    }

    private void removeMaJiang(GamePlayer currGamePalyer, MaJiang maJiang, int removieNum) throws NotExistentException {
        Map<MaJiang, Integer> maJiangMap = currGamePalyer.getMaJiangMap();
        Integer num = maJiangMap.get(maJiang);
        if (num == null || num != removieNum) {
            throw new NotExistentException();
        }

        for (int i = 0; i < removieNum; i++) {
            currGamePalyer.getMaJiangs().remove(maJiang);
        }

        maJiangMap.remove(maJiang);
        currGamePalyer.getMaJiangOutMap().put(maJiang, removieNum + 1);
    }

    @Override
    public void hu(GamePlayer currGamePalyer, MaJiang maJiang) {

    }

    @Override
    public void zimo(GamePlayer currGamePalyer, MaJiang maJiang) {

    }

    @Override
    public void chi(GamePlayer currGamePalyer, MaJiang maJiang) {

    }
}

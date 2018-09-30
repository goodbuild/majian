package game.baseMajiang;

import exception.CanNotChiException;
import exception.CanNotHuException;
import exception.NotExistentException;
import game.GamePlayer;
import game.MaJiang;
import game.Rules;

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
    public void hu(GamePlayer currGamePalyer, MaJiang maJiang) throws CanNotHuException {
        if (!currGamePalyer.getWinList().contains(maJiang)) {
            throw new CanNotHuException();
        }
    }

    @Override
    public void zimo(GamePlayer currGamePalyer, MaJiang maJiang) throws CanNotHuException {
        if (!currGamePalyer.getWinList().contains(maJiang)) {
            throw new CanNotHuException();
        }
    }

    @Override
    public void chi(GamePlayer currGamePalyer, MaJiang maJiang) throws CanNotChiException {
        if (!currGamePalyer.getChiList().contains(maJiang)) {
            throw new CanNotChiException();
        }
    }
}

package game.baseMajiang;

import config.Config;
import exception.*;
import game.GamePlayer;
import game.MaJiang;
import game.Rules;
import util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseRulesImpl implements Rules {
    @Override
    public void peng(GamePlayer currGamePalyer, MaJiang maJiang) throws NotExistentException {
        removeMaJiang(currGamePalyer, maJiang, 2);
    }

    @Override
    public void gang(GamePlayer currGamePalyer, MaJiang maJiang) throws NotExistentException {
        removeMaJiang(currGamePalyer, maJiang, 3);
    }

    private void removeMaJiang(GamePlayer currGamePalyer, MaJiang maJiang, int removieNum) throws NotExistentException {
        Map<MaJiang, Integer> maJiangMap = null;//currGamePalyer.getPengMap();
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

    @Override
    public List<MaJiang> ting(List<MaJiang> list) throws CanNotHuException {
        // 单张
        int num = list.size();
        if (num == 1) {
            return list;
        }

        List<MaJiang> huList = new ArrayList<>();
        int pengNum = 0;
        int checkout = 0;
        for (int i = 0; i < list.size()-1;) {
            MaJiang one = list.get(i);
            MaJiang two = list.get(i+1);
            MaJiang three = null;

            try{
                three = list.get(i+2);
            } catch (Exception e) {
            }

            try {
                if (one.getSortId() - three.getSortId() != Config.RANGE_AC) {// 本身不是ABC,但这里有个bug 就是 23456 这种糊147 会变成 47
                    huList.addAll(Utils.getChi(one, two, three));
                    checkout++;
                    i = i +2;
                } else {
                    i = i + 3;
                }
            } catch (CanNotChiException e) {
                huList.add(one);
                i++;
            } catch (IsPengException e) {
                pengNum++;
                huList.add(one);
                i = i +2;
            } catch (IsGangException e) {
                i = i + 3;
            }

            if (checkout > 3 || pengNum > 2) {
                throw new CanNotHuException();
            }
        }

        return huList;
    }
}

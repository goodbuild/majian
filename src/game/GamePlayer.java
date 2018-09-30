package game;

import config.Config;
import exception.CanNotChiException;
import exception.CanNotHuException;
import util.Utils;

import java.util.*;

/**
 * @Title: GamePlayer
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午1:24
 * @Version 1.0.0
 */
public class GamePlayer {
    private int scores = 0;
    private String userId;

    private List<MaJiang> maJiangs = new ArrayList<>();
    private List<MaJiang> pengList = new ArrayList<>();
    private List<MaJiang> gangList = new ArrayList<>();
    private List<MaJiang> anGangList = new ArrayList<>();
    private List<MaJiang> winList = new ArrayList<>();
    private List<MaJiang> chiList = new ArrayList<>();
    private Map<MaJiang, Integer> maJiangOutMap = new HashMap<>();

    public GamePlayer(String userId, int scores) {
        this.scores = scores;
        this.userId = userId;
    }

    public void in(MaJiang maJiang) {
        // 可以优化算法
        maJiangs.add(maJiang);
        sortAndInit();
    }

    public void out(MaJiang maJiang) {
        // 可以优化算法
        maJiangs.remove(maJiang);
        sortAndInit();
    }

    public void sortAndInit() {
        maJiangs.sort(new Comparator<MaJiang>() {
            public int compare(MaJiang n1, MaJiang n2) {
                return n1.getSortId() - n2.getSortId();
            }
        });

        Map<MaJiang, Integer> map = new HashMap<>();

        // mark peng & Gang & Chi
        for(MaJiang maJiang : maJiangs) {
            int num = map.get(maJiang) == null ? 1 : map.get(maJiang) + 1;

            switch (num) {
                case Config.NUM_PENG:
                    pengList.add(maJiang);
                    break;
                case Config.NUM_GANG:
                    pengList.remove(maJiang);
                    gangList.add(maJiang);
                    break;
                case Config.NUM_ANGANG:
                    gangList.remove(maJiang);
                    anGangList.add(maJiang);
                    break;
            }

            map.put(maJiang, num);
        }

        try {
            chiList = Utils.fingChi(maJiangs);
        } catch (CanNotChiException e) {
            chiList.clear();
        }
    }

    public void cleanMaJiangs() {
        this.maJiangs.clear();
        this.maJiangOutMap.clear();
        this.anGangList.clear();
        this.gangList.clear();
        this.pengList.clear();
    }

    public List<MaJiang> getMaJiangs() {
        return maJiangs;
    }

    public void setMaJiangs(List<MaJiang> MaJiangs) {
        this.maJiangs = MaJiangs;
    }

    public Map<MaJiang, Integer> getMaJiangOutMap() {
        return maJiangOutMap;
    }

    @Override
    public String toString() {
        // TODO 删除日志
        System.out.println(userId);
        System.out.println("     " + maJiangs.toString());
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            return this.userId.equals(obj);
        }

        GamePlayer _obj = (GamePlayer)obj;
        return this.userId.equals(_obj.userId);
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = this.scores + scores;
    }

    public String getUserId() {
        return userId;
    }

    public List<MaJiang> getWinList() {
        return winList;
    }

    public List<MaJiang> getChiList() {
        return chiList;
    }

    public void setWinList(List<MaJiang> winList) {
        this.winList = winList;
    }

    public List<MaJiang> getPengList() {
        return pengList;
    }

    public List<MaJiang> getGangList() {
        return gangList;
    }

    public List<MaJiang> getAnGangList() {
        return anGangList;
    }
}

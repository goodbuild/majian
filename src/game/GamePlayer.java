package game;

import config.Config;
import exception.CanNotChiException;
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
    private Map<MaJiang, Integer> maJiangMap = new HashMap<>();
    private List<MaJiang> winList = new ArrayList<>();
    private List<MaJiang> chiList = new ArrayList<>();
    private Map<MaJiang, Integer> maJiangOutMap = new HashMap<>();

    public GamePlayer(String userId, int scores) {
        this.scores = scores;
        this.userId = userId;
    }

    public void in(MaJiang maJiang) throws CanNotChiException {
        // 可以优化算法
        maJiangs.add(maJiang);
        sortAndInit();
    }

    public void out(MaJiang maJiang) throws CanNotChiException {
        // 可以优化算法
        maJiangs.remove(maJiang);
        sortAndInit();
    }

    public void sortAndInit() throws CanNotChiException {
        maJiangs.sort(new Comparator<MaJiang>() {
            public int compare(MaJiang n1, MaJiang n2) {
                return n2.getSortId() - n1.getSortId();
            }
        });

        // 还可以优化，3次循环变成一次，并且可以考虑这块挪到Play类下面会更合理和方便扩展
        try {
            makePengAndGang();
            makeWin();
            makeChi();
        } catch (Exception e) {

        }
    }

    private void makeChi() throws CanNotChiException {
        chiList = Utils.fingChi(maJiangs);
    }

    private void makeWin() {
        winList.clear();
        if (maJiangs.size() < 2) {
            return;
        }

        // int num = maJiangs.size() / 3;

        // 先排除掉对或者3个的
        List<MaJiang> pengs = new ArrayList<>();
        List<MaJiang> gangs = new ArrayList<>();
        List<MaJiang> ones = new ArrayList<>();

        for (MaJiang maJiang : maJiangMap.keySet()) {
            int num = maJiangMap.get(maJiang);
            if (num == Config.NUM_PENG) {
                pengs.add(maJiang);
            } else if (num == Config.NUM_GANG) {
                gangs.add(maJiang);
            } else {
                ones.add(maJiang);
            }
        }

        // 不能胡
        if (pengs.size() > 2) {
            return;
        }

        // 去掉字
        List<MaJiang> noWordList = new ArrayList<>();

        for (MaJiang maJiang : maJiangs) {
            switch (maJiang.getMaJiangCardEnum()) {
                case WangZi:
                case TongZi:
                case SuoZi:
                    noWordList.add(maJiang);
                    break;
                default:
                    break;
            }
        }

        // 去掉单一的 ABC

        for (int i = 1; i < noWordList.size(); i = i + 2) {
            MaJiang maJiang = maJiangs.get(i);
        }

        //去掉单纯碰
        MaJiang danDiao = null;  // 单调牌
        for (MaJiang maJiang : pengs) {
            switch (maJiang.getMaJiangCardEnum()) {
                case WangZi:
                case TongZi:
                case SuoZi:
                    try {
                        int index = noWordList.indexOf(maJiang) + 1; // 碰是两个
                    } catch (Exception e) {
                        if (danDiao != null) { // 两个单调是不合理的
                            return;
                        }
                    }
            }

        }
        //一个的排序，如果有两个
        // x*AAA + y*ABC + z * DD
        for (int i = 1; i < noWordList.size(); i++) {
            MaJiang maJiang = maJiangs.get(i);
        }
    }

    private boolean isTing() {

        return false;
    }

    /**
     * 标注哪些麻将可以碰杠
     */
    private void makePengAndGang() {
        maJiangMap.clear();
        for (MaJiang maJiang : maJiangs) {
            Integer num = maJiangMap.get(maJiang);
            if (num == null) {
                num = 0;
            }
            maJiangMap.put(maJiang, ++num);
        }
    }

    public void cleanMaJiangs() {
        this.maJiangs = new ArrayList<>();
        this.maJiangMap = new HashMap<>();
        this.maJiangOutMap = new HashMap<>();
    }

    public List<MaJiang> getMaJiangs() {
        return maJiangs;
    }

    public void setMaJiangs(List<MaJiang> MaJiangs) {
        this.maJiangs = MaJiangs;
    }

    public Map<MaJiang, Integer> getMaJiangMap() {
        return maJiangMap;
    }

    public void setMaJiangMap(Map<MaJiang, Integer> maJiangMap) {
        this.maJiangMap = maJiangMap;
    }

    public Map<MaJiang, Integer> getMaJiangOutMap() {
        return maJiangOutMap;
    }

    @Override
    public String toString() {
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
}

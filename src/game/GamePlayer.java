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
                return n1.getSortId() - n2.getSortId();
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

    private void makeWin() throws CanNotHuException {
        winList = Utils.findHu(maJiangs);

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
}

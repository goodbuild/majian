package game;

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

    public GamePlayer(String userId,int scores) {
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
                return n2.getCard().compareTo(n1.getCard());
            }
        });

        // 还可以优化，3次循环变成一次，并且可以考虑这块挪到Play类下面会更合理和方便扩展
        makePengAndGang();
        makeWin();
        makeChi();
    }

    private void makeChi() {
        if (maJiangs.size() < 2) {
            return;
        }

        for (int i = 1; i < maJiangs.size()- 2; i++) {
            MaJiang maJiang = maJiangs.get(i);
        }
    }

    private void makeWin() {
        if (maJiangs.size() < 2) {
            return;
        }

        // AABB
        for (int i = 1; i < maJiangs.size(); i++) {
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

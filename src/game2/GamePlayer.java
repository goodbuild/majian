package game2;

import exception.NotExistentException;

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

    public void out(String maJiang) {
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
}

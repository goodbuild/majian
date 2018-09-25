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
    private List<MaJiang> maJiangs = new ArrayList<MaJiang>();
    private int scores = 0;
    private String userId;

    public GamePlayer(String userId,int scores) {
        this.scores = scores;
        this.userId = userId;
    }

    public void in(MaJiang MaJiang) {
        maJiangs.add(MaJiang);
    }

    public void out(String MaJiang) {

    }

    public void sort() {
        maJiangs.sort(new Comparator<MaJiang>() {
            public int compare(MaJiang n1, MaJiang n2) {
                return n2.getCard().compareTo(n1.getCard());
            }
        });

    }

    public void cleanMaJiangs() {
        this.maJiangs = new ArrayList<MaJiang>();
    }

    public List<MaJiang> getMaJiangs() {
        return maJiangs;
    }

    public void setMaJiangs(List<MaJiang> MaJiangs) {
        this.maJiangs = MaJiangs;
    }

    @Override
    public String toString() {
        System.out.println(userId);
        System.out.println("     " + maJiangs.toString());
        return "";
    }
}

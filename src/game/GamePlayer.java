package game;

import java.util.*;

import exception.*;

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

    private List<MaJiang> maJiangs = new ArrayList<MaJiang>();
    private Map<MaJiang, Integer> pengMap = new HashMap<MaJiang, Integer>();
    private Map<MaJiang, Integer> gangMap = new HashMap<MaJiang, Integer>();
    private Map<MaJiang, Integer> chiMap = new HashMap<MaJiang, Integer>();
    private final int Invalid = -99;

    public GamePlayer(String userId,int scores) {
        this.scores = scores;
        this.userId = userId;
    }

    public void in(MaJiang MaJiang) {
        maJiangs.add(MaJiang);
    }

    public void out(String MaJiang) {

    }

    public int[] peng(MaJiang maJiang) throws NotExistentException{
        Integer count =  maJiangs.indexOf(maJiang);
        if (count == 2) {
            throw new NotExistentException();
        }

        int index = maJiangs.indexOf(maJiang);
        maJiangs.remove(maJiang);
        maJiangs.remove(maJiang);
        pengMap.remove(maJiang);

        gangMap.put(maJiang, Invalid);

        int[] range = {index, index +1};
        return range;
    }

    public int[] gang(MaJiang maJiang) throws NotExistentException {
        Integer index = gangMap.get(maJiang);
        if (index == null) {
            throw new NotExistentException();
        }

        maJiangs.remove(maJiang);
        gangMap.remove(maJiang);

        int[] range = {index - 2 , index};
        return range;
    }

    public void hu(MaJiang maJiang) {

    }

    public void zimo(MaJiang maJiang) {
    }

    public boolean chi() {
        return false;
    }

    public void sortAndInit() {
        maJiangs.sort(new Comparator<MaJiang>() {
            public int compare(MaJiang n1, MaJiang n2) {
                return n2.getCard().compareTo(n1.getCard());
            }
        });


        for (MaJiang maJiang : maJiangs) {

        }

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

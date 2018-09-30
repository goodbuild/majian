import config.Config;
import enums.MaJiangCardEnum;
import game.GamePlayer;
import game.MaJiang;
import game.Play;
import util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Title: Main
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午1:08
 * @Version 1.0.0
 */
public class Main2 {
    public static void main(String[] args) throws Exception {
        GamePlayer g1 = new GamePlayer("A用户", 100);
        GamePlayer g2 = new GamePlayer("B用户", 100);
        GamePlayer g3 = new GamePlayer("C用户", 100);
        GamePlayer g4 = new GamePlayer("D用户", 100);

        Play play = PlayFactory.createBasePlay(Config.MAX_GAMG_NUM_8);

        play.addGamePlayer(g1);
        play.addGamePlayer(g2);
        play.addGamePlayer(g3);
        play.addGamePlayer(g4);

        play.faPai();

//        play.toString();

//        play.getCurrPlayer().toString();
//        _check(play.getCurrPlayer().getMaJiangs());


        List<MaJiang> maJiangs = new ArrayList<>();
        maJiangs.add(new MaJiang("w6"));
        maJiangs.add(new MaJiang("w6"));
        maJiangs.add(new MaJiang("w7"));
        maJiangs.add(new MaJiang("w8"));

        maJiangs.add(new MaJiang("w9"));
        maJiangs.add(new MaJiang("t7"));
        maJiangs.add(new MaJiang("t7"));
        maJiangs.add(new MaJiang("t7"));

        maJiangs.add(new MaJiang("s2"));
        maJiangs.add(new MaJiang("s3"));
        maJiangs.add(new MaJiang("s4"));
        maJiangs.add(new MaJiang("bf"));

        maJiangs.add(new MaJiang("bf"));

        maJiangs.sort(new Comparator<MaJiang>() {
            public int compare(MaJiang n1, MaJiang n2) {
                return n1.getSortId() - n2.getSortId();
            }
        });

        System.out.println(Utils.checkABC(maJiangs));
    }


}

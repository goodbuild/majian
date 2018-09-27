import config.Config;
import enums.MaJiangCardEnum;
import game.GamePlayer;
import game.MaJiang;
import game.Play;

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
    static List<List<MaJiang>> lists = new ArrayList<>();

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


        List<MaJiang> noWords = new ArrayList<>();
        noWords.add(new MaJiang("w7"));
        noWords.add(new MaJiang("w7"));
        noWords.add(new MaJiang("w7"));
        noWords.add(new MaJiang("t9"));
        noWords.add(new MaJiang("t8"));
        noWords.add(new MaJiang("t7"));
        noWords.add(new MaJiang("t7"));
        noWords.add(new MaJiang("s9"));
        noWords.add(new MaJiang("s2"));
        noWords.add(new MaJiang("df"));
        noWords.add(new MaJiang("nf"));
        noWords.add(new MaJiang("bf"));
        noWords.add(new MaJiang("xf"));
        noWords.add(new MaJiang("hz"));
        noWords.add(new MaJiang("fc"));
        noWords.add(new MaJiang("bb"));

        noWords.sort(new Comparator<MaJiang>() {
            public int compare(MaJiang n1, MaJiang n2) {
                return n2.getSortId() - n1.getSortId();
            }
        });

        _check(noWords);
    }

    private static void _check(List<MaJiang> list) {
        List<MaJiang> noWords = new ArrayList<>();
        for (int i = 1; i < list.size() - 1; i++) {
            MaJiang prev = list.get(i-0);
            MaJiang curr = list.get(i + 1);

            switch (prev.getMaJiangCardEnum()) {
                case SuoZi:
                case WangZi:
                case TongZi:
                    int range = prev.getSortId() - curr.getSortId();

                    if (range == 1) {
                        noWords.add(prev);
                    } else if (noWords.size() > 0 ){
                        lists.add(noWords);
                        noWords = new ArrayList();
                    }
                    break;
                default:
                    noWords = new ArrayList();
            }
        }

        //TODO 删除日志
        System.out.println(list);
        for (List<MaJiang> l : lists) {
            System.out.print(l.size() + "   : ");
            System.out.println(l.toString());
        }

    }


}

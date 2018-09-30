import config.Config;
import enums.MaJiangCardEnum;
import exception.CanNotHuException;
import game.GamePlayer;
import game.MaJiang;
import game.Play;
import util.Utils;

import java.util.*;

/**
 * @Title: Main
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午1:08
 * @Version 1.0.0
 */
public class Main {
    public static void main(String[] args) throws Exception {
        palyTest();
    }

    static void palyTest() throws Exception{
        Play play = testBasePlay();
        GamePlayer curr = play.getCurrPlayer();
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("请 %s 出牌: ", curr.getUserId()));
        String outText = scanner.nextLine();
        boolean go = true;
        while (go) {
            curr = play.getCurrPlayer();
            MaJiang maJiang = new MaJiang(outText);
            switch (outText) {
                case "peng":
                    play.peng( null, maJiang);
                    break;
                case "gang":
                    play.gang(null, maJiang);
                    break;
                case "chi":
                    play.chi(null, maJiang);
                    break;
                case "hu":
                    play.hu(curr, maJiang);
                    go = false;
                    break;
                case "zimo":
                    play.zimo(curr, maJiang);
                    go = false;
                    break;
                default:
                    play.out(curr, maJiang);
                    play.in();

            }

            System.out.println();
            System.out.println(String.format("请 %s 出牌: ", play.getCurrPlayer().getUserId()));
            outText = scanner.nextLine();
        }


    }

    static Play testBasePlay() throws Exception{
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

        play.toString();

        return play;
    }


    static void testHu() throws Exception {
        List<MaJiang> maJiangs = new ArrayList<>();
        maJiangs.add(new MaJiang("w6"));
        maJiangs.add(new MaJiang("w6"));
        maJiangs.add(new MaJiang("w7"));
        maJiangs.add(new MaJiang("w8"));

        maJiangs.add(new MaJiang("w6"));
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

        // TODO 删除日志
        System.out.println();
        System.out.println("测试胡牌结果");
        System.out.println(maJiangs);
        System.out.println(Utils.findHu(maJiangs));
    }

}

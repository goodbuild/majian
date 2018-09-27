import config.Config;
import game.GamePlayer;
import game.MaJiang;
import game.Play;

import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);

        String one = scanner.next();
        play.out(play.getCurrPlayer() , new MaJiang(one));

        while (true) {
            System.out.println();
            System.out.println("----------------------------------");
            play.in();
            play.getCurrPlayer().toString();
            String out = scanner.next();
            play.out(play.getCurrPlayer() , new MaJiang(out));
        }

    }
}

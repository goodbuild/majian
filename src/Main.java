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

        Play brpg = PlayFactory.createBasePlay(8);

        brpg.addGamePlayer(g1);
        brpg.addGamePlayer(g2);
        brpg.addGamePlayer(g3);
        brpg.addGamePlayer(g4);

        brpg.faPai();

        brpg.toString();

        Scanner scanner = new Scanner(System.in);

        String one = scanner.next();
        brpg.out(brpg.getCurrPlayer() , new MaJiang(one));

        while (true) {
            System.out.println();
            System.out.println("----------------------------------");
            brpg.in();
            brpg.getCurrPlayer().toString();
            String out = scanner.next();
            brpg.out(brpg.getCurrPlayer() , new MaJiang(out));
        }

    }
}

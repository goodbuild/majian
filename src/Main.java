import game.BRPG;
import game.GamePlayer;

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

        BRPG brpg = new BRPG();

        brpg.addGamePlayer(g1);
        brpg.addGamePlayer(g2);
        brpg.addGamePlayer(g3);
        brpg.addGamePlayer(g4);

        brpg.faPai();

        brpg.toString();

    }
}

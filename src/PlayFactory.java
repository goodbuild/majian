import game.Play;
import game.baseMajiang.BasePlayImpl;

public class PlayFactory {

    /**
     * 基础玩法
     * @return
     */
    public static Play createBasePlay(int maxNumberByGame) {
        return new BasePlayImpl(maxNumberByGame);
    }
}

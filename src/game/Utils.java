package game;

import java.util.*;

/**
 * @Title: Utils
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午2:10
 * @Version 1.0.0
 */
public class Utils {
    public static List<MaJiang> initFeiZiMaJiang() {
        List<MaJiang> list = new ArrayList<MaJiang>();

        list.addAll(initMaJiangList(MaJiangCard.WangZi)); // 万
        list.addAll(initMaJiangList(MaJiangCard.SuoZi)); // 索 、条
        list.addAll(initMaJiangList(MaJiangCard.TiaoZi)); // 筒
        // list.addAll(initMaJiangList("w")); // 花
        return list;
    }


    public static List<MaJiang> initZiMaJiang() {
        List<MaJiang> list = new ArrayList<MaJiang>();
        MaJiangCard[] ziList = new MaJiangCard[]{MaJiangCard.Dong, MaJiangCard.Nan, MaJiangCard.Xi, MaJiangCard.Bei,
                MaJiangCard.HongZhong, MaJiangCard.FaCai, MaJiangCard.BaiBan};
        for (MaJiangCard MaJiangCard : ziList) {
            MaJiang maJiang = new MaJiang(MaJiangCard);
            list.add(maJiang);
            list.add(maJiang);
            list.add(maJiang);
            list.add(maJiang);
        }
        return list;
    }

    private static List<MaJiang> initMaJiangList(MaJiangCard MaJiangCard) {
        List<MaJiang> list = new ArrayList<MaJiang>();
        for (int i=1; i<10; i++) {
            MaJiang maJiang = new MaJiang(MaJiangCard, i);
            list.add(maJiang);
            list.add(maJiang);
            list.add(maJiang);
            list.add(maJiang);
        }

        return list;
    }
}

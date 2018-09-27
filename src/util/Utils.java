package util;

import game.MaJiang;
import enums.MaJiangCardEnum;

import java.util.ArrayList;
import java.util.List;

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

        list.addAll(initMaJiangList(MaJiangCardEnum.WangZi)); // 万
        list.addAll(initMaJiangList(MaJiangCardEnum.SuoZi)); // 索 、条
        list.addAll(initMaJiangList(MaJiangCardEnum.TongZi)); // 筒
        // list.addAll(initMaJiangList("w")); // 花
        return list;
    }


    public static List<MaJiang> initZiMaJiang() {
        List<MaJiang> list = new ArrayList<MaJiang>();
        MaJiangCardEnum[] ziList = new MaJiangCardEnum[]{MaJiangCardEnum.Dong, MaJiangCardEnum.Nan, MaJiangCardEnum.Xi, MaJiangCardEnum.Bei,
                MaJiangCardEnum.HongZhong, MaJiangCardEnum.FaCai, MaJiangCardEnum.BaiBan};
        for (MaJiangCardEnum MaJiangCard : ziList) {
            MaJiang maJiang = new MaJiang(MaJiangCard);
            list.add(maJiang);
            list.add(maJiang);
            list.add(maJiang);
            list.add(maJiang);
        }
        return list;
    }

    private static List<MaJiang> initMaJiangList(MaJiangCardEnum MaJiangCard) {
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

package game;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: MaJiangCard
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午4:41
 * @Version 1.0.0
 */
public enum MaJiangCard {
    WangZi("万", "w"),
    TiaoZi("筒", "t"),
    SuoZi("索", "s"),
    Dong("东风", "D"),
    Nan("南风", "N"),
    Xi("西风", "X"),
    Bei("北风", "B"),
    HongZhong("红中", "hz"),
    FaCai("发财", "fc"),
    BaiBan("白板", "bb"),
    CunHua("春", "ch"),
    XiaHua("夏", "xh"),
    QiuHua("秋", "qh"),
    DongHua("冬", "dh");

    private String cName;
    private String name;
    private static Map<String, MaJiangCard> map = null;

    MaJiangCard(String cName, String name) {
        this.cName = cName;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getcName() {
        return cName;
    }
    
    public static MaJiangCard valueOfName(String name) {
        if (map == null) {
            map = new HashMap<String, MaJiangCard>();

            for (MaJiangCard MaJiangCard: MaJiangCard.values()) {
                map.put(MaJiangCard.name, MaJiangCard);
            }
        }

        return map.get(name);
    }
}

package enums;

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
public enum MaJiangCardEnum {
    WangZi("万", "w", 1),
    TongZi("筒", "t", 3),
    SuoZi("索", "s", 5),
    Dong("东风", "df", 7),
    Nan("南风", "nf", 8),
    Xi("西风", "xf", 9),
    Bei("北风", "bf", 10),
    HongZhong("红中", "hz", 11),
    FaCai("发财", "fc", 12),
    BaiBan("白板", "bb", 13),
    CunHua("春", "cun", 14),
    XiaHua("夏", "xia", 15),
    QiuHua("秋", "qiu", 16),
    DongHua("冬", "dong", 17);

    private String cName;
    private String name;
    private int sort;
    private static Map<String, MaJiangCardEnum> map = null;

    MaJiangCardEnum(String cName, String name, int sort) {
        this.cName = cName;
        this.name = name;
        this.sort = sort;
    }

    public String getName() {
        return this.name;
    }

    public String getcName() {
        return cName;
    }

    public int getSort(Integer num) {
        return num != null? sort * 10 + num: sort * 10;
    }

    public int getSort() {
        return getSort(null);
    }

    public static MaJiangCardEnum valueOfName(String name) {
        if (map == null) {
            map = new HashMap<String, MaJiangCardEnum>();

            for (MaJiangCardEnum MaJiangCard: MaJiangCardEnum.values()) {
                map.put(MaJiangCard.name, MaJiangCard);
            }
        }

        return map.get(name);
    }
}

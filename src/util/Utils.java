package util;

import config.Config;
import exception.CanNotChiException;
import exception.CanNotHuException;
import exception.IsPengException;
import game.MaJiang;
import enums.MaJiangCardEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: Utils
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午2:10
 * @Version 1.0.0
 */
public class Utils {
    public static List<MaJiang> initNoWordMaJiang() {
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

    public static List<List<MaJiang>> findABC(List<MaJiang> list) {
        List<List<MaJiang>> lists = new ArrayList<>();
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

        return lists;
    }

    /**
     * 找出可以吃的牌 包括 ?BC? 和 A?B
     * @param list
     * @return
     */
    public static List<MaJiang> findABCHu(List<MaJiang> list) {
        List<List<MaJiang>> lists = new ArrayList<>();
        List<MaJiang> noWords = new ArrayList<>();
        List<MaJiang> chiList = new ArrayList<>();

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
                    } else if (range == 2) {
                        chiList.add(new MaJiang(curr.getMaJiangCardEnum(), curr.getNum() + 1));
                    } else if (noWords.size() > 0 ){
                        lists.add(noWords);
                        noWords = new ArrayList();
                    }
                    break;
                default:
                    noWords = new ArrayList();
            }
        }

        return chiList;
    }


    /**
     * OK 的
     * 找出可以吃的牌 包括 ?BC? 和 A?B
     * @param list
     * @return
     */
    public static List<MaJiang> fingChi(List<MaJiang> list) throws CanNotChiException {
        List<MaJiang> chiList = new ArrayList<>();

        if (list.size() < 2) { // 单张就不算了
            throw new CanNotChiException();
        }

        for (int i = 0; i < list.size() - 1; i++) {
            try {
                chiList.addAll(_getChi(list.get(i), list.get(i + 1)));
            } catch (Exception e) {
                continue;
            }
        }

        if (chiList.size() == 0) {
            throw new CanNotChiException();
        }

        return chiList;
    }

    private static List<MaJiang> _getChi(MaJiang oneMaJiang, MaJiang twoMaJiang) throws CanNotChiException, IsPengException {
        List<MaJiang> chiList = new ArrayList<>();
        MaJiangCardEnum maJiangCardEnum = oneMaJiang.getMaJiangCardEnum();
        int oneNum = oneMaJiang.getNum();
        int twoNum = twoMaJiang.getNum();
        int range = oneNum - twoNum;

        if (range > -2) {
            throw new CanNotChiException();
        }

        if (range == 0) {
            throw new IsPengException();
        }

        if (range == -1) { // ?BC?
            if (oneNum != 1) { // 头不取 1
                chiList.add(new MaJiang(maJiangCardEnum, oneNum - 1));
            }
            if (twoNum != 9) { // 尾不取9
                chiList.add(new MaJiang(maJiangCardEnum, twoNum + 1));
            }
        } else if (range == -2) { // A?B
            chiList.add(new MaJiang(maJiangCardEnum, oneNum + 1));
        }

        return chiList;
    }

    public static List<MaJiang> findHu(List<MaJiang> list) throws CanNotHuException {
        Map<MaJiang, Boolean> huMap = new HashMap<>();
        List<MaJiang> huList = new ArrayList<>();

        // 单张
        int num = list.size();
        if (num == 1) {
            return list;
        }

        // 2 张
        if (num == 2) {
            try {
                huList.addAll(_getChi(list.get(0), list.get(1)));
            } catch (CanNotChiException e) {
                throw new CanNotHuException();
            } catch (IsPengException e) {
                huList.add(list.get(0));
            }
            return huList;
        }

        // 2 张以上
        Map<MaJiang, Integer> map = new HashMap<>();
        List<MaJiang> pengList = new ArrayList<>();
        List<MaJiang> gangList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            MaJiang maJiang = list.get(i);
            Integer count = map.get(maJiang) != null ? map.get(maJiang) + 1 : 0;

            if (count == Config.GANG_NUM) {
                pengList.add(maJiang);
            } else if (count == Config.PENG_NUM) {
                pengList.remove(maJiang);
                gangList.add(maJiang);
            }

            map.put(maJiang, count);
        }

        if (pengList.size() > 3) {
            throw new CanNotHuException();
        } else if (pengList.size() == 2) {
            for (MaJiang maJiang : pengList) {
                list.remove(maJiang);
                list.remove(maJiang);
            }

            if (list.size() == 0) {// 剩下两对
                return pengList;
            }


            try {
                List<MaJiang> chiList = fingChi(list);

                for (MaJiang chi : chiList) {
                    list.remove(chi);
                }
            } catch (CanNotChiException e) {
            }

            if (list.size() != 0) {
                throw new CanNotHuException();
            }

            return pengList;
        } else if (pengList.size() == 1 && gangList.size() > 1) {
            //有可能听这两对，和其他
            MaJiang peng = pengList.get(0);
            list.remove(peng);
            list.remove(peng);

            for (MaJiang gang : gangList) {
                list.remove(peng);
                list.remove(peng);
                list.remove(peng);
            }

            List<MaJiang> chiList = new ArrayList<>();
            try {
                chiList = fingChi(list);
                for (MaJiang chi : list) {
                    chiList.remove(chi);
                }
            } catch (CanNotChiException e) {
            }

            if (chiList.size() )

            return chiList;
        } else {

        }

        return (List<MaJiang>) huMap.keySet();
    }
}

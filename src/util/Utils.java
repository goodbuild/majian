package util;

import config.Config;
import exception.CanNotChiException;
import exception.CanNotHuException;
import exception.IsGangException;
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
                chiList.addAll(_getChi(list.get(i), list.get(i + 1), null));
            } catch (Exception e) {
                continue;
            }
        }

        if (chiList.size() == 0) {
            throw new CanNotChiException();
        }

        return chiList;
    }

    /**
     * 主要判断前两张牌是否可以吃，
     * @param oneMaJiang
     * @param twoMaJiang
     * @param threeMaJiang
     * @return
     * @throws CanNotChiException
     * @throws IsPengException
     * @throws IsGangException
     */
    private static List<MaJiang> _getChi(MaJiang oneMaJiang, MaJiang twoMaJiang, MaJiang threeMaJiang) throws CanNotChiException, IsPengException, IsGangException {
        List<MaJiang> chiList = new ArrayList<>();

        MaJiangCardEnum maJiangCardEnum = oneMaJiang.getMaJiangCardEnum();
        int oneNum = oneMaJiang.getSortId();
        int twoNum = twoMaJiang.getSortId();
        int threeNum = threeMaJiang == null ? 0 : threeMaJiang.getSortId();

        int oneRange = oneNum - twoNum;
        int twoRane = twoNum - threeNum;

        switch (oneRange) {
            case Config.RANGE_BC:
                if (oneNum != 1) { // 头不取 1
                    chiList.add(new MaJiang(maJiangCardEnum, oneNum - 1));
                }
                if (twoNum != 9) { // 尾不取9
                    chiList.add(new MaJiang(maJiangCardEnum, twoNum + 1));
                }
                break;
            case Config.RANGE_AC:
                chiList.add(new MaJiang(maJiangCardEnum, oneNum + 1));
                break;
            case Config.RANGE_PENG:
                if (twoRane == Config.RANGE_GANG) {
                    throw new IsGangException();
                } else {
                    throw new IsPengException();
                }
            default:
                throw new CanNotChiException();

        }

        return chiList;
    }

    public static List<MaJiang> findHu(List<MaJiang> list) throws CanNotHuException {
        // 单张
        int num = list.size();
        if (num == 1) {
            return list;
        }

        System.out.println(list);
        List<MaJiang> huList = new ArrayList<>();
        int pengNum = 0;
        int checkout = 0;
        for (int i = 0; i < list.size()-1;) {
            MaJiang one = list.get(i);
            MaJiang two = list.get(i+1);
            MaJiang three = null;

            try{
                three = list.get(i+2);
            } catch (Exception e) {
            }

            try {
                if (one.getSortId() - three.getSortId() != Config.RANGE_AC) {// 本身不是ABC,但这里有个bug 就是 23456 这种糊147 会变成 47
                    huList.addAll(_getChi(one, two, three));
                    checkout++;
                    i++;
                } else {
                    i = i + 3;
                }
            } catch (CanNotChiException e) {
                huList.add(one);
                i++;
            } catch (IsPengException e) {
                pengNum++;
                huList.add(one);
                i = i +2;
            } catch (IsGangException e) {
                i = i + 3;
            }

            if (checkout > 3 || pengNum > 2) {
                throw new CanNotHuException();
            }
        }

        return huList;
    }
}

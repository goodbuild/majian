package game;

import enums.MaJiangCardEnum;

/**
 * @Title: MaJiang
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午1:28
 * @Version 1.0.0
 */
public class MaJiang {
    private String card;
    private MaJiangCardEnum maJiangCardEnum;
    private Integer num;
    private int sortId;

    public MaJiang(String card) {
        String[] strs = card.split("\\d");
        this.maJiangCardEnum = MaJiangCardEnum.valueOfName(strs[0]);
        this.card = card;
        try{
            this.num = Integer.parseInt(card.replaceAll(maJiangCardEnum.getName(), ""));
            this.sortId = maJiangCardEnum.getSort(num);
        } catch (Exception e) {

        }
    }

    public MaJiang(MaJiangCardEnum maJiangCard) {
        this.maJiangCardEnum = maJiangCard;
        this.card = maJiangCard.getName();
        this.sortId = maJiangCardEnum.getSort();
    }

    public MaJiang(MaJiangCardEnum maJiangCard, int num) {
        this.maJiangCardEnum = maJiangCard;
        this.card = maJiangCard.getName() + num;
        this.num = num;
        this.sortId = maJiangCardEnum.getSort(null);
    }

    public MaJiang(MaJiangCardEnum maJiangCard, int num, int sortId) {
        this.maJiangCardEnum = maJiangCard;
        this.card = maJiangCard.getName() + num;
        this.num = num;
        this.sortId = sortId;
    }

    public String getCard() {
        return card;
    }

    public int getSortId() {
        return sortId;
    }

    public MaJiangCardEnum getMaJiangCardEnum() {
        return maJiangCardEnum;
    }

    @Override
    public String toString() {
        return String.format("%s%s (%s)",
                this.card.replace(this.maJiangCardEnum.getName(), ""),
                this.maJiangCardEnum.getcName(),
                this.card
                );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            return this.card.equals(obj);
        }

        MaJiang _obj = (MaJiang)obj;
        return this.card.equals(_obj.card);
    }
}

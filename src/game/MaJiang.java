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
    private int sortId = 0;

    public MaJiang(String card) {
        String[] strs = card.split("\\d");
        this.maJiangCardEnum = MaJiangCardEnum.valueOfName(strs[0]);
        this.card = card;
        try{
            this.sortId = Integer.parseInt(card.replaceAll(maJiangCardEnum.getName(), ""));
        } catch (Exception e) {

        }
    }

    public MaJiang(MaJiangCardEnum maJiangCard) {
        this.maJiangCardEnum = maJiangCard;
        this.card = maJiangCard.getName();
    }

    public MaJiang(MaJiangCardEnum maJiangCard, int num) {
        this.maJiangCardEnum = maJiangCard;
        this.card = maJiangCard.getName() + num;
        this.sortId = num;
    }

    public MaJiang(MaJiangCardEnum maJiangCard, int num, int sortId) {
        this.maJiangCardEnum = maJiangCard;
        this.card = maJiangCard.getName() + num;
        this.sortId = sortId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
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

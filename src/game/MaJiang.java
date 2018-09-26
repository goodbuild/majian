package game;

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
    private int sortId = 0;

    public MaJiang(MaJiangCard maJiangCard) {
        this.card = maJiangCard.getName();
    }

    public MaJiang(MaJiangCard maJiangCard, int num) {
        this.card = maJiangCard.getName() + num;
        this.sortId = num;
    }

    public MaJiang(MaJiangCard maJiangCard, int num, int sortId) {
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

    @Override
    public String toString() {
        String[] strs = this.card.split("\\d");
        MaJiangCard maJiangCard = MaJiangCard.valueOfName(strs[0]);
        return String.format("%s%s", this.card.replace(strs[0], ""), maJiangCard.getcName());
    }

    @Override
    public boolean equals(Object obj) {
        MaJiang _obj = (MaJiang)obj;
        return this.card.equals(_obj.card);
    }
}

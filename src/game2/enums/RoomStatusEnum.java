package game2.enums;

/**
 * @Title: RoomStatus
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午2:49
 * @Version 1.0.0
 */
public enum RoomStatusEnum {
    WaitPlayer("等待玩家"), WaitFaPai("等待发牌"),Palying("玩牌中"), End("清盘");

    private String name;

    RoomStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

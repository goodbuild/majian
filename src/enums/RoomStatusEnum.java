package enums;

/**
 * @Title: RoomStatus
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午2:49
 * @Version 1.0.0
 */
public enum RoomStatusEnum {
    WaitPlayerJoin("等待玩家加入"),
    WaitFaPai("等待发牌"),
    Playing("玩牌中"),
    Wait("等待"),
    Win("有人赢了");

    private String name;

    RoomStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

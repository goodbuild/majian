package game2.enums;

public enum PlayRuleEnum {
    peng("碰"), gang("杠"), chi("吃"), hu("糊"), zimo("自摸");

    private final String name;

    PlayRuleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

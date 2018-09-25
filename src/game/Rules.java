package game;

/**
 * @Title: 玩法接口
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午1:14
 * @Version 1.0.0
 */
interface Rules {
    public void faPai() throws Exception;

    public boolean peng();

    public boolean gang();

    public boolean hu();

    public boolean zimo();

    public boolean chi();
}
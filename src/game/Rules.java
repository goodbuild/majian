package game;

import exception.CanNotChiException;
import exception.CanNotHuException;
import exception.NotExistentException;

import java.util.List;

/**
 * @Title: 规则接口
 * @ProjectName MaJiang
 * @Description:
 * @Author xuelong
 * @Date 2018/9/25下午1:14
 * @Version 1.0.0
 */
public interface Rules {
    public void peng(GamePlayer currGamePalyer, MaJiang maJiang) throws NotExistentException;

    public void gang(GamePlayer currGamePalyer, MaJiang maJiang) throws NotExistentException;

    public void hu(GamePlayer currGamePalyer, MaJiang maJiang) throws CanNotHuException;

    public void zimo(GamePlayer currGamePalyer, MaJiang maJiang) throws CanNotHuException;

    public void chi(GamePlayer currGamePalyer, MaJiang maJiang) throws CanNotChiException;

    public List<MaJiang> ting(List<MaJiang> maJiangs) throws CanNotHuException;
}
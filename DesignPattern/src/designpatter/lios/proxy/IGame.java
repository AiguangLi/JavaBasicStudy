package designpatter.lios.proxy;

/**
 * @author liaiguang
 */
public interface IGame {
    /**
     * 代理登录
     * @param account
     * @param password
     */
    void login(String account, String password);

    /**
     * 玩游戏
     */
    void playGame();

    /**
     * 升级
     */
    void upgrade();
}

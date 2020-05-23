package designpatter.lios.proxy;


/**
 * @author liaiguang
 */
public class GamePlayer implements IGame {
    String account;
    String password;
    String username;

    private IGame proxy;

    public void setProxy(IGame _proxy) {
        proxy = _proxy;
    }

    public GamePlayer(String _account, String _password) {
        account = _account;
        password = _password;
    }

    @Override
    public void login(String account, String password) {
        if (null == proxy) {
            System.out.println("不知道怎么登录，需要代理帮忙！");
        } else {
            proxy.login(account, password);
        }
    }

    @Override
    public void playGame() {
        if (null == proxy) {
            System.out.println("不知道怎么玩游戏，需要代理帮忙！");
        } else {
            proxy.playGame();
        }
    }

    @Override
    public void upgrade() {
        if (null == proxy) {
            System.out.println("不知道怎么升级，需要代理帮忙！");
        } else {
            proxy.upgrade();
        }
    }
}

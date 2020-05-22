package com.lios.proxy;

/**
 * @author liaiguang
 */
public class Example {
    public static void main(String[] args) {
        GamePlayer player = new GamePlayer("user", "123456");
        GameProxy proxy = new GameProxy();
        // 设置代理
        player.setProxy(proxy);
        // 实际上所有的操作由代理完成
        // 在实际应用中，当对象的某些操作无法自己完成时，可以交给代理完成
        // 典型的场景如列表视图因为没有持有数据，并不知道怎么显示，这时候可以由代理控制列表数据（如数量，第几行显示的数据），而视图只需要管UI绘制即可
        player.login(player.account, player.password);
        player.playGame();
        player.upgrade();
    }
}

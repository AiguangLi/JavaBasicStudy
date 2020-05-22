package com.lios.proxy;

/**
 * @author liaiguang
 */
public class GameProxy implements IGame {

    @Override
    public void login(String account, String password) {
        System.out.println("使用" + account + "登录，玩家名称为张山。");
    }

    @Override
    public void playGame() {
        System.out.println( "玩游戏中...");
    }

    @Override
    public void upgrade() {
        System.out.println("升级啦!");
    }
}

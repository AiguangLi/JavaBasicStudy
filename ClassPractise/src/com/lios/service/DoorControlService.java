package com.lios.service;

/**
 * @author liaiguang
 */
public interface DoorControlService {
    /**
     * 控制门禁开门
     * @param serialNumber 门禁厂商唯一编号
     * @return boolean true = 成功， false = 失败
     */
    boolean open(String serialNumber);

    /**
     * 控制门禁关门
     * @param serialNumber 门禁厂商唯一编号
     * @return boolean true = 成功， false = 失败
     */
    boolean close(String serialNumber);

    /**
     * 获取门禁是否在线
     * @param serialNumber 门禁厂商唯一编号
     * @return boolean true = 在线， false = 离线
     */
    default boolean isOnline(String serialNumber) {
        return true;
    }

    /**
     * 检测门是否打开
     * @param serialNumber 门禁厂商唯一编号
     * @return boolean true = 打开， false = 关闭
     */
    default boolean isOpen(String serialNumber) {
        return false;
    }

    /**
     * 获取实现类的接口地址
     * @return String api接口地址
     */
    String getApiPath();

    /**
     * 获取实现类的厂商序列号
     * @return String 门禁厂商唯一序列号
     */
    String getSerialNum();
}

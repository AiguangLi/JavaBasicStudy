package com.lios.impl;

import com.lios.service.DoorControlService;

/**
 * @author liaiguang
 */
public class DigDoorControlImpl implements DoorControlService {
    String deviceName = "";
    String serialNumber = "";
    String apiAddress = "http://192.168.1.6:8081/dig/";

    public DigDoorControlImpl(String deviceName, String serialNumber) {
        this.deviceName = deviceName;
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean open(String serialNumber) {
        String fullApiPath = getApiPath() + "open";
        System.out.println("Dig: Send request to " + deviceName + " " + serialNumber + " to open door. \n" + fullApiPath);

        return true;
    }

    @Override
    public boolean close(String serialNumber) {
        String fullApiPath = getApiPath() + "close";
        System.out.println("Dig: Send request to " + deviceName + " " + serialNumber + " to close door.\n" + fullApiPath);

        return true;
    }

    @Override
    public String getApiPath() {
        return apiAddress;
    }

    @Override
    public String getSerialNum() {
        return serialNumber;
    }
}

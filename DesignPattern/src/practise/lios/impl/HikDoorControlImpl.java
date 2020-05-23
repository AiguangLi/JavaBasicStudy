package practise.lios.impl;

import practise.lios.service.DoorControlService;

/**
 * @author liaiguang
 */
public class HikDoorControlImpl implements DoorControlService {
    String deviceName = "";
    String serialNumber = "";
    String apiAddress = "http://192.168.1.5:8080/hik/";
    boolean doorIsOpen = false;

    public HikDoorControlImpl(String deviceName, String serialNumber) {
        this.deviceName = deviceName;
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean open(String serialNumber) {
        String fullApiPath = getApiPath() + "open";
        System.out.println("Hik: Send request to " + deviceName + " " + serialNumber + " to open door. \n" + fullApiPath);
        doorIsOpen = true;

        return true;
    }

    @Override
    public boolean close(String serialNumber) {
        String fullApiPath = getApiPath() + "close";
        System.out.println("Hik: Send request to " + deviceName + " " + serialNumber + " to close door.\n" + fullApiPath);
        doorIsOpen = false;

        return true;
    }

    @Override
    public boolean isOnline(String serialNumber) {
        String fullApiPath = getApiPath() + "checkOnline";
        System.out.println("Hik: Send request to "  + deviceName + " " + serialNumber + " to check online status.\n" + fullApiPath);

        return true;
    }

    @Override
    public boolean isOpen(String serialNumber) {
        return doorIsOpen;
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

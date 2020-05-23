package practise.lios;

import practise.lios.impl.DigDoorControlImpl;
import practise.lios.impl.HikDoorControlImpl;
import practise.lios.service.DoorControlService;

/**
 * @author liaiguang
 */
public class DoorController {
    DoorControlService doorControl;

    /**
     * 依赖注入service，由service控制
     * @param doorControl DoorControl接口具体实现类
     */
    public DoorController(DoorControlService doorControl) {
        this.doorControl = doorControl;
    }

    public void openDoor() {
        boolean open = doorControl.open(doorControl.getSerialNum());
        System.out.println(doorControl.getSerialNum() + " open " + (open ? "success" : "failed"));
    }

    public void closeDoor() {
        boolean close = doorControl.close(doorControl.getSerialNum());
        System.out.println(doorControl.getSerialNum() + " close " + (close ? "success" : "failed"));
    }

    public void checkOnline() {
        System.out.println("Door is online: " + doorControl.isOnline(doorControl.getSerialNum()));
    }

    public void checkDoorOpen() {
        System.out.println("Door is open: " + doorControl.isOpen(doorControl.getSerialNum()));
    }

    public static void main(String[] args) {
        HikDoorControlImpl hikDoorControl = new HikDoorControlImpl("人脸识别机603", "389851");

        DoorController doorController = new DoorController(hikDoorControl);
        doorController.openDoor();
        doorController.checkDoorOpen();
        doorController.closeDoor();
        doorController.checkDoorOpen();
        doorController.checkOnline();

        DigDoorControlImpl digDoorControl = new DigDoorControlImpl("10吋门口机", "1243535");
        DoorController doorController1 = new DoorController(digDoorControl);
        doorController1.openDoor();
        doorController1.checkDoorOpen();
        doorController1.closeDoor();
        doorController1.checkDoorOpen();
        doorController1.checkOnline();
    }
}

package edu.ucalgary.oop;

public class SmartLock extends SmartDevice<Boolean> {
    public SmartLock() {
        super();
        setState(true); // Default state: LOCKED
    }

    @Override
    public void performAction() {
        if (getState()) {
            System.out.println("SmartLock is LOCKED. Securing the door.");
        } else {
            System.out.println("SmartLock is UNLOCKED. Door is open.");
        }
    }

    @Override
    public void update(String message) {
        switch (message) {
            case "Sleep":
                setState(true); // Lock the door in sleep mode
                break;
            case "Vacation":
                setState(true); // Lock the door in vacation mode
                break;
            default:
                System.out.println("SmartLock: Unknown message - " + message);
        }
    }

    public void autoLock(int delayInSeconds) {
        System.out.println("Auto-lock enabled. Door will lock in " + delayInSeconds + " seconds.");
        new Thread(() -> {
            try {
                Thread.sleep(delayInSeconds * 1000L);
                setState(true);
                System.out.println("Door auto-locked.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

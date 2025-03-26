package edu.ucalgary.oop;

public class SmartLight extends SmartDevice<Boolean> {
    public SmartLight() {
        super();
        setState(false); // Default state: OFF
    }

    @Override
    public void performAction() {
        if (getState()) {
            System.out.println("SmartLight is ON. Brightening the room.");
        } else {
            System.out.println("SmartLight is OFF. Room is dark.");
        }
    }

    @Override
    public void update(String message) {
        switch (message) {
            case "Sleep":
                setState(false);
                break;
            case "Vacation":
                setState(Math.random() < 0.5); // Randomly turn ON/OFF
                break;
            default:
                System.out.println("SmartLight: Unknown message - " + message);
        }
    }

    public void dim(int percentage) {
        if (getState()) {
            System.out.println("Dimming the light to " + percentage + "%.");
        } else {
            System.out.println("Cannot dim the light. It is currently OFF.");
        }
    }
}

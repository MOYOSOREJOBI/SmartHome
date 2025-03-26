package edu.ucalgary.oop;

public class SmartThermostat extends SmartDevice<Integer> {
    public SmartThermostat() {
        super();
        setState(20); // Default state: 20°C
    }

    @Override
    public void performAction() {
        System.out.println("Thermostat is set to " + getState() + "°C. Adjusting temperature.");
    }

    @Override
    public void update(String message) {
        switch (message) {
            case "Sleep":
                setState(18); // Lower temperature for sleep
                break;
            case "Vacation":
                setState(20); // Moderate temperature for vacation
                break;
            default:
                System.out.println("SmartThermostat: Unknown message - " + message);
        }
    }

    public void adjustTemperature(int desiredTemp) {
        int currentTemp = getState();
        int difference = desiredTemp - currentTemp;

        if (difference > 0) {
            System.out.println("Increasing temperature by " + difference + "°C.");
        } else if (difference < 0) {
            System.out.println("Decreasing temperature by " + Math.abs(difference) + "°C.");
        } else {
            System.out.println("Temperature is already at the desired level.");
        }

        setState(desiredTemp);
    }
}

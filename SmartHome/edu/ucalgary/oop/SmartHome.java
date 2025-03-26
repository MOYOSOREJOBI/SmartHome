package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;

public class SmartHome {
    private List<SmartDevice<?>> devices = new ArrayList<>();
    private boolean isBuilt = false;

    // Add a device (allowed before build)
    public SmartHome addDevice(SmartDevice<?> device) {
        if (isBuilt) {
            throw new IllegalStateException("Cannot add devices after the SmartHome is built.");
        }
        devices.add(device);
        return this;
    }

    // Build the SmartHome (finalizes the setup)
    public SmartHome build() {
        if (devices.isEmpty()) {
            throw new IllegalStateException("No devices registered. Add at least one device before building.");
        }
        isBuilt = true;
        return this;
    }

    // Set device state (only allowed after build)
    public <T> void setDeviceState(SmartDevice<T> device, T state) {
        if (!isBuilt) {
            throw new IllegalStateException("SmartHome must be built before setting device states.");
        }
        if (!devices.contains(device)) {
            throw new IllegalArgumentException("Device is not registered in this SmartHome.");
        }
        device.setState(state);
    }

    // Send overarching messages (only allowed after build)
    public void sendMessage(String message) {
        if (!isBuilt) {
            throw new IllegalStateException("SmartHome must be built before sending messages.");
        }
        System.out.println("SmartHome: Sending message - " + message);
        for (SmartDevice<?> device : devices) {
            device.update(message);
        }
    }
}

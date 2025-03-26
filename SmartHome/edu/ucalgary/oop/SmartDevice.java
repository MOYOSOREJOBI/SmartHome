package edu.ucalgary.oop;

public abstract class SmartDevice<T> implements Observer {
    private T state;

    // Default state is set to null (can be overridden in child classes)
    public SmartDevice() {
        this.state = null; // Default state
    }

    public void setState(T state) {
        this.state = state;
        performAction();
    }

    public T getState() {
        return state;
    }

    public abstract void performAction();
}

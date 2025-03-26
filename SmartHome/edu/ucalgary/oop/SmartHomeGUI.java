package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SmartHomeGUI extends JFrame implements ActionListener {
    // Smart Home components
    private SmartHome smartHome;
    private SmartLight light;
    private SmartLock lock;
    private SmartThermostat thermostat;

    // GUI components
    private JLabel lightStatusLabel;
    private JButton lightOnButton;
    private JButton lightOffButton;

    private JLabel thermostatStatusLabel;
    private JTextField thermostatTextField;
    private JButton setThermostatButton;

    private JLabel lockStatusLabel;
    private JButton lockButton;
    private JButton unlockButton;

    private JButton sleepModeButton;
    private JButton vacationModeButton;

    public SmartHomeGUI() {
        // Initialize Smart Home System
        initializeSmartHome();
        
        // Set up JFrame properties
        setTitle("Smart Home Control System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));
        
        // Create panels for each device type
        JPanel lightPanel = createLightPanel();
        JPanel thermostatPanel = createThermostatPanel();
        JPanel lockPanel = createLockPanel();
        JPanel modePanel = createModePanel();
        
        // Add panels to the frame
        add(lightPanel);
        add(thermostatPanel);
        add(lockPanel);
        add(modePanel);
    }

    private void initializeSmartHome() {
        // Create devices
        light = new SmartLight();
        thermostat = new SmartThermostat();
        lock = new SmartLock();
        
        // Create and build the smart home
        smartHome = new SmartHome()
                .addDevice(light)
                .addDevice(thermostat)
                .addDevice(lock)
                .build();
    }

    private JPanel createLightPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Smart Light Control"));
        panel.setLayout(new FlowLayout());
        
        // Create components
        lightStatusLabel = new JLabel("Light is OFF");
        lightOnButton = new JButton("Turn ON");
        lightOffButton = new JButton("Turn OFF");
        
        // Add action listeners
        lightOnButton.addActionListener(this);
        lightOffButton.addActionListener(this);
        
        // Add components to panel
        panel.add(lightStatusLabel);
        panel.add(lightOnButton);
        panel.add(lightOffButton);
        
        // Update light status display
        updateLightStatus();
        
        return panel;
    }

    private JPanel createThermostatPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Smart Thermostat Control"));
        panel.setLayout(new FlowLayout());
        
        // Create components
        thermostatStatusLabel = new JLabel("Temperature: " + thermostat.getState() + "°C");
        thermostatTextField = new JTextField(5);
        setThermostatButton = new JButton("Set Temperature");
        
        // Add action listeners
        setThermostatButton.addActionListener(this);
        
        // Add components to panel
        panel.add(thermostatStatusLabel);
        panel.add(new JLabel("New Temperature:"));
        panel.add(thermostatTextField);
        panel.add(setThermostatButton);
        
        return panel;
    }

    private JPanel createLockPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Smart Lock Control"));
        panel.setLayout(new FlowLayout());
        
        // Create components
        lockStatusLabel = new JLabel(lock.getState() ? "Door is LOCKED" : "Door is UNLOCKED");
        lockButton = new JButton("Lock Door");
        unlockButton = new JButton("Unlock Door");
        
        // Add action listeners
        lockButton.addActionListener(this);
        unlockButton.addActionListener(this);
        
        // Add components to panel
        panel.add(lockStatusLabel);
        panel.add(lockButton);
        panel.add(unlockButton);
        
        return panel;
    }

    private JPanel createModePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Mode Control"));
        panel.setLayout(new FlowLayout());
        
        // Create components
        sleepModeButton = new JButton("Sleep Mode");
        vacationModeButton = new JButton("Vacation Mode");
        
        // Add action listeners
        sleepModeButton.addActionListener(this);
        vacationModeButton.addActionListener(this);
        
        // Add components to panel
        panel.add(sleepModeButton);
        panel.add(vacationModeButton);
        
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        // Light controls
        if (source == lightOnButton) {
            smartHome.setDeviceState(light, true);
            updateLightStatus();
        } else if (source == lightOffButton) {
            smartHome.setDeviceState(light, false);
            updateLightStatus();
        }
        
        // Thermostat controls
        else if (source == setThermostatButton) {
            try {
                int temperature = Integer.parseInt(thermostatTextField.getText());
                thermostat.adjustTemperature(temperature);
                thermostatStatusLabel.setText("Temperature: " + thermostat.getState() + "°C");
                thermostatTextField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter a valid temperature (number).", 
                    "Invalid Input", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Lock controls
        else if (source == lockButton) {
            smartHome.setDeviceState(lock, true);
            updateLockStatus();
        } else if (source == unlockButton) {
            smartHome.setDeviceState(lock, false);
            updateLockStatus();
        }
        
        // Mode controls
        else if (source == sleepModeButton) {
            smartHome.sendMessage("Sleep");
            updateAllStatuses();
        } else if (source == vacationModeButton) {
            smartHome.sendMessage("Vacation");
            updateAllStatuses();
        }
    }
    
    private void updateLightStatus() {
        lightStatusLabel.setText("Light is " + (light.getState() ? "ON" : "OFF"));
    }
    
    private void updateLockStatus() {
        lockStatusLabel.setText("Door is " + (lock.getState() ? "LOCKED" : "UNLOCKED"));
    }
    
    private void updateAllStatuses() {
        updateLightStatus();
        updateLockStatus();
        thermostatStatusLabel.setText("Temperature: " + thermostat.getState() + "°C");
    }
    
    public static void main(String[] args) {
        // Use the Event Dispatch Thread for Swing applications
        EventQueue.invokeLater(() -> {
            SmartHomeGUI gui = new SmartHomeGUI();
            gui.setVisible(true);
        });
    }
}
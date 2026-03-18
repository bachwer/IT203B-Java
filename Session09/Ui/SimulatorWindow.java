package Session09.Ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main application window for the Smart Traffic Simulator.
 * Handles UI controls and integrates the TrafficPanel.
 */
public class SimulatorWindow extends JFrame {
    
    private TrafficPanel trafficPanel;
    private JButton startBtn, pauseBtn, resetBtn;
    private JComboBox<String> speedCombo;
    private JLabel statusLabel;
    private Timer statusUpdateTimer;
    
    public SimulatorWindow() {
        setTitle("🚦 SMART TRAFFIC SIMULATOR - Professional Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 850);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Create UI
        createUI();
        
        // Status update timer
        statusUpdateTimer = new Timer();
        statusUpdateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateStatus();
            }
        }, 500, 500);
        
        setVisible(true);
    }
    
    private void createUI() {
        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout(0, 10));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.setBackground(Color.WHITE);
        
        // Top control panel
        JPanel controlPanel = createControlPanel();
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        
        // Center - traffic simulation
        trafficPanel = new TrafficPanel();
        mainPanel.add(trafficPanel, BorderLayout.CENTER);
        
        // Bottom status panel
        JPanel statusPanel = createStatusPanel();
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panel.setBackground(new Color(33, 150, 243));
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        
        // Title
        JLabel titleLabel = new JLabel("🚦 Traffic Control Center");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);
        
        // Separator
        JSeparator sep1 = new JSeparator(JSeparator.VERTICAL);
        sep1.setPreferredSize(new Dimension(2, 40));
        sep1.setForeground(new Color(255, 255, 255, 100));
        panel.add(sep1);
        
        // Speed control
        JLabel speedLabel = new JLabel("Speed:");
        speedLabel.setForeground(Color.WHITE);
        speedLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(speedLabel);
        
        speedCombo = new JComboBox<>(new String[]{
            "🐌 Slow (0.5x)",
            "⚡ Normal (1.0x)",
            "🚀 Fast (2.0x)"
        });
        speedCombo.setSelectedIndex(1);
        speedCombo.setFont(new Font("Arial", Font.PLAIN, 11));
        speedCombo.addActionListener(e -> handleSpeedChange());
        panel.add(speedCombo);
        
        // Separator
        JSeparator sep2 = new JSeparator(JSeparator.VERTICAL);
        sep2.setPreferredSize(new Dimension(2, 40));
        sep2.setForeground(new Color(255, 255, 255, 100));
        panel.add(sep2);
        
        // Buttons
        startBtn = createButton("▶ START", new Color(76, 175, 80));
        startBtn.addActionListener(e -> handleStart());
        panel.add(startBtn);
        
        pauseBtn = createButton("⏸ PAUSE", new Color(255, 152, 0));
        pauseBtn.setEnabled(false);
        pauseBtn.addActionListener(e -> handlePause());
        panel.add(pauseBtn);
        
        resetBtn = createButton("🔄 RESET", new Color(244, 67, 54));
        resetBtn.addActionListener(e -> handleReset());
        panel.add(resetBtn);
        
        return panel;
    }
    
    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(120, 40));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor);
            }
        });
        
        return btn;
    }
    
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panel.setBackground(new Color(33, 150, 243));
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        panel.setPreferredSize(new Dimension(0, 50));
        
        statusLabel = new JLabel("Status: Ready | Vehicles Active: 0 | Total Generated: 0 | Crossed: 0");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setForeground(Color.WHITE);
        panel.add(statusLabel);
        
        return panel;
    }
    
    private void handleStart() {
        trafficPanel.start();
        startBtn.setEnabled(false);
        pauseBtn.setEnabled(true);
        resetBtn.setEnabled(false);
        speedCombo.setEnabled(false);
    }
    
    private void handlePause() {
        trafficPanel.pause();
        startBtn.setEnabled(true);
        pauseBtn.setEnabled(false);
        resetBtn.setEnabled(true);
        speedCombo.setEnabled(true);
    }
    
    private void handleReset() {
        trafficPanel.reset();
        startBtn.setEnabled(true);
        pauseBtn.setEnabled(false);
        resetBtn.setEnabled(true);
        speedCombo.setEnabled(true);
        speedCombo.setSelectedIndex(1);
        updateStatus();
    }
    
    private void handleSpeedChange() {
        int selectedIndex = speedCombo.getSelectedIndex();
        double speedFactor = switch (selectedIndex) {
            case 0 -> 0.5;   // Slow
            case 2 -> 2.0;   // Fast
            default -> 1.0;  // Normal
        };
        trafficPanel.setSpeed(speedFactor);
    }
    
    private void updateStatus() {
        String status = String.format(
            "Status: %s | Vehicles Active: %d | Total Generated: %d | Crossed: %d",
            "Running",
            trafficPanel.getVehicleCount(),
            trafficPanel.getTotalVehiclesGenerated(),
            trafficPanel.getVehiclesCrossed()
        );
        statusLabel.setText(status);
    }
    
    // Main entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            new SimulatorWindow();
        });
    }
}

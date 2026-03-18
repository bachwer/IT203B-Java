package Session09.Ui;

import java.awt.*;

/**
 * Manages traffic light states and phases.
 * Controls when vehicles can move in each direction.
 */
public class TrafficLightUI {
    
    // Light state
    public enum LightState {
        RED, YELLOW, GREEN
    }
    
    // Phase
    public enum Phase {
        NORTH_SOUTH_GREEN,
        NORTH_SOUTH_YELLOW,
        EAST_WEST_GREEN,
        EAST_WEST_YELLOW
    }
    
    private Phase currentPhase;
    private long phaseStartTime;
    private long lastPhaseChangeTime;
    
    // Timing (in milliseconds)
    private long GREEN_DURATION = 10000;    // 10 seconds
    private long YELLOW_DURATION = 3000;   // 3 seconds
    private long RED_DURATION = 10000;     // 10 seconds
    
    // Constructor
    public TrafficLightUI() {
        this.currentPhase = Phase.NORTH_SOUTH_GREEN;
        this.phaseStartTime = System.currentTimeMillis();
        this.lastPhaseChangeTime = phaseStartTime;
    }
    
    /**
     * Update the traffic light phase based on elapsed time
     */
    public void update() {
        long currentTime = System.currentTimeMillis();
        long phaseElapsedTime = currentTime - phaseStartTime;
        
        switch (currentPhase) {
            case NORTH_SOUTH_GREEN:
                if (phaseElapsedTime > GREEN_DURATION) {
                    currentPhase = Phase.NORTH_SOUTH_YELLOW;
                    phaseStartTime = currentTime;
                }
                break;
                
            case NORTH_SOUTH_YELLOW:
                if (phaseElapsedTime > YELLOW_DURATION) {
                    currentPhase = Phase.EAST_WEST_GREEN;
                    phaseStartTime = currentTime;
                }
                break;
                
            case EAST_WEST_GREEN:
                if (phaseElapsedTime > GREEN_DURATION) {
                    currentPhase = Phase.EAST_WEST_YELLOW;
                    phaseStartTime = currentTime;
                }
                break;
                
            case EAST_WEST_YELLOW:
                if (phaseElapsedTime > YELLOW_DURATION) {
                    currentPhase = Phase.NORTH_SOUTH_GREEN;
                    phaseStartTime = currentTime;
                }
                break;
        }
    }
    
    /**
     * Get light state for a specific direction
     */
    public LightState getLightForDirection(VehicleSprite.Direction direction) {
        switch (currentPhase) {
            case NORTH_SOUTH_GREEN:
                if (direction == VehicleSprite.Direction.NORTH || direction == VehicleSprite.Direction.SOUTH) {
                    return LightState.GREEN;
                } else {
                    return LightState.RED;
                }
                
            case NORTH_SOUTH_YELLOW:
                if (direction == VehicleSprite.Direction.NORTH || direction == VehicleSprite.Direction.SOUTH) {
                    return LightState.YELLOW;
                } else {
                    return LightState.RED;
                }
                
            case EAST_WEST_GREEN:
                if (direction == VehicleSprite.Direction.EAST || direction == VehicleSprite.Direction.WEST) {
                    return LightState.GREEN;
                } else {
                    return LightState.RED;
                }
                
            case EAST_WEST_YELLOW:
                if (direction == VehicleSprite.Direction.EAST || direction == VehicleSprite.Direction.WEST) {
                    return LightState.YELLOW;
                } else {
                    return LightState.RED;
                }
                
            default:
                return LightState.RED;
        }
    }
    
    /**
     * Draw all traffic lights
     */
    public void draw(Graphics2D g, int centerX, int centerY) {
        int lightSize = 20;
        int spacing = 40;
        
        // North light
        drawLight(g, centerX - 90, centerY - 140, getLightForDirection(VehicleSprite.Direction.NORTH), lightSize);
        
        // South light
        drawLight(g, centerX + 90, centerY + 140, getLightForDirection(VehicleSprite.Direction.SOUTH), lightSize);
        
        // East light
        drawLight(g, centerX + 140, centerY - 90, getLightForDirection(VehicleSprite.Direction.EAST), lightSize);
        
        // West light
        drawLight(g, centerX - 140, centerY + 90, getLightForDirection(VehicleSprite.Direction.WEST), lightSize);
    }
    
    /**
     * Draw a single traffic light
     */
    private void drawLight(Graphics2D g, int x, int y, LightState state, int size) {
        // Background box
        g.setColor(new Color(50, 50, 50));
        g.fillRect(x - 15, y - 50, 30, 100);
        
        // Green (top)
        g.setColor(state == LightState.GREEN ? new Color(0, 255, 0) : new Color(100, 150, 100));
        g.fillOval(x - 8, y - 40, size, size);
        if (state == LightState.GREEN) {
            g.setColor(new Color(0, 200, 0));
            g.setStroke(new BasicStroke(2));
            g.drawOval(x - 8, y - 40, size, size);
        }
        
        // Yellow (middle)
        g.setColor(state == LightState.YELLOW ? new Color(255, 255, 0) : new Color(150, 150, 100));
        g.fillOval(x - 8, y - 10, size, size);
        if (state == LightState.YELLOW) {
            g.setColor(new Color(200, 200, 0));
            g.setStroke(new BasicStroke(2));
            g.drawOval(x - 8, y - 10, size, size);
        }
        
        // Red (bottom)
        g.setColor(state == LightState.RED ? new Color(255, 0, 0) : new Color(150, 100, 100));
        g.fillOval(x - 8, y + 20, size, size);
        if (state == LightState.RED) {
            g.setColor(new Color(200, 0, 0));
            g.setStroke(new BasicStroke(2));
            g.drawOval(x - 8, y + 20, size, size);
        }
    }
    
    // Getters
    public Phase getCurrentPhase() {
        return currentPhase;
    }
    
    public String getPhaseString() {
        switch (currentPhase) {
            case NORTH_SOUTH_GREEN: return "N/S GREEN";
            case NORTH_SOUTH_YELLOW: return "N/S YELLOW";
            case EAST_WEST_GREEN: return "E/W GREEN";
            case EAST_WEST_YELLOW: return "E/W YELLOW";
            default: return "UNKNOWN";
        }
    }
    
    // Setters for timing (for speed control)
    public void setSpeed(double factor) {
        GREEN_DURATION = (long)(10000 / factor);
        YELLOW_DURATION = (long)(3000 / factor);
        RED_DURATION = (long)(10000 / factor);
    }
}

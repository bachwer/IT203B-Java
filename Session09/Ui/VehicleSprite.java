package Session09.Ui;

import java.awt.*;

/**
 * Represents a single vehicle sprite in the simulation.
 * Handles movement, state, and rendering properties.
 */
public class VehicleSprite {
    
    // Vehicle types
    public enum VehicleType {
        CAR, TRUCK, MOTORBIKE, AMBULANCE
    }
    
    // Direction enum
    public enum Direction {
        NORTH, SOUTH, EAST, WEST
    }
    
    // Vehicle state
    public enum Lane {
        LANE_1, LANE_2
    }
    
    // Properties
    private int id;
    private VehicleType type;
    private Direction direction;
    private Lane lane;
    private double x, y;
    private double speed;
    private double maxSpeed;
    private Color color;
    private int width, height;
    
    // State
    private boolean canMove = false;
    private boolean hasCrossed = false;
    private double targetX, targetY;
    
    // Blinking for ambulance
    private long blinkCounter = 0;
    private boolean blinking = false;
    
    // Constructor
    public VehicleSprite(int id, VehicleType type, Direction direction, Lane lane, 
                         double startX, double startY, double maxSpeed) {
        this.id = id;
        this.type = type;
        this.direction = direction;
        this.lane = lane;
        this.x = startX;
        this.y = startY;
        this.targetX = startX;
        this.targetY = startY;
        this.maxSpeed = maxSpeed;
        this.speed = 0;
        
        // Set vehicle properties based on type
        setVehicleProperties();
    }
    
    private void setVehicleProperties() {
        switch (type) {
            case CAR:
                this.width = 30;
                this.height = 15;
                this.color = new Color(0, 102, 204);      // Blue
                this.maxSpeed = 2.5;
                break;
            case TRUCK:
                this.width = 40;
                this.height = 18;
                this.color = new Color(204, 102, 0);      // Orange
                this.maxSpeed = 2.0;
                break;
            case MOTORBIKE:
                this.width = 20;
                this.height = 10;
                this.color = new Color(0, 153, 76);        // Green
                this.maxSpeed = 3.0;
                break;
            case AMBULANCE:
                this.width = 35;
                this.height = 16;
                this.color = new Color(255, 255, 255);    // White body
                this.maxSpeed = 3.5;
                break;
        }
    }
    
    public void update(boolean canMove, double targetX, double targetY) {
        this.canMove = canMove;
        this.targetX = targetX;
        this.targetY = targetY;
        
        // Move towards target
        if (canMove && (Math.abs(x - targetX) > 0.5 || Math.abs(y - targetY) > 0.5)) {
            speed = Math.min(speed + 0.05, maxSpeed);
        } else {
            speed = Math.max(speed - 0.1, 0);
        }
        
        // Update position
        if (speed > 0.1) {
            double dx = targetX - x;
            double dy = targetY - y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            
            if (distance > 0.5) {
                x += (dx / distance) * speed;
                y += (dy / distance) * speed;
            }
        }
        
        // Ambulance blinking effect
        if (type == VehicleType.AMBULANCE) {
            blinkCounter++;
            blinking = (blinkCounter / 10) % 2 == 0;
        }
    }
    
    public void draw(Graphics2D g) {
        // Skip if ambulance is in blink-off state
        if (type == VehicleType.AMBULANCE && !blinking) {
            return;
        }
        
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Move to vehicle position
        g2.translate(x, y);
        
        // Rotate based on direction
        double rotation = 0;
        switch (direction) {
            case NORTH: rotation = -Math.PI / 2; break;
            case SOUTH: rotation = Math.PI / 2; break;
            case EAST:  rotation = 0; break;
            case WEST:  rotation = Math.PI; break;
        }
        g2.rotate(rotation);
        
        // Draw shadow (slightly offset and semi-transparent)
        g2.setColor(new Color(0, 0, 0, 60));
        g2.fillRoundRect(-width / 2 + 2, -height / 2 + 2, width, height, 5, 5);
        
        // Draw vehicle body
        g2.setColor(color);
        g2.fillRoundRect(-width / 2, -height / 2, width, height, 5, 5);
        
        // Draw border/windows
        g2.setColor(new Color(40, 40, 40));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(-width / 2, -height / 2, width, height, 5, 5);
        
        // Windshield and windows
        g2.setColor(new Color(135, 206, 250, 200)); // Light blue glass
        g2.fillRect(-width / 2 + 5, -height / 2 + 2, width / 4, height - 4);
        
        // Lights
        if (speed < 0.2) {
            // Brake lights (Red)
            g2.setColor(Color.RED);
            g2.fillRect(-width / 2, -height / 2, 2, 4);
            g2.fillRect(-width / 2, height / 2 - 4, 2, 4);
        } else {
            // Tail lights (Dim Red)
            g2.setColor(new Color(150, 0, 0));
            g2.fillRect(-width / 2, -height / 2, 2, 4);
            g2.fillRect(-width / 2, height / 2 - 4, 2, 4);
        }
        
        // Headlights (Yellow/White)
        g2.setColor(new Color(255, 255, 200, 180));
        g2.fillRect(width / 2 - 2, -height / 2, 2, 4);
        g2.fillRect(width / 2 - 2, height / 2 - 4, 2, 4);

        // Ambulance specific decorations
        if (type == VehicleType.AMBULANCE) {
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(-3, 0, 3, 0);
            g2.drawLine(0, -3, 0, 3);
            
            // Emergency light bar
            g2.setColor(blinking ? Color.BLUE : Color.RED);
            g2.fillRect(-2, -height / 2 - 2, 4, height + 4);
        }

        g2.dispose();
    }
    
    private String getTypeLabel() {
        return ""; // Label removed for cleaner look
    }
    
    // Getters
    public int getId() { return id; }
    public VehicleType getType() { return type; }
    public Direction getDirection() { return direction; }
    public Lane getLane() { return lane; }
    public double getX() { return x; }
    public double getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean canIgnoreRedLight() { 
        return type == VehicleType.AMBULANCE; 
    }
    public Color getColor() { return color; }
    public double getSpeed() { return speed; }
    public boolean hasCrossed() { return hasCrossed; }
    
    // Setters
    public void setCrossed(boolean crossed) { this.hasCrossed = crossed; }
    public void setPosition(double x, double y) { 
        this.x = x; 
        this.y = y; 
    }
}

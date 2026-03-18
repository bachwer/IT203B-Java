package Session09.Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main simulation panel that handles rendering and updating all vehicles.
 * Uses a timer for smooth animation at ~60 FPS.
 */
public class TrafficPanel extends JPanel {
    
    private ArrayList<VehicleSprite> vehicles = new ArrayList<>();
    private TrafficLightUI trafficLight;
    
    private int panelWidth, panelHeight;
    private int centerX, centerY;
    private int roadWidth = 120;  // Each road is 120px wide
    private int laneWidth = 60;   // Each lane is 60px wide
    
    // Lane positions (for 2-lane system)
    private double[] horizontalLanes = new double[2];
    private double[] verticalLanes = new double[2];
    
    // Intersection bounds
    private int intersectionLeft, intersectionRight, intersectionTop, intersectionBottom;
    
    // Vehicle spawn settings
    private long lastSpawnTime = 0;
    private int spawnInterval = 300;  // 300ms
    private int totalVehiclesGenerated = 0;
    private final int MAX_VEHICLES = Integer.MAX_VALUE; // Allow continuous spawning
    
    // Simulation state
    private boolean isRunning = false;
    private Timer animationTimer;
    private double speedFactor = 1.0;
    
    // Statistics
    private int vehiclesCrossed = 0;
    private long simulationStartTime = 0;
    
    public TrafficPanel() {
        setBackground(new Color(240, 240, 240));
        trafficLight = new TrafficLightUI();
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateDimensions();
            }
        });
        
        // Animation timer for smooth movement (16ms ≈ 60 FPS)
        animationTimer = new Timer(16, e -> {
            update();
            repaint();
        });
    }
    
    private void updateDimensions() {
        this.panelWidth = getWidth();
        this.panelHeight = getHeight();
        this.centerX = panelWidth / 2;
        this.centerY = panelHeight / 2;
        
        // Lane positions (lane centers)
        horizontalLanes[0] = centerY - laneWidth / 2;      // North lane
        horizontalLanes[1] = centerY + laneWidth / 2;      // South lane
        
        verticalLanes[0] = centerX - laneWidth / 2;        // West lane
        verticalLanes[1] = centerX + laneWidth / 2;        // East lane
        
        // Intersection bounds
        intersectionLeft = centerX - roadWidth / 2;
        intersectionRight = centerX + roadWidth / 2;
        intersectionTop = centerY - roadWidth / 2;
        intersectionBottom = centerY + roadWidth / 2;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (panelWidth == 0) {
            updateDimensions();
        }
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        // Draw components in order
        drawRoads(g2d);
        drawLaneMarkings(g2d);
        drawIntersectionBox(g2d);
        drawStopLines(g2d);
        drawVehicles(g2d);
        drawTrafficLights(g2d);
        drawUI(g2d);
    }
    
    private void drawRoads(Graphics2D g) {
        // Draw background Grass
        g.setColor(new Color(34, 139, 34)); // Forest Green
        g.fillRect(0, 0, panelWidth, panelHeight);
        
        // Add some noise/texture to grass
        g.setColor(new Color(0, 100, 0, 30));
        for (int i = 0; i < panelWidth; i += 50) {
            for (int j = 0; j < panelHeight; j += 50) {
                if ((i + j) % 100 == 0) {
                    g.fillOval(i, j, 5, 5);
                }
            }
        }

        // Sidewalks
        g.setColor(new Color(180, 180, 180));
        // Horizontal sidewalks
        g.fillRect(0, centerY - roadWidth / 2 - 15, panelWidth, 15);
        g.fillRect(0, centerY + roadWidth / 2, panelWidth, 15);
        // Vertical sidewalks
        g.fillRect(centerX - roadWidth / 2 - 15, 0, 15, panelHeight);
        g.fillRect(centerX + roadWidth / 2, 0, 15, panelHeight);

        // Asphalt
        g.setColor(new Color(45, 45, 45)); // Dark Asphalt
        
        // Horizontal road (East-West)
        g.fillRect(0, centerY - roadWidth / 2, panelWidth, roadWidth);
        
        // Vertical road (North-South)
        g.fillRect(centerX - roadWidth / 2, 0, roadWidth, panelHeight);
        
        // Intersection area
        g.setColor(new Color(50, 50, 50));
        g.fillRect(centerX - roadWidth / 2, centerY - roadWidth / 2, roadWidth, roadWidth);
    }
    
    private void drawLaneMarkings(Graphics2D g) {
        // Center yellow lines (double line)
        g.setColor(new Color(255, 204, 0));
        g.setStroke(new BasicStroke(2));
        
        // Horizontal center
        g.drawLine(0, centerY - 2, panelWidth, centerY - 2);
        g.drawLine(0, centerY + 2, panelWidth, centerY + 2);
        
        // Vertical center
        g.drawLine(centerX - 2, 0, centerX - 2, panelHeight);
        g.drawLine(centerX + 2, 0, centerX + 2, panelHeight);

        // White dashed lane markings
        g.setColor(new Color(255, 255, 255, 200));
        float[] dash = {20.0f, 20.0f};
        g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash, 0));
        
        // Horizontal lanes
        g.drawLine(0, centerY - roadWidth / 4, panelWidth, centerY - roadWidth / 4);
        g.drawLine(0, centerY + roadWidth / 4, panelWidth, centerY + roadWidth / 4);
        
        // Vertical lanes
        g.drawLine(centerX - roadWidth / 4, 0, centerX - roadWidth / 4, panelHeight);
        g.drawLine(centerX + roadWidth / 4, 0, centerX + roadWidth / 4, panelHeight);
    }
    
    private void drawIntersectionBox(Graphics2D g) {
        // Pedestrian Crossings (Zebra)
        g.setColor(new Color(255, 255, 255, 180));
        g.setStroke(new BasicStroke(8));
        
        int zebraLength = 30;
        int zebraGap = 12;
        
        // North
        for (int i = intersectionLeft + 5; i < intersectionRight; i += zebraGap) {
            g.drawLine(i, intersectionTop - 5, i, intersectionTop - zebraLength);
        }
        // South
        for (int i = intersectionLeft + 5; i < intersectionRight; i += zebraGap) {
            g.drawLine(i, intersectionBottom + 5, i, intersectionBottom + zebraLength);
        }
        // West
        for (int i = intersectionTop + 5; i < intersectionBottom; i += zebraGap) {
            g.drawLine(intersectionLeft - 5, i, intersectionLeft - zebraLength, i);
        }
        // East
        for (int i = intersectionTop + 5; i < intersectionBottom; i += zebraGap) {
            g.drawLine(intersectionRight + 5, i, intersectionRight + zebraLength, i);
        }
    }
    
    private void drawStopLines(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(4));
        
        // North stop line
        g.drawLine(intersectionLeft, intersectionTop - 2, intersectionRight, intersectionTop - 2);
        // South stop line
        g.drawLine(intersectionLeft, intersectionBottom + 2, intersectionRight, intersectionBottom + 2);
        // West stop line
        g.drawLine(intersectionLeft - 2, intersectionTop, intersectionLeft - 2, intersectionBottom);
        // East stop line
        g.drawLine(intersectionRight + 2, intersectionTop, intersectionRight + 2, intersectionBottom);
    }
    
    private void drawVehicles(Graphics2D g) {
        synchronized (vehicles) {
            for (VehicleSprite vehicle : vehicles) {
                vehicle.draw((Graphics2D) g);
            }
        }
    }
    
    private void drawTrafficLights(Graphics2D g) {
        trafficLight.draw((Graphics2D) g, centerX, centerY);
    }
    
    private void drawUI(Graphics2D g) {
        // Status panel with glassmorphism effect
        int padding = 20;
        int panelW = 300;
        int panelH = 120;
        
        // Background
        g.setColor(new Color(20, 20, 20, 180));
        g.fillRoundRect(padding, padding, panelW, panelH, 15, 15);
        
        // Border
        g.setColor(new Color(255, 255, 255, 50));
        g.setStroke(new BasicStroke(1));
        g.drawRoundRect(padding, padding, panelW, panelH, 15, 15);
        
        // Title
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 18));
        g.drawString("🚦 Traffic Simulation Pro", padding + 20, padding + 35);
        
        // Divider
        g.setColor(new Color(255, 255, 255, 30));
        g.drawLine(padding + 20, padding + 45, padding + panelW - 20, padding + 45);
        
        // Stats
        g.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        long elapsedSeconds = isRunning ? (System.currentTimeMillis() - simulationStartTime) / 1000 : 0;
        
        g.setColor(new Color(200, 200, 200));
        g.drawString("⏱ Uptime:", padding + 20, padding + 65);
        g.drawString("🚗 Active:", padding + 20, padding + 85);
        g.drawString("✅ Crossed:", padding + 20, padding + 105);
        
        g.setColor(Color.WHITE);
        g.drawString(elapsedSeconds + "s", padding + 100, padding + 65);
        g.drawString(getVehicleCount() + " vehicles", padding + 100, padding + 85);
        g.drawString(vehiclesCrossed + " units", padding + 100, padding + 105);

        // Status badge
        String statusText = isRunning ? "LIVE" : "PAUSED";
        Color statusColor = isRunning ? new Color(0, 200, 0) : new Color(200, 0, 0);
        
        g.setColor(statusColor);
        g.fillRoundRect(padding + panelW - 80, padding + 15, 60, 20, 10, 10);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 10));
        g.drawString(statusText, padding + panelW - 65, padding + 29);
    }
    
    private void update() {
        if (!isRunning) return;
        
        // Update traffic light
        trafficLight.update();
        
        // Spawn new vehicles
        spawnVehicles();
        
        // Update all vehicles
        synchronized (vehicles) {
            for (int i = vehicles.size() - 1; i >= 0; i--) {
                VehicleSprite vehicle = vehicles.get(i);
                updateVehicle(vehicle);
                
                // Remove vehicles that have exited
                if (hasExitedMap(vehicle)) {
                    vehicles.remove(i);
                    vehiclesCrossed++;
                }
            }
        }
    }
    
    private void spawnVehicles() {
        if (totalVehiclesGenerated >= MAX_VEHICLES) return;
        
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpawnTime > spawnInterval) {
            spawnRandomVehicle();
            lastSpawnTime = currentTime;
            totalVehiclesGenerated++;
            
            // Random spawn interval 200-500ms
            spawnInterval = 200 + (int)(Math.random() * 300);
        }
    }
    
    private void spawnRandomVehicle() {
        Random rand = new Random();
        
        // Random direction
        VehicleSprite.Direction direction = VehicleSprite.Direction.values()[rand.nextInt(4)];
        
        // Random lane
        VehicleSprite.Lane lane = rand.nextBoolean() ? VehicleSprite.Lane.LANE_1 : VehicleSprite.Lane.LANE_2;
        
        // Random type (10% ambulance, 90% standard)
        VehicleSprite.VehicleType type;
        if (rand.nextInt(10) == 0) {
            type = VehicleSprite.VehicleType.AMBULANCE;
        } else {
            type = VehicleSprite.VehicleType.values()[rand.nextInt(3)];  // CAR, TRUCK, MOTORBIKE
        }
        
        // Calculate spawn position based on direction and lane
        double x, y;
        double speed;
        
        switch (direction) {
            case NORTH:
                x = verticalLanes[lane == VehicleSprite.Lane.LANE_1 ? 0 : 1];
                y = panelHeight + 100; // Spawn further off-screen
                break;
            case SOUTH:
                x = verticalLanes[lane == VehicleSprite.Lane.LANE_1 ? 0 : 1];
                y = -100;
                break;
            case EAST:
                x = -100;
                y = horizontalLanes[lane == VehicleSprite.Lane.LANE_1 ? 0 : 1];
                break;
            case WEST:
            default:
                x = panelWidth + 100;
                y = horizontalLanes[lane == VehicleSprite.Lane.LANE_1 ? 0 : 1];
                break;
        }
        
        VehicleSprite vehicle = new VehicleSprite(totalVehiclesGenerated, type, direction, lane, x, y, 2.5);
        
        synchronized (vehicles) {
            vehicles.add(vehicle);
        }
    }
    
    private void updateVehicle(VehicleSprite vehicle) {
        double targetX = vehicle.getX();
        double targetY = vehicle.getY();
        
        VehicleSprite.Direction dir = vehicle.getDirection();
        double laneY = horizontalLanes[vehicle.getLane() == VehicleSprite.Lane.LANE_1 ? 0 : 1];
        double laneX = verticalLanes[vehicle.getLane() == VehicleSprite.Lane.LANE_1 ? 0 : 1];
        
        // Determine if vehicle can move through intersection
        TrafficLightUI.LightState light = trafficLight.getLightForDirection(dir);
        boolean canMove = false;
        
        if (vehicle.canIgnoreRedLight()) {
            canMove = true;
        } else if (light == TrafficLightUI.LightState.GREEN) {
            canMove = true;
        } else {
            // RED or YELLOW
            if (isPassedStopLine(vehicle)) {
                // Already in intersection, must clear it
                canMove = true;
            } else {
                // Not yet passed stop line.
                if (light == TrafficLightUI.LightState.YELLOW && isNearStopLine(vehicle)) {
                    // Commit to yellow if close
                    canMove = true;
                } else {
                    // Red or far yellow: move until reaching the stop line
                    canMove = !isAtStopLine(vehicle);
                }
            }
        }
        
        // Wait for vehicle ahead (Queuing logic)
        double safetyDistance = 50 + (vehicle.getSpeed() * 5); // Base distance + speed-dependent buffer
        if (isVehicleAhead(vehicle, safetyDistance)) {
            canMove = false;
        }

        // Calculate next target position
        double step = 2.5 * speedFactor;
        switch (dir) {
            case NORTH:
                targetY = vehicle.getY() - step;
                targetX = laneX;
                break;
            case SOUTH:
                targetY = vehicle.getY() + step;
                targetX = laneX;
                break;
            case EAST:
                targetX = vehicle.getX() + step;
                targetY = laneY;
                break;
            case WEST:
                targetX = vehicle.getX() - step;
                targetY = laneY;
                break;
        }
        
        // Update vehicle
        vehicle.update(canMove, targetX, targetY);
    }
    
    // Check if vehicle has already passed the stop line (inside intersection)
    private boolean isPassedStopLine(VehicleSprite vehicle) {
        double x = vehicle.getX();
        double y = vehicle.getY();
        
        switch (vehicle.getDirection()) {
            case NORTH: return y < intersectionBottom;
            case SOUTH: return y > intersectionTop;
            case EAST:  return x > intersectionLeft;
            case WEST:  return x < intersectionRight;
            default: return false;
        }
    }

    // Check if vehicle is "at" the stop line (should stop for Red)
    private boolean isAtStopLine(VehicleSprite vehicle) {
        double x = vehicle.getX();
        double y = vehicle.getY();
        double threshold = 10; // Pixels from stop line
        
        switch (vehicle.getDirection()) {
            case NORTH: return y <= intersectionBottom + threshold && y >= intersectionBottom;
            case SOUTH: return y >= intersectionTop - threshold && y <= intersectionTop;
            case EAST:  return x >= intersectionLeft - threshold && x <= intersectionLeft;
            case WEST:  return x <= intersectionRight + threshold && x >= intersectionRight;
            default: return false;
        }
    }
    
    // Check if vehicle is approaching but not yet at the stop line
    private boolean isNearStopLine(VehicleSprite vehicle) {
        double x = vehicle.getX();
        double y = vehicle.getY();
        int margin = 80; // Distance to recognize light
        
        switch (vehicle.getDirection()) {
            case NORTH: return y <= intersectionBottom + margin && y > intersectionBottom;
            case SOUTH: return y >= intersectionTop - margin && y < intersectionTop;
            case EAST:  return x >= intersectionLeft - margin && x < intersectionLeft;
            case WEST:  return x <= intersectionRight + margin && x > intersectionRight;
            default: return false;
        }
    }

    private boolean isVehicleAhead(VehicleSprite vehicle, double minDistance) {
        synchronized (vehicles) {
            for (VehicleSprite other : vehicles) {
                if (other == vehicle) continue;
                
                // Only check same direction and lane
                if (other.getDirection() != vehicle.getDirection() || 
                    other.getLane() != vehicle.getLane()) {
                    continue;
                }
                
                double dx = other.getX() - vehicle.getX();
                double dy = other.getY() - vehicle.getY();
                
                // Directional check: Is 'other' in front of 'vehicle'?
                boolean inFront = false;
                double dist = 0;
                
                switch (vehicle.getDirection()) {
                    case NORTH: 
                        inFront = dy < 0; 
                        dist = -dy;
                        break;
                    case SOUTH: 
                        inFront = dy > 0; 
                        dist = dy;
                        break;
                    case EAST:  
                        inFront = dx > 0; 
                        dist = dx;
                        break;
                    case WEST:  
                        inFront = dx < 0; 
                        dist = -dx;
                        break;
                }
                
                if (inFront && dist < minDistance) {
                    return true;
                }
            }
        }
        return false;
    }
    

    

    
    private boolean hasExitedMap(VehicleSprite vehicle) {
        double x = vehicle.getX();
        double y = vehicle.getY();
        
        return x < -100 || x > panelWidth + 100 || 
               y < -100 || y > panelHeight + 100;
    }
    
    // Control methods
    public void start() {
        isRunning = true;
        simulationStartTime = System.currentTimeMillis();
        animationTimer.start();
    }
    
    public void pause() {
        isRunning = false;
        animationTimer.stop();
    }
    
    public void reset() {
        pause();
        synchronized (vehicles) {
            vehicles.clear();
        }
        totalVehiclesGenerated = 0;
        vehiclesCrossed = 0;
        lastSpawnTime = 0;
        trafficLight = new TrafficLightUI();
    }
    
    public void setSpeed(double factor) {
        this.speedFactor = factor;
        trafficLight.setSpeed(factor);
    }
    
    // Getters
    public int getVehicleCount() {
        synchronized (vehicles) {
            return vehicles.size();
        }
    }
    
    public int getTotalVehiclesGenerated() {
        return totalVehiclesGenerated;
    }
    
    public int getVehiclesCrossed() {
        return vehiclesCrossed;
    }
}

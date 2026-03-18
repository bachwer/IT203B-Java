package Session09.entity;

public abstract class Vehicle implements Runnable{

    String id;
    int speed;
    int priority;
    static int vehicleNumber = 0;
    Intersection intersection;
    double posX = 0;
    double posY = 0;
    int vehicleIndex = -1;

    public Vehicle(int speed, int priority, Intersection intersection) {
        this.id = String.valueOf(vehicleNumber + 1);
        this.speed = speed;
        this.priority = priority;
        this.intersection = intersection;
        vehicleNumber += 1;
    }

    public String getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    public int getPriority() {
        return priority;
    }

    public Intersection getIntersection() {
        return intersection;
    }

    public abstract String getType();
    public abstract boolean isPriority();

    @Override
    public void run() {
        try {
            approachIntersection();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void approachIntersection() throws InterruptedException {
        System.out.println(getType() + " #" + id + " approaching intersection");
        
        // Random entry point
        int direction = (int)(Math.random() * 4); // 0=North, 1=South, 2=East, 3=West
        initializePosition(direction);
        
        intersection.requestCross(this);
    }
    
    private void initializePosition(int direction) {
        int width = 1200;
        int height = 800;
        int centerX = width / 2;
        int centerY = height / 2;
        
        switch(direction) {
            case 0: // North - approaching from top
                posX = centerX - 30;
                posY = 50;
                break;
            case 1: // South - approaching from bottom
                posX = centerX + 30;
                posY = height - 80;
                break;
            case 2: // East - approaching from right
                posX = width - 80;
                posY = centerY - 20;
                break;
            case 3: // West - approaching from left
                posX = 50;
                posY = centerY + 20;
                break;
        }
        
    }
    
    public void updatePosition(double x, double y) {
        this.posX = x;
        this.posY = y;
    }
    
    public double getPosX() {
        return posX;
    }
    
    public double getPosY() {
        return posY;
    }
}

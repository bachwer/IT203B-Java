package Session09.entity;
import java.util.concurrent.locks.ReentrantLock;


public class Intersection {
    final ReentrantLock lock = new ReentrantLock();
    final TrafficLight trafficLight;

    public Intersection(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }
    
    public void requestCross(Vehicle vehicle) throws InterruptedException{
        while(true){
            if(trafficLight.getState() == TrafficLight.State.GREEN || vehicle.getPriority() > 5){

                if(lock.tryLock()){
                    try{
                        cross(vehicle);
                        return;
                    }finally{
                        lock.unlock();
                    }
                }
            }
            Thread.sleep(200); // Don't spin, wait a bit
        }
    }

    private void cross(Vehicle vehicle) throws InterruptedException {
        System.out.println(vehicle.getType() + " #" + vehicle.getId() + " is crossing");
        
        // Animate vehicle crossing intersection smoothly
        int centerX = 600;
        int centerY = 400;
        int steps = 30;
        
        // Randomly choose direction for each vehicle
        int direction = (int)(Math.random() * 4);
        
        for (int i = 0; i <= steps; i++) {
            double progress = (double) i / steps; // 0 to 1 progress
            double newX, newY;
            
            switch(direction) {
                case 0: // North to South (vertical)
                    newX = centerX + (Math.random() - 0.5) * 80;
                    newY = 50 + progress * 700;
                    break;
                case 1: // South to North (vertical reversed)
                    newX = centerX + (Math.random() - 0.5) * 80;
                    newY = 750 - progress * 700;
                    break;
                case 2: // West to East (horizontal)
                    newX = 50 + progress * 1100;
                    newY = centerY + (Math.random() - 0.5) * 80;
                    break;
                default: // East to West (horizontal reversed)
                    newX = 1150 - progress * 1100;
                    newY = centerY + (Math.random() - 0.5) * 80;
                    break;
            }
            
            vehicle.updatePosition(newX, newY);
            Thread.sleep(150); // Update every 150ms for smooth animation
        }
        
        System.out.println(vehicle.getType() + " #" + vehicle.getId() + " passed intersection");
    }
}

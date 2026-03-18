package Session09.engine;

import Session09.entity.*;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationEngine {
    static final int VEHICLE_COUNT = 50;

    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        Intersection intersection = new Intersection(trafficLight);

        Thread lightThread = new Thread(trafficLight);
        lightThread.setDaemon(true);
        lightThread.start();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Random random = new Random();

        for(int i = 0; i < VEHICLE_COUNT; i++){
            Vehicle vehicle;

            if(random.nextInt(10) == 0){
                vehicle = new PriorityVehicle(80, intersection, "Ambulance");

            }else {
                String[] types = {"Car", "Truck", "Motorbike"};
                String type = types[random.nextInt(types.length)];
                vehicle = new StandardVehicle(60,intersection,  type);
            }

            executor.submit(vehicle);
            try{
                Thread.sleep(300);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }


        executor.shutdown();
    }
}

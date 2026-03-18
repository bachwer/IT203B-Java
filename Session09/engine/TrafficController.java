package Session09.engine;

import Session09.entity.Intersection;
import Session09.entity.PriorityVehicle;
import Session09.entity.StandardVehicle;
import Session09.entity.Vehicle;

import java.util.Random;
import java.util.concurrent.ExecutorService;

public class TrafficController {
    final ExecutorService executor;
    final Intersection intersection;
    private final Random random = new Random();


    public TrafficController(ExecutorService executor, Intersection intersection) {
        this.executor = executor;
        this.intersection = intersection;
    }

    public void generateVehicle(){
        Vehicle vehicle;

        if(random.nextInt(10) == 0){
            vehicle =  new PriorityVehicle(80, intersection, "Ambulance");
        }else{
            String[] types = {"Car", "Truck", "Motorbike"};
            vehicle = new StandardVehicle(60, intersection, types[random.nextInt(3)]);
        }
        executor.submit(vehicle);
    }
}

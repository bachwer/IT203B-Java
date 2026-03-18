package Session09.pattern.factory;

import Session09.entity.Intersection;
import Session09.entity.PriorityVehicle;
import Session09.entity.StandardVehicle;
import Session09.entity.Vehicle;

import java.util.Random;

public class VehicleFactory {
    static final Random random = new Random();
    public static Vehicle createRandomVehicle(Intersection intersection){
        int chance = random.nextInt(10);

        if(chance == 0){
            return new PriorityVehicle(80, intersection, "Ambulance");
        }
        String[] types = {"Car", "Truck", "MotorBike"};
        String type = types[random.nextInt(types.length)];
        int speed;
        switch (type) {
            case "Car":
                speed = 60;
                break;
            case "Truck":
                speed = 50;
                break;
            default:
                speed = 70;
        }

        return new StandardVehicle(speed , intersection, type);
    }
}

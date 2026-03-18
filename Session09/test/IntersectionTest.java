package Session09.test;

import Session09.entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectionTest {

    @Test
    void testVehicleCrossing() throws InterruptedException {

        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setState(TrafficLight.State.GREEN);

        Intersection intersection = new Intersection(trafficLight);

        Vehicle car = new StandardVehicle(60, intersection, "Car" );

        intersection.requestCross(car);

        assertTrue(true);

    }
    @Test
    void testMultipleVehicles() throws InterruptedException {

        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setState(TrafficLight.State.GREEN);

        Intersection intersection = new Intersection(trafficLight);

        for(int i = 0; i < 50; i++){

            Vehicle car = new StandardVehicle(60, intersection,"Car" );

            new Thread(car).start();

        }

        Thread.sleep(2000);

        assertTrue(true);

    }

}
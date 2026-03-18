package Session09.test;

import Session09.entity.TrafficLight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficLightTest {

    @Test
    void testInitialState() {

        TrafficLight light = new TrafficLight();

        assertEquals(TrafficLight.State.RED, light.getState());

    }

    @Test
    void testStateChange() {

        TrafficLight light = new TrafficLight();

        light.setState(TrafficLight.State.GREEN);

        assertEquals(TrafficLight.State.GREEN, light.getState());

    }

}
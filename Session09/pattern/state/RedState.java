package Session09.pattern.state;

import Session09.entity.TrafficLight;

public class RedState implements TrafficLightState {
    @Override
    public void change(TrafficLight trafficLight) {

        System.out.println("Traffic Light: RED");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        trafficLight.setState(TrafficLight.State.GREEN);

    }

    @Override
    public String getColor() {
        return "RED";
    }
}

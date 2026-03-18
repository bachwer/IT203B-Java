package Session09.pattern.state;

import Session09.entity.TrafficLight;

public class GreenState implements TrafficLightState {

    @Override
    public void change(TrafficLight trafficLight) {
        System.out.println("Traffic Light: GREEN");

        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        trafficLight.setState(TrafficLight.State.YELLOW);

    }

    @Override
    public String getColor() {
        return "GREEN";
    }
}

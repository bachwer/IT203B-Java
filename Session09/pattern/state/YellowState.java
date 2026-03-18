package Session09.pattern.state;

import Session09.entity.TrafficLight;

public class YellowState implements TrafficLightState {

    @Override
    public void change(TrafficLight trafficLight) {
        System.out.println("Traffic Light: YELLOW");

        try{
            Thread.sleep(3000);

        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        trafficLight.setState(TrafficLight.State.RED);

    }

    @Override
    public String getColor() {
        return "YELLOW";
    }
}

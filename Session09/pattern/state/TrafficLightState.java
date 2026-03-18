package Session09.pattern.state;

import Session09.entity.TrafficLight;

public interface TrafficLightState {
    public void change(TrafficLight trafficLight);
    public String getColor();

}

package Session09.entity;

public class PriorityVehicle extends Vehicle{
    String type;

    public PriorityVehicle(int speed, Intersection intersection, String type) {
        super(speed, 10, intersection);
        this.type = type;
    }
    
    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public boolean isPriority() {
        return true;
    }
}

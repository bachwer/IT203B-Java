package Session09.entity;

public class StandardVehicle extends Vehicle{

    String type;

    public StandardVehicle(int speed, Intersection intersection, String type) {
        super(speed, 1, intersection);
        this.type = type;
    }
    
    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public boolean isPriority() {
        return false;
    }
}

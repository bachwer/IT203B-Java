package Session06.Ex6;
public class Ticket {

    private String id;
    private String roomName;
    private boolean sold;

    public Ticket(String id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }

    public String getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean isSold() {
        return sold;
    }

    public void sell() {
        sold = true;
    }
}
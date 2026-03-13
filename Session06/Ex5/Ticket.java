package Session06.Ex5;
public class Ticket {

    private String ticketId;
    private String roomName;

    private boolean isSold;
    private boolean isHeld;

    private boolean isVIP;

    private long holdExpiryTime;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
        this.isSold = false;
        this.isHeld = false;
    }

    public String getTicketId() {
        return ticketId;
    }

    public boolean isSold() {
        return isSold;
    }

    public boolean isHeld() {
        return isHeld;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void hold(boolean vip, long expiryTime) {
        this.isHeld = true;
        this.isVIP = vip;
        this.holdExpiryTime = expiryTime;
    }

    public void sell() {
        this.isSold = true;
        this.isHeld = false;
    }

    public void release() {
        this.isHeld = false;
        this.isVIP = false;
    }

    public long getHoldExpiryTime() {
        return holdExpiryTime;
    }
}
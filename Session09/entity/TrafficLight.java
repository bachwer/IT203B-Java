package Session09.entity;

import java.util.concurrent.atomic.AtomicReference;

public class TrafficLight implements Runnable {

    public enum State {
        GREEN, YELLOW, RED
    }
//    đảm bảo thread-safe state change
    private final AtomicReference<State> currentState = new AtomicReference<>(State.RED);
    public State getState(){
        return currentState.get();
    }

    public void setState(State state){
        currentState.set(state);
        System.out.println("Traffic Light changed to: " + state);
    }
    @Override
    public void run() {

        try{
            while(true){
                setState(State.GREEN);
                Thread.sleep(15000);

                setState(State.YELLOW);
                Thread.sleep(3000);

                setState(State.RED);
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

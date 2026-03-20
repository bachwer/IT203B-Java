package Session08.tonghop;

import java.util.*;

public class TempSubject {
    private List<TempObserver> obs = new ArrayList<>();
    public void attach(TempObserver o) { obs.add(o); }
    public void notifyAll(int t) { for (TempObserver o : obs) o.update(t); }
}

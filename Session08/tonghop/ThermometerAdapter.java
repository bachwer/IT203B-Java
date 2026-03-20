package Session08.tonghop;

public class ThermometerAdapter implements TempSensor {
    private OldThermometer old;
    public ThermometerAdapter(OldThermometer old) { this.old = old; }
    public double getCelsius() { return (old.getF() - 32) * 5.0 / 9.0; }
}

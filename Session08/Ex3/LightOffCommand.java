package Session08.Ex3;

public class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    @Override
    public void execute() { light.off(); }
    @Override
    public void undo() { light.on(); }
}

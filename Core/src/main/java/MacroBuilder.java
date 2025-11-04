import java.util.List;

public class MacroBuilder implements Builder{
    private String name;
    private HotkeyTrigger trigger;
    private List<Action> actions;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setTrigger(HotkeyTrigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Macro getResult(){
        return new Macro(name, trigger, actions);
    }
}

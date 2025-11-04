
import java.util.ArrayList;
import java.util.List;

public class Macro {
    private String name;

    private HotkeyTrigger trigger;
    private List<Action> actions;
    public Macro(String name, HotkeyTrigger trigger, List<Action> actions){
        this.name = name;

        this.trigger = trigger;
        this.actions = actions;
    }
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(HotkeyTrigger trigger) {
        this.trigger = trigger;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}

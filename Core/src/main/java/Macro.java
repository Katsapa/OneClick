
import java.util.ArrayList;
import java.util.List;

public class Macro {
    private String name;
    private String description;
    private HotkeyTrigger trigger;
    private List<Action> actions;
    public Macro(String name, String description, HotkeyTrigger trigger, List<Action> actions){
        this.name = name;
        this.description = description;
        this.trigger = trigger;
        this.actions = actions;
    }
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

import java.util.ArrayList;

public class Macro {
    private String name;
    private String description;
    private Trigger trigger;
    private ArrayList<Action> actions;
    public Macro(String name, String description, Trigger trigger, ArrayList<Action> actions){
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

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }
}

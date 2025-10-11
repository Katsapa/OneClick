import java.util.List;

public interface Builder {
    void setName(String name);
    void setDescription(String description);
    void setTrigger(HotkeyTrigger trigger);
    void setActions(List<Action> actions);
}

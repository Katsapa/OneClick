import java.util.List;

public interface Builder {
    void setName(String name);
    void setTrigger(HotkeyTrigger trigger);
    void setActions(List<Action> actions);
}

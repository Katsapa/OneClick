
public class HotkeyTrigger implements Trigger{
    private String combination;
    private boolean active;

    HotkeyTrigger(String combination){
        this.combination = combination;
    }

    public void activate(){
        active = true;
    }
    public void deactivate(){
        active = false;
    }

    public boolean isActive(){
        return active;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }
}

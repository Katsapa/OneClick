import java.awt.*;
import java.util.List;

public class KeyboardAction implements Action{
    Robot robot;
    String value;
    String mod;
    int sec;

    private final Object lock = new Object();

    int keyCode;
    static List<String> mods = List.of("press", "click", "temp");


    KeyboardAction(String value, String mod) {
        if(!mods.contains(mod)){
            System.out.println("Non-exist mod");
            return;
        }
        this.mod = mod;
        this.value = value;
        this.robot = RobotAndListController.getRobot();
    }
    KeyboardAction(String value, String mod, int sec) {
        if(!mods.contains(mod)){
            return;
        }
        this.mod = mod;
        this.value = value;
        this.robot = RobotAndListController.getRobot();
        this.sec = sec;
    }

    public void execute() throws InterruptedException{
        if(InitializeMap.getKeyMap().containsKey(value.toUpperCase())){
            if(mod.equals(mods.get(0))){
                this.pressKey();
            } else if(mod.equals(mods.get(1))){
                this.clickExecute();
            } else {
                this.tempClicking(sec);
            }
        } else {
            System.out.println("Non-exist key");
        }
    }

    private void tempClicking(int sec){
        keyCode = InitializeMap.getKeyMap().get(value);
        robot.keyPress(keyCode);
        robot.delay(sec);
        robot.keyRelease(keyCode);
    }

    private void clickExecute(){
        keyCode = InitializeMap.getKeyMap().get(value);
        robot.keyPress(keyCode);

        robot.keyRelease(keyCode);
    }

    private void pressKey(){
        keyCode = InitializeMap.getKeyMap().get(value);
        ListController.getKeyboardList().add(value);
        robot.keyPress(keyCode);
    }
}

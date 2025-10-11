import java.awt.*;


public class KeyboardRelease implements  Action{

    int keyCode;
    String key;
    Robot robot = RobotAndListController.getRobot();

    KeyboardRelease(String key){
        this.keyCode = InitializeMap.getKeyMap().get(key);
        this.key = key;
    }

    public void execute(){
        if (ListController.getKeyboardList().contains(key)) {
            robot.keyRelease(keyCode);
            ListController.getKeyboardList().remove(key);
        }
    }
}

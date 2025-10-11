import java.awt.*;

public class KeyboardRelease implements  Action{

    int keyCode;
    Robot robot = RobotAndListController.getRobot();

    KeyboardRelease(String key){
        if(RobotAndListController.getKeyboardList().contains(key)){
            this.keyCode = InitializeMap.getKeyMap().get(key);
        }
    }

    public void execute(){
        robot.keyRelease(keyCode);
    }
}

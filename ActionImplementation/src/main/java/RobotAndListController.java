import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RobotAndListController {
    private RobotAndListController(){}
    private static Robot robot;
    private static List<String> pressedKeyList = new ArrayList<>();
    private static List<Integer> pressedBottonList = new ArrayList<>();

    public static Robot getRobot() {
        if(robot == null){
            synchronized (RobotAndListController.class){
                if(robot == null){
                    try{
                        robot = new Robot();
                        robot.setAutoWaitForIdle(true);
                    } catch (AWTException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return robot;
    }

    public static List<String> getKeyboardList(){
        return pressedKeyList;
    }

    public static List<Integer> getMouseList(){
        return pressedBottonList;
    }
}

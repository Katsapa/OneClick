import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RobotAndListController {
    private RobotAndListController(){}
    private static Robot robot;


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

}

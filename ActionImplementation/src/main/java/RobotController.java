import java.awt.*;

public class RobotController {
    private  RobotController(){}
    private static Robot robot;

    public static Robot getRobot() {
        if(robot == null){
            synchronized (RobotController.class){
                if(robot == null){
                    try{
                        robot = new Robot();
                    } catch (AWTException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return robot;
    }
}

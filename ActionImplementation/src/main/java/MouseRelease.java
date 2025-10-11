import java.awt.*;

public class MouseRelease implements Action{
    int mouseSide;
    Robot robot = RobotAndListController.getRobot();

    MouseRelease(int mouseSide){
        if(RobotAndListController.getMouseList().contains(mouseSide)){
            this.mouseSide = mouseSide;
        }
    }

    public void execute(){
        robot.keyPress(mouseSide);
        robot.mouseRelease(mouseSide);
    }
}

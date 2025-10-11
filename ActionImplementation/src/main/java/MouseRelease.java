import java.awt.*;

public class MouseRelease implements Action{
    int mouseSide;
    Robot robot = RobotAndListController.getRobot();

    MouseRelease(int mouseSide){
        if(ListController.getMouseList().contains(mouseSide)){
            this.mouseSide = mouseSide;
        }
    }

    public void execute(){
        robot.mouseRelease(mouseSide);
        ListController.getMouseList().remove(mouseSide);
    }
}

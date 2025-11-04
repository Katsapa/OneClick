import java.awt.*;

public class MouseRelease implements Action{
    int mouseSide;
    Robot robot = RobotAndListController.getRobot();

    MouseRelease(int mouseSide){
        if(ListController.getMouseList().contains(mouseSide)){
            this.mouseSide = mouseSide;
        } else {
            throw new IllegalArgumentException("illegal argument on the mouse release execute method");
        }
    }

    public void execute(){
        robot.mouseRelease(mouseSide);
        ListController.getMouseList().remove(mouseSide);
    }
}

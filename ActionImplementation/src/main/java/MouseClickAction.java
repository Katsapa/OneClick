import java.awt.*;
import java.awt.event.InputEvent;
import java.util.List;

public class MouseClickAction implements Action{
    int mouseSide;
    Robot robot;
    String mod;
    List<String> mods = List.of("double click", "click", "continued click");

    MouseClickAction(int mouseSide, String mod){
        if(!mods.contains(mod)){
            throw new IllegalArgumentException("Not exists mod");
        }
        this.mod = mod;
        this.mouseSide = translateOnInputEvent(mouseSide);
        this.robot = RobotAndListController.getRobot();
    }

    private static int translateOnInputEvent(int value){
        switch(value){
            case 1:
                return InputEvent.BUTTON1_DOWN_MASK;
            case 2:
                return InputEvent.BUTTON2_DOWN_MASK;
            case 3:
                return InputEvent.BUTTON3_DOWN_MASK;
            default:
                throw new IllegalArgumentException("Not existed mouse button");
        }
    }

    public void execute(){
        if(mod.equals(mods.get(0))){
            doubleClick();
        } else if(mod.equals(mods.get(1))){
            click();
        } else {
            pressMouse();
        }
    }

    public void pressMouse(){
        robot.mousePress(mouseSide);
        ListController.getMouseList().add(mouseSide);
    }

    private void click(){
        robot.keyPress(mouseSide);
        robot.mouseRelease(mouseSide);
    }

    private void doubleClick(){
        robot.mousePress(mouseSide);
        robot.mouseRelease(mouseSide);
        robot.mousePress(mouseSide);
        robot.mouseRelease(mouseSide);
    }
}

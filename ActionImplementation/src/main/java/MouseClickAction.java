import java.awt.*;
import java.util.List;

public class MouseClickAction implements Action{
    int mouseSide;
    Robot robot;
    String mod;
    List<String> mods = List.of("double click", "click", "continued click");

    MouseClickAction(int mouseSide, String mod){
        if(!mods.contains(mod)){
            return;
        }
        this.mod = mod;
        this.mouseSide = mouseSide;
    }

    public void execute(){
        if(mouseSide > 0 && mouseSide < 3){
            if(mod.equals(mods.get(0))){
                doubleClick();
            } else if(mod.equals(mods.get(1))){
                click();
            } else {
                pressMouse();
            }
        } else {
            System.out.println("Incorrect: going beyond the range of value mouse button");
        }
    }

    public void pressMouse(){
        robot.mousePress(mouseSide);
        RobotAndListController.getMouseList().add(mouseSide);
    }

    private void click(){
        robot.keyPress(mouseSide);
        robot.mouseRelease(mouseSide);
    }

    private void doubleClick(){
        robot.keyPress(mouseSide);
        robot.keyPress(mouseSide);
        robot.mouseRelease(mouseSide);
        robot.mouseRelease(mouseSide);
    }
}

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyboardAction implements Action{
    Robot robot;
    String value;
    ArrayList<String> mods;
    KeyboardAction(String value, String mod) throws AWTException {

        this.value = value;
        this.robot = new Robot();
    }
    public void execute(){
        if(InitializeMap.getKeyMap().containsKey(value.toUpperCase())){

        }
    }
}

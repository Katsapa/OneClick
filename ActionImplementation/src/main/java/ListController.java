import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListController {
    private static List<String> pressedKeyList = new ArrayList<>();
    private static List<Integer> pressedBottonList = new ArrayList<>();
    private ListController(){}

    public static List<String> getKeyboardList(){
        return pressedKeyList;
    }
    public static List<Integer> getMouseList(){
        return pressedBottonList;
    }
}

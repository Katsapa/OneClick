import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class InitializeMap {
    private static Map<String, Integer> keyMap;
    static InitializeMap instance;

    private InitializeMap(){}

    private void initializeKeyMap() {
        keyMap = new HashMap<>();

        for (char c = 'A'; c <= 'Z'; c++) {
            keyMap.put(String.valueOf(c), KeyEvent.getExtendedKeyCodeForChar(c));
        }



        for (char c = '0'; c <= '9'; c++) {
            keyMap.put(String.valueOf(c), KeyEvent.getExtendedKeyCodeForChar(c));
        }

        for(int i = 1; i <= 12; i++){
            keyMap.put("F"+i, 0x69+i);
        }

        // Специальные клавиши
        keyMap.put("ENTER", KeyEvent.VK_ENTER);
        keyMap.put("SPACE", KeyEvent.VK_SPACE);
        keyMap.put("TAB", KeyEvent.VK_TAB);
        keyMap.put("ESC", KeyEvent.VK_ESCAPE);
        keyMap.put("BACKSPACE", KeyEvent.VK_BACK_SPACE);
        keyMap.put("DELETE", KeyEvent.VK_DELETE);
        keyMap.put("INSERT", KeyEvent.VK_INSERT);
        keyMap.put("HOME", KeyEvent.VK_HOME);
        keyMap.put("END", KeyEvent.VK_END);
        keyMap.put("PAGE_UP", KeyEvent.VK_PAGE_UP);
        keyMap.put("PAGE_DOWN", KeyEvent.VK_PAGE_DOWN);

        // Стрелки
        keyMap.put("UP", KeyEvent.VK_UP);
        keyMap.put("DOWN", KeyEvent.VK_DOWN);
        keyMap.put("LEFT", KeyEvent.VK_LEFT);
        keyMap.put("RIGHT", KeyEvent.VK_RIGHT);

        // Модификаторы
        keyMap.put("SHIFT", KeyEvent.VK_SHIFT);
        keyMap.put("CTRL", KeyEvent.VK_CONTROL);
        keyMap.put("ALT", KeyEvent.VK_ALT);
        keyMap.put("WINDOWS", KeyEvent.VK_WINDOWS);
        keyMap.put("COMMAND", KeyEvent.VK_META);

        // Символы
        keyMap.put("COMMA", KeyEvent.VK_COMMA);
        keyMap.put("PERIOD", KeyEvent.VK_PERIOD);
        keyMap.put("SLASH", KeyEvent.VK_SLASH);
        keyMap.put("SEMICOLON", KeyEvent.VK_SEMICOLON);
        keyMap.put("EQUALS", KeyEvent.VK_EQUALS);
        keyMap.put("MINUS", KeyEvent.VK_MINUS);
        keyMap.put("PLUS", KeyEvent.VK_PLUS);
        keyMap.put("ASTERISK", KeyEvent.VK_ASTERISK);
        keyMap.put("BACKSLASH", KeyEvent.VK_BACK_SLASH);
        keyMap.put("QUOTE", KeyEvent.VK_QUOTE);
        keyMap.put("BACK_QUOTE", KeyEvent.VK_BACK_QUOTE);
        keyMap.put("OPEN_BRACKET", KeyEvent.VK_OPEN_BRACKET);
        keyMap.put("CLOSE_BRACKET", KeyEvent.VK_CLOSE_BRACKET);

    }
    public static Map<String, Integer> getKeyMap(){
        if(instance == null){
            instance = new InitializeMap();
            instance.initializeKeyMap();
        }
        return keyMap;
    }

}

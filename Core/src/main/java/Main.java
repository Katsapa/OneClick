import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.List;

public class Main implements NativeKeyListener {


    public static void main(String[] args) {
        createMacro();
        RegisterNativeHook nativeHook = RegisterNativeHook.getInstance();
        GlobalScreen.addNativeKeyListener(nativeHook);

    }

    public static void createMacro(){
        MacroBuilder builder = new MacroBuilder();
        HotkeyTrigger trigger = new HotkeyTrigger("RB");
        List<Action> actions = new ArrayList<>();
        actions.add(new KeyboardAction("R", "press"));
        actions.add(new TextInputAction("text"));
        actions.add(new KeyboardRelease("R"));

        builder.setName("First macro");
        builder.setDescription("test");
        builder.setTrigger(trigger);
        builder.setActions(actions);
        Macro macro = builder.getResult();
        MacroManager.getMacroManager().addMacro(macro);
    }
}

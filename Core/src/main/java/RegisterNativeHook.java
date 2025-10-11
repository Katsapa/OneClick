import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class RegisterNativeHook implements NativeKeyListener {

    private static RegisterNativeHook instance;
    private RegisterNativeHook(){}
    private final StringBuilder currentCombination = new StringBuilder();
    private final MacroManager macroManager = MacroManager.getMacroManager();

    public static RegisterNativeHook getInstance(){
        if(instance == null){
            synchronized (RegisterNativeHook.class){
                if(instance == null){
                    Logger logger = Logger.getLogger(RegisterNativeHook.class.getPackage().getName());
                    logger.setLevel(Level.OFF);
                    logger.setUseParentHandlers(false);
                    try{
                        GlobalScreen.registerNativeHook();
                        System.out.println("Successfully to register the hook");
                    }catch (NativeHookException e) {
                        System.out.println("Failed to register the hook: " + e.getMessage());
                        System.exit(1);
                    }
                    instance = new RegisterNativeHook();
                }
            }
        } return instance;
    }
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {

        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
        currentCombination.append(keyText);

        if(macroManager.getListOfTriggers().contains(currentCombination.toString())){
            System.out.println("Нажата комбинация: " + currentCombination);

            PlaybackService service = new PlaybackService(MacroManager.getMacroManager().getMacroForTrigger(currentCombination.toString()));
            Thread thread = new Thread(service);
            thread.start();
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            System.out.println("Выход по ESC...");
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }


    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
        currentCombination.delete(0, currentCombination.length());
        System.out.println("Отпущена: " + keyText);
    }

//    @Override
//    public void nativeKeyTyped(NativeKeyEvent e) {
//        // Вызывается при вводе символа (учитывает раскладку)
//        System.out.println("Введен символ: '" + e.getKeyChar() + "'");
//    }
}



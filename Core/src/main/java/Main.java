import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Main implements NativeKeyListener {
    public static void main(String[] args) {
        RegisterNativeHook nativeHook = RegisterNativeHook.getInstance();
        GlobalScreen.addNativeKeyListener(nativeHook);
    }

}

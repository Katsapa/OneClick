
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github. kwhat.jnativehook.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalScreenMy implements NativeKeyListener {

    public static void main(String[] args) {
        // Отключаем логирование JNativeHook для чистоты вывода
        Logger logger = Logger.getLogger(GlobalScreenMy.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);

        try {
            System.out.println("Регистрируем нативный хук...");

            // Этот вызов загружает нативную библиотеку и устанавливает системный хук
            GlobalScreen.registerNativeHook();

            System.out.println("Хук успешно зарегистрирован!");

        } catch (NativeHookException ex) {
            System.err.println("Ошибка регистрации хука: " + ex.getMessage());
            System.exit(1);
        }

        // Добавляем слушатель событий
        GlobalScreen.addNativeKeyListener(new GlobalScreenMy());

        System.out.println("Слушатель активен. Нажимайте клавиши...");
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        // Вызывается при НАЖАТИИ клавиши
        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
        System.out.println("Нажата: " + keyText + " (код: " + e.getKeyCode() + ")");

        // Пример обработки специальных клавиш
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
        // Вызывается при ОТПУСКАНИИ клавиши
        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
        System.out.println("Отпущена: " + keyText);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // Вызывается при вводе символа (учитывает раскладку)
        System.out.println("Введен символ: '" + e.getKeyChar() + "'");
    }

}

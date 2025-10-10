import java.awt.*;
import java.util.List;

public class KeyboardAction implements Action{
    Robot robot;
    String value;
    String mod;
    int sec;

    private final Object lock = new Object();
    private boolean signal = false;
    int keyCode;
    static List<String> mods = List.of("continued", "click", "temp");


    KeyboardAction(String value, String mod) {
        if(!mods.contains(mod)){
            System.out.println("Non-exist mod");
            return;
        }
        this.mod = mod;
        this.value = value;
        this.robot = RobotController.getRobot();
    }
    KeyboardAction(String value, String mod, int sec) {
        if(!mods.contains(mod)){
            return;
        }
        this.mod = mod;
        this.value = value;
        this.robot = RobotController.getRobot();
        this.sec = sec;
    }

    public void execute() throws InterruptedException{
        if(InitializeMap.getKeyMap().containsKey(value.toUpperCase())){
            if(mod.equals(mods.get(0))){
                this.continuousExecute();
            } else if(mod.equals(mods.get(1))){
                this.clickExecute();
            } else {
                this.tempClicking(sec);
            }
        } else {
            System.out.println("Non-exist key");
        }
    }

    private void tempClicking(int sec){
        keyCode = InitializeMap.getKeyMap().get(value);
        robot.keyPress(keyCode);
        try{
            Thread.currentThread().sleep(sec);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        robot.keyRelease(keyCode);
    }

    private void clickExecute(){
        keyCode = InitializeMap.getKeyMap().get(value);
        robot.keyPress(keyCode);

        robot.keyRelease(keyCode);
    }

    private void continuousExecute() throws InterruptedException{
        keyCode = InitializeMap.getKeyMap().get(value);
        robot.keyPress(keyCode);

        synchronized (lock){
            while(!signal){
                lock.wait();
            }
        }
        robot.keyRelease(keyCode);
    }

    public void signalForRelease(){
        synchronized (lock){
            signal = true;
            lock.notify();
        }
    }

    public boolean getSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }
    public Object getLock(){
        return lock;
    }
}

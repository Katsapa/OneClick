public class DelayAction implements Action{
    private int delayInMillis;
    DelayAction(int sec){
        delayInMillis = sec;
    }
    public void execute() {
        RobotAndListController.getRobot().delay(delayInMillis);
    }
}

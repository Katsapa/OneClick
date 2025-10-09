public class DelayAction implements Action{
    private int delayInMillis;
    DelayAction(int sec){
        delayInMillis = sec;
    }
    public void execute() {
        try{
            Thread.currentThread().sleep(delayInMillis);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

}

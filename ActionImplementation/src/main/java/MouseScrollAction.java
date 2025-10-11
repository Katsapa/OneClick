public class MouseScrollAction implements Action{
    private int amt;
    MouseScrollAction(int amt){
        this.amt = amt;
    }

    public void execute(){
        RobotAndListController.getRobot().mouseWheel(amt);
    }


    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }
}

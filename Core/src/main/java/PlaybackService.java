

public class PlaybackService implements Runnable{
    private Macro macro;

    PlaybackService(Macro macro){
        this.macro = macro;
    }
    PlaybackService(){}

    public void run() {
//        for(Action action : macro.getActions()){
//            try{
//                action.execute();
//            } catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
        KeyboardAction press1 = new KeyboardAction("WINDOWS", "continued");
        KeyboardAction press2 = new KeyboardAction("R", "click");
        try{
            press1.execute();
            press2.execute();
            press1.getLock().notify();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}

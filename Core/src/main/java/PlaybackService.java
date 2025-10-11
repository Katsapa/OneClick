

public class PlaybackService implements Runnable{
    private Macro macro;

    PlaybackService(Macro macro){
        this.macro = macro;
    }

    public void run() {
        for(Action action : macro.getActions()){
            try{
                action.execute();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}

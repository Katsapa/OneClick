import java.util.List;

public class MacroManager {
    private static MacroManager macroManager = new MacroManager();
    private List<Macro> listOfMacro;
    private List<String> listOfTriggers;
    public void addMacro(Macro m){
        listOfMacro.add(m);
    }

    public void removeMacro(Macro m){
        listOfMacro.remove(m);
    }

    public List<Macro> getListOfMacro(){
        return listOfMacro;
    }

    public void startMacro(Macro m){
        m.getTrigger().activate();
    }

    public void stopMacro(Macro m){
        m.getTrigger().deactivate();
    }

    public void saveMacro(){
        //доделать с использованием сериализации
    }

    public static synchronized MacroManager getMacroManager(){
        return macroManager;
    }

    public List<String> getListOfTriggers(){
        listOfMacro.forEach(m -> listOfTriggers.add(m.getTrigger().getCombination()));
        return listOfTriggers;
    }

}

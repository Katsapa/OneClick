
import java.util.ArrayList;
import java.util.List;

public class MacroManager {
    private final static MacroManager macroManager = new MacroManager();
    private List<Macro> listOfMacro = new ArrayList<>();
    private List<String> listOfTriggers = new ArrayList<>();
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

    public Macro getMacroForTrigger(String combination){
        for(Macro m : listOfMacro){
            if(m.getTrigger().getCombination().equals(combination)){
                return m;
            }
        }
        return null;
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
        listOfMacro.forEach(m -> listOfTriggers.add(m.getTrigger().getCombination().toUpperCase()));
        return listOfTriggers;
    }

}

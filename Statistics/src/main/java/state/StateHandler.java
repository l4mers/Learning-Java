package state;

import enums.State;
import main.Program;

import java.util.ArrayList;

public class StateHandler {
    private final ArrayList<SourceState> stateList = new ArrayList<>();
    private SourceState currentState;
    private final Program p;

    public StateHandler(Program p){
        this.p = p;
        setup();
    }
    public ArrayList<SourceState> getStateList() {
        return stateList;
    }

    public void changeState(State state){
        stateList.forEach(e->{
            if(e.getState().equals(state)){
                currentState = e;
                currentState.setVisible(true);
            }else{
                e.setVisible(false);
            }
        });
    }

    public SourceState getCurrentState() {
        return currentState;
    }
    private void setup(){
        stateList.add(new InitState(p));
        stateList.add(new ReportOne(p));
        stateList.add(new ReportTwo(p));
        currentState = stateList.get(0);
    }
}

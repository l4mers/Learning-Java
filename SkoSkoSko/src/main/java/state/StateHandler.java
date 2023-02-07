package state;

import enums.State;
import main.Program;
import java.util.ArrayList;

public class StateHandler {

    private final ArrayList<StateTemplate> stateList = new ArrayList<>();
    private StateTemplate currentState;
    private final Program p;

    public StateHandler(Program p){
        this.p = p;
        setup();
    }
    public ArrayList<StateTemplate> getStateList() {
        return stateList;
    }

    public void changeState(State state){
        stateList.forEach(e->{
            if(e.getState().equals(state)){
                StateTemplate s = currentState;
                currentState = e;
                currentState.setLast(s.getState());
                currentState.setVisible(true);
            }else{
                e.setVisible(false);
            }
        });
    }
    public StateTemplate getCurrentState() {
        return currentState;
    }
    private void setup(){
        stateList.add(new InitState(p));
        stateList.add(new LoginState(p));
        stateList.add(new ApplyState(p));
        stateList.add(new CustomerState(p));
        stateList.add(new BrowseState(p));
        stateList.add(new ListState(p));
        stateList.add(new ListProduct(p));
        stateList.add(new ProductState(p));
        stateList.add(new MyOrderState(p));
        stateList.add(new SelectedOrderState(p));
        stateList.add(new CartState(p));
        stateList.add(new ProfileState(p));
        currentState = stateList.get(0);
    }
}

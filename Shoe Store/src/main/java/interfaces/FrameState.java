package interfaces;

import enums.State;

public interface FrameState {
    State nextState();
    State lastState();
    State getState();

    void setNext(State state);
    void setLast(State state);

}

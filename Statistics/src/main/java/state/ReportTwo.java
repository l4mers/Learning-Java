package state;

import enums.State;
import main.Program;

public class ReportTwo extends SourceState{
    public ReportTwo(Program p) {
        super(p);

        state = State.REPORT_TWO;

        setup();
    }
    public void setup(){
        standardSettings();
        standardListSetting();
    }
}

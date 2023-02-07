package main;

import components.ComponentHandler;
import database.DatabaseHandler;
import state.StateHandler;

public class Program {
    final private DatabaseHandler dh = new DatabaseHandler("sko", "root", "mysql");
    final private StateHandler sh = new StateHandler(this);
    final private Window window = new Window(this);
    final private ComponentHandler ch = new ComponentHandler(dh);
    public StateHandler getSh() {
        return sh;
    }
    public DatabaseHandler getDh() {
        return dh;
    }
    public ComponentHandler getCh(){return ch;}
}

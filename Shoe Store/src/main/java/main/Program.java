package main;

import components.Cart;
import components.ComponentHandler;
import database.DatabaseHandler;
import state.StateHandler;

public class Program{
    final private DatabaseHandler dh = new DatabaseHandler("sko", "root", "mysql");
    final private StateHandler sh = new StateHandler(this);
    final private Window window = new Window(this);
    final private ComponentHandler ch = new ComponentHandler();
    final private Cart cart = new Cart(this);
    public Window getWindow() {
        return window;
    }
    public StateHandler getSh() {
        return sh;
    }
    public DatabaseHandler getDh() {
        return dh;
    }
    public ComponentHandler getCh(){return ch;}
    public Cart getCart() {return cart;}
}

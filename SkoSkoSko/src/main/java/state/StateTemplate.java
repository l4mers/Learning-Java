package state;

import components.Item;
import components.Order;
import components.Shoe;
import enums.State;
import interfaces.FrameState;
import main.Program;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StateTemplate extends JPanel implements FrameState {

    final protected int size = 96;
    final protected int width = size * 8;
    final protected int height = size * 8;
    final protected Program p;

    protected DefaultListModel<String> stringListModel;
    protected DefaultListModel<Shoe> shoeListModel;
    protected DefaultListModel<Order> orderListModel;
    protected DefaultListModel<Item> itemListModel;
    protected DefaultComboBoxModel<String> stringBoxModel;
    protected DefaultComboBoxModel<Integer> intBoxModel;
    protected JList<Shoe> productList;
    protected JList<Order> orderList;
    protected JList<Item> itemList;
    protected JList<String> stringList;
    protected State state, nextState, lastState;
    protected JLabel productName;
    protected JLabel message;
    protected JLabel price;
    protected JComboBox<Integer> shoeSize;
    protected JComboBox<String> color;
    protected JLabel welcome;

    StateTemplate(Program p) {
        this.p = p;
        this.setVisible(false);
    }

    @Override
    public State nextState() {
        return nextState;
    }

    @Override
    public State lastState() {
        return lastState;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setNext(State state) {
        this.nextState = state;
    }

    @Override
    public void setLast(State state) {
        this.lastState = state;
    }

    protected void standardSettings(){
        this.setBounds(0, 0, width, height - size);
        this.setFocusable(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);
    }
    protected void updateProductList(ArrayList<Shoe> shoeList){
        p.getSh().getCurrentState().shoeListModel.removeAllElements();
        shoeList.forEach(e->p.getSh().getCurrentState().shoeListModel.addElement(e));
    }
    protected void updateList(ArrayList<String> list){
        p.getSh().getCurrentState().stringListModel.removeAllElements();
        list.forEach(e->p.getSh().getCurrentState().stringListModel.addElement(e));
    }
    protected void updateOrderList(ArrayList<Order> orderList){
        p.getSh().getCurrentState().orderListModel.removeAllElements();
        orderList.forEach(e->p.getSh().getCurrentState().orderListModel.addElement(e));
    }
    protected void updateSelectedProduct(Shoe shoe){
        p.getCh().setShoe(shoe);
        intBoxModel.removeAllElements();
        stringBoxModel.removeAllElements();
        productName.setText(shoe.name());
        price.setText(String.valueOf(shoe.price()));
        p.getDh().getShoeSizeByShoeID(shoe.id()).forEach(e->intBoxModel.addElement(e));
        p.getDh().getShoeColorByShoeID(shoe.id()).forEach(e->stringBoxModel.addElement(e));
    }
    public void updateItemList(ArrayList<Item> items){
        itemListModel.removeAllElements();
        items.forEach(e-> itemListModel.addElement(e));
        price.setText("Total: " + (items
                .stream()
                .mapToInt(Item::getPrice)
                .reduce(0, Integer::sum)));
    }
    protected void toggleCartButton(){
        p.getWindow().getCart().setVisible(p.getCart().orderExist());
    }
    protected void welcomeMessage(){
        welcome.setText("Welcome " + p.getCh().getCustomer().getName());
    }
}

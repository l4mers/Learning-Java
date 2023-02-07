package state;

import components.*;
import enums.State;
import main.Program;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SourceState extends JPanel {

    final protected int size = 96;
    final protected int width = size * 8;
    final protected int height = size * 8;

    protected State state;
    protected DefaultListModel<String> stringListModel;
    protected JList<String> stringList;
    Program p;

    public SourceState(Program p){
        this.p = p;
        this.setVisible(false);
        state = State.SOURCE_STATE;
    }
    protected void standardListSetting(){
        stringListModel = new DefaultListModel<>();
        stringList = new JList<>(stringListModel);
        JScrollPane jpString = new JScrollPane();
        jpString.setViewportView(stringList);
        jpString.setBounds(width / 2 - 200, size, 400, height - size * 4);
        this.add(jpString);
    }
    protected void standardSettings(){
        this.setBounds(0, 0, width, height - size);
        this.setFocusable(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);
    }

    public State getState() {
        return state;
    }

    protected void updateCustomerList(List<OrderItem> itemList) {
        stringListModel.removeAllElements();
        itemList.forEach(e -> stringListModel.addElement(e.order().getCustomer().toString()));
    }
    protected void updateOrderCountList(List<OrderCount> orderList){
        stringListModel.removeAllElements();
        orderList.forEach(e -> stringListModel.addElement(e.toString()));
    }
    protected void updateCustomerValue(List<CustomerValue> orderList){
        stringListModel.removeAllElements();
        orderList.forEach(e -> stringListModel.addElement(e.toString()));
    }
    protected void updateCountyValue(List<CountyValue> orderList){
        stringListModel.removeAllElements();
        orderList.forEach(e -> stringListModel.addElement(e.toString()));
    }
    protected void updateShoeTopList(List<TopShoeList> shoeList){
        stringListModel.removeAllElements();
        shoeList.forEach(te -> stringListModel.addElement(te.toString()));
    }
}

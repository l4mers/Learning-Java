package state;

import enums.State;
import main.Program;

import javax.swing.*;

public class ListState extends StateTemplate {
    ListState(Program p) {
        super(p);

        state = State.LIST_STATE;

        setup();
    }
    private void setup(){
        standardSettings();

        stringListModel = new DefaultListModel<>();
        stringList = new JList<>(stringListModel);
        JScrollPane jpString = new JScrollPane();
        jpString.setViewportView(stringList);
        jpString.setBounds(width / 2 - 200, size, 400, height - size * 4);
        this.add(jpString);

        JButton b1 = new JButton("Select");
        b1.setBounds(width / 2 - 200, height - size * 3 + 30, 100, 30);
        b1.addActionListener(e->{
            if(stringList.getSelectedIndex() != -1){
                switch (p.getSh().getCurrentState().nextState()){
                    case CATEGORY_STATE ->{
                        p.getSh().changeState(State.LIST_PRODUCT_STATE);
                        updateProductList(p.getDh().getAllProductsByCategory(stringList.getSelectedValue()));
                        p.getSh().getCurrentState().setLast(State.CATEGORY_STATE);
                    }
                    case BRAND_STATE ->{
                        p.getSh().changeState(State.LIST_PRODUCT_STATE);
                        updateProductList(p.getDh().getAllProductsByBrand(stringList.getSelectedValue()));
                        p.getSh().getCurrentState().setLast(State.BRAND_STATE);
                    }
                }
            }
        });
        this.add(b1);

        JButton b2 = new JButton("Back");
        b2.setBounds(width / 2 + 100, height - size * 3 + 30, 100, 30);
        b2.addActionListener(e ->{
            p.getSh().changeState(State.SELECT_STATE);
        });
        this.add(b2);
    }
}

package state;

import enums.State;
import main.Program;

import javax.swing.*;

public class BrowseState extends StateTemplate {
    BrowseState(Program p) {
        super(p);

        state = State.SELECT_STATE;

        setup();
    }
    private void setup(){
        standardSettings();

        JLabel titel = new JLabel("How would you like to browse our products?");
        titel.setBounds(width / 2 - 125, size, 300, 30);
        this.add(titel);

        JButton b1 = new JButton("By Categories");
        b1.setBounds(width / 2 - 100,height / 2 - size * 2,200,50);
        b1.addActionListener(e -> {
            p.getSh().changeState(State.LIST_STATE);
            p.getSh().getCurrentState().setNext(State.CATEGORY_STATE);
            p.getSh().getCurrentState().updateList(p.getDh().getAllCategories());
        });
        this.add(b1);

        JButton b2 = new JButton("By Brand");
        b2.setBounds(width / 2 - 100,height / 2 - size,200,50);
        b2.addActionListener(e->{
            p.getSh().changeState(State.LIST_STATE);
            p.getSh().getCurrentState().setNext(State.BRAND_STATE);
            p.getSh().getCurrentState().updateList(p.getDh().getAllBrand());
        });
        this.add(b2);

        JButton b3 = new JButton("Browse All");
        b3.setBounds(width / 2 - 100,height / 2 ,200,50);
        b3.addActionListener(e ->{
            p.getSh().changeState(State.LIST_PRODUCT_STATE);
            p.getSh().getCurrentState().setNext(State.PRODUCT_STATE);
            p.getSh().getCurrentState().updateProductList(p.getDh().getAllProducts());
        });
        this.add(b3);

        JButton b4 = new JButton("Back");
        b4.setBounds(width / 2 - 100,height / 2 + size,200,50);
        b4.addActionListener(e ->{
            if(p.getCh().getCustomer() == null){
                p.getSh().changeState(State.INIT_STATE);
            } else {
                p.getSh().changeState(State.CUSTOMER_STATE);
            }
        });
        this.add(b4);

    }
}

package state;

import enums.State;
import main.Program;

import javax.swing.*;

public class ListProduct extends StateTemplate {
    ListProduct(Program p) {
        super(p);
        state = State.LIST_PRODUCT_STATE;

        setup();
    }
    private void setup(){
        standardSettings();

        shoeListModel = new DefaultListModel<>();
        productList = new JList<>(shoeListModel);
        JScrollPane jpProduct = new JScrollPane();
        jpProduct.setViewportView(productList);
        jpProduct.setBounds(width / 2 - 200, size, 400, height - size * 4);
        this.add(jpProduct);

        JButton b1 = new JButton("Select");
        b1.setBounds(width / 2 - 200, height - size * 3 + 30, 100, 30);
        b1.addActionListener(e->{
            if(productList.getSelectedIndex() != -1){
                toggleCartButton();
                p.getSh().changeState(State.PRODUCT_SELECTION_STATE);
                p.getSh().getCurrentState().updateSelectedProduct(shoeListModel.getElementAt(productList.getSelectedIndex()));
            }
        });
        this.add(b1);

        JButton b2 = new JButton("Back");
        b2.setBounds(width / 2 + 100, height - size * 3 + 30, 100, 30);
        b2.addActionListener(e ->{
            switch (p.getSh().getCurrentState().lastState()){
                case CATEGORY_STATE -> {
                    p.getSh().changeState(State.LIST_STATE);
                    p.getSh().getCurrentState().updateList(p.getDh().getAllCategories());
                }
                case BRAND_STATE -> {
                    p.getSh().changeState(State.LIST_STATE);
                    p.getSh().getCurrentState().updateList(p.getDh().getAllBrand());
                }
                default -> p.getSh().changeState(State.SELECT_STATE);
            }
        });
        this.add(b2);

    }
}

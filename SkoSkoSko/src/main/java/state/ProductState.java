package state;

import components.Order;
import components.StockProduct;
import enums.State;
import utility.Intermediary;
import main.Program;
import interfaces.ShoeConversion;

import javax.swing.*;
import java.time.LocalDate;

public class ProductState extends StateTemplate {

    ShoeConversion sc;
    ProductState(Program p) {
        super(p);

        state = State.PRODUCT_SELECTION_STATE;

        setup();
    }
    private void setup(){
        standardSettings();

        sc = (shoe, size, color) ->{
            int id = p.getDh().getIdFromStockProduct(shoe.id(), size, color);
            if(id != 0){
                return new StockProduct(id, shoe, size, color);
            }
            return null;
        };


        message = new JLabel("This item is out of stock");
        message.setBounds(width / 2 - 70, height - size * 3, 400, 25);
        message.setVisible(false);
        this.add(message);

        price = new JLabel();
        price.setBounds(width / 2 - 200, size + 30, 400, 25);
        this.add(price);

        productName = new JLabel();
        productName.setBounds(width / 2 - 200, size, 400, 25);
        this.add(productName);

        intBoxModel = new DefaultComboBoxModel<>();
        shoeSize = new JComboBox<>(intBoxModel);
        shoeSize.setBounds(width / 2 - 200, size * 3, 100, 25);
        this.add(shoeSize);

        stringBoxModel = new DefaultComboBoxModel<>();
        color = new JComboBox<>(stringBoxModel);
        color.setBounds(width / 2 + 100, size * 3, 100, 25);
        this.add(color);

        JButton b1 = new JButton("Add");
        b1.setBounds(width / 2 - 200, height - size * 3 + 30, 100, 30);
        b1.addActionListener(e->{
            if(p.getDh().shoeNotInStock(p.getCh().getShoe().id(),
                    stringBoxModel.getElementAt(color.getSelectedIndex()),
                    intBoxModel.getElementAt(shoeSize.getSelectedIndex()))){
                if(p.getCh().getCustomer() == null){
                    p.getSh().changeState(State.LOGIN_STATE);
                } else {
                    if(p.getCart().orderExist()){
                        addItemToCart();
                    }else{
                        p.getCart().setOrder(new Order(p.getCh().getCustomer(), LocalDate.now()));
                        addItemToCart();
                    }
                    p.getWindow().getCart().setVisible(true);
                }
            } else{
                message.setVisible(true);
            }
        });
        this.add(b1);

        JButton b2 = new JButton("Back");
        b2.setBounds(width / 2 + 100, height - size * 3 + 30, 100, 30);
        b2.addActionListener(e ->{
            message.setVisible(false);
            if(lastState != State.LOGIN_STATE){
                p.getSh().changeState(p.getSh().getCurrentState().lastState);
            } else {
                p.getSh().changeState(State.SELECT_STATE);
            }
        });
        this.add(b2);
    }
    private void addItemToCart(){
        p.getCart().addItem(new Intermediary().stockProductToItem(
                sc,
                p.getCart().getOrder(),
                p.getCh().getShoe(),
                intBoxModel.getElementAt(shoeSize.getSelectedIndex()),
                stringBoxModel.getElementAt(color.getSelectedIndex())));
    }
}

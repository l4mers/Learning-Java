package state;

import enums.State;
import main.Program;
import javax.swing.*;

public class CartState extends StateTemplate {
    CartState(Program p) {
        super(p);

        state = State.CART_STATE;

        setup();
    }
    private void setup(){
        standardSettings();

        itemListModel = new DefaultListModel();
        itemList = new JList<>(itemListModel);
        JScrollPane jpString = new JScrollPane();
        jpString.setViewportView(itemList);
        jpString.setBounds(width / 2 - 200, size, 400, height - size * 4);
        this.add(jpString);

        price = new JLabel();
        price.setBounds(width / 2 - 200, height - size * 3, 400, 25);
        this.add(price);

        JButton b1 = new JButton("Buy");
        b1.setBounds(width / 2 - 200, height - size * 3 + 30, 100, 30);
        b1.addActionListener(e->{
            p.getCart().getOrder().setId(p.getCart().createOrder());
            p.getCart().sendItemList();
            p.getCart().emptyCart();
            JOptionPane.showMessageDialog(null, "Order have been placed");
            p.getSh().changeState(State.CUSTOMER_STATE);
        });
        this.add(b1);

        JButton b2 = new JButton("Back");
        b2.setBounds(width / 2 + 100, height - size * 3 + 30, 100, 30);
        b2.addActionListener(e ->{
            p.getSh().changeState(State.SELECT_STATE);
        });
        this.add(b2);

        JButton b3 = new JButton("+");
        b3.setBounds(width / 2 + 5, height - size * 2, 50, 50);
        b3.addActionListener(e->{
            if(itemList.getSelectedIndex() != -1){
                if(p.getDh().shoeNotInStock(itemListModel.getElementAt(itemList.getSelectedIndex()).getProduct())){
                    itemListModel.getElementAt(itemList.getSelectedIndex()).increaseQuantity();

                    p.getCart().addItem(itemListModel.getElementAt(itemList.getSelectedIndex()));

                    String[] split = price.getText().split(" ");
                    int newPrice = Integer.parseInt(split[1]) + itemListModel.getElementAt(itemList.getSelectedIndex()).getProduct().shoe().price();
                    price.setText("Total: " + newPrice);
                }else{
                    JOptionPane.showMessageDialog(null, "No more item in stock");
                }
            }
        });
        this.add(b3);

        JButton b4 = new JButton("-");
        b4.setBounds(width / 2 - 55, height - size * 2, 50, 50);
        b4.addActionListener(e ->{
            if(itemList.getSelectedIndex() != -1){
                itemListModel.getElementAt(itemList.getSelectedIndex()).decreaseQuantity();

                p.getCart().getItemList().remove(itemListModel.getElementAt(itemList.getSelectedIndex()));

                String[] split = price.getText().split(" ");
                int newPrice = Integer.parseInt(split[1]) - itemListModel.getElementAt(itemList.getSelectedIndex()).getProduct().shoe().price();
                price.setText("Total: " + newPrice);

                if(itemListModel.getElementAt(itemList.getSelectedIndex()).getQuantity() < 1){
                    itemListModel.remove(itemList.getSelectedIndex());
                }
            }
        });
        this.add(b4);
    }
}

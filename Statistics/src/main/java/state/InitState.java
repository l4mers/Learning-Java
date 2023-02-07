package state;

import enums.State;
import interfaces.CompareCustomerId;
import interfaces.CompareInt;
import interfaces.CompareString;
import interfaces.OrderCount;
import main.Program;
import tools.Search;

import javax.swing.*;

public class InitState extends SourceState{

    public InitState(Program p) {
        super(p);
        state = State.INIT_STATE;
        this.setVisible(true);
        setup();
    }
    private void setup(){
        standardSettings();

        JButton b1 = new JButton("Rapport 1");
        b1.setBounds(width / 2 - 100,height / 2 - size * 3,200,50);
        b1.addActionListener(e -> {
            p.getSh().changeState(State.REPORT_ONE);
            p.getCh().setOrderItemList(p.getDh().getOrderItems());
        });
        this.add(b1);

        OrderCount countOrders = ((customer, items) ->{
            if(items.getCustomer().id() == customer.id()){
                return 1;
            }
            return 0;
        });

        JButton b2 = new JButton("Rapport 2");
        b2.setBounds(width / 2 - 100,height / 2 - size * 2,200,50);
        b2.addActionListener(e->{
            p.getSh().changeState(State.REPORT_TWO);
            p.getSh().getCurrentState().updateOrderCountList(new Search().convertOrderListToOrderCountList(p.getDh().getOrderList(), countOrders));
        });
        this.add(b2);

        CompareCustomerId maxValue = ((item, customer) ->{
            if(customer.id() == item.order().getCustomer().id()){
                return item.shoe().price() * item.quantity();
            }
            return 0;
        });

        JButton b3 = new JButton("Rapport 3");
        b3.setBounds(width / 2 - 100,height / 2 - size,200,50);
        b3.addActionListener(e ->{
            p.getSh().changeState(State.REPORT_TWO);
            p.getSh().getCurrentState().updateCustomerValue(new Search().convertOrderItemListToCustomerValueList(p.getDh().getOrderItems(), maxValue));
        });
        this.add(b3);

        CompareString topCounty = ((item, string)->{
            if(item.order().getCustomer().county().equals(string)){
                return item.shoe().price() * item.quantity();
            }
            return 0;
        });

        JButton b4 = new JButton("Rapport 4");
        b4.setBounds(width / 2 - 100,height / 2,200,50);
        b4.addActionListener(e ->{
            p.getSh().changeState(State.REPORT_TWO);
            p.getSh().getCurrentState().updateCountyValue(new Search().convertOrderItemListToCountyValueList(p.getDh().getOrderItems(), topCounty));
        });
        this.add(b4);

        CompareInt topList = ((item, shoe) ->{
            if(shoe.id() == item.shoe().id()){
                return item.quantity();
            }
            return 0;
        });

        JButton b5 = new JButton("Rapport 5");
        b5.setBounds(width / 2 - 100,height / 2 + size,200,50);
        b5.addActionListener(e -> {
            p.getSh().changeState(State.REPORT_TWO);
            p.getSh().getCurrentState().updateShoeTopList(new Search().convertOrderItemListToTopShoeList(p.getDh().getOrderItems(), topList));
        });
        this.add(b5);
    }
}

package state;

import enums.State;
import main.Program;

import javax.swing.*;

public class ReportOne extends SourceState{
    public ReportOne(Program p) {
        super(p);

        state = State.REPORT_ONE;

        setup();
    }
    private void setup(){
        standardSettings();

        JLabel lSearch = new JLabel("Search");
        lSearch.setBounds(width / 2 - 200, 25, 400, 25);
        this.add(lSearch);

        JTextPane searchField = new JTextPane();
        searchField.setBounds(width / 2 - 200, 50, 400, 25);
        this.add(searchField);

        standardListSetting();

        JButton search = new JButton("Search");
        search.setBounds(width / 2 - 200, height - size * 3 + 30, 100, 30);
        search.addActionListener(e ->{
            if(!searchField.getText().isEmpty()){
                if(searchField.getText().matches("[0-9]+")){
                    updateCustomerList(new tools.Search().search((orderItem, word) -> orderItem.shoe().size().equals(word),
                            p.getCh().getOrderItems(), searchField.getText()));
                }else if(p.getCh().getColors().contains(searchField.getText())){
                    updateCustomerList(new tools.Search().search((orderItem, word) -> orderItem.shoe().color().equalsIgnoreCase(word),
                            p.getCh().getOrderItems(), searchField.getText()));
                }else{
                    updateCustomerList(new tools.Search().search((orderItem, word) -> orderItem.shoe().brand().equalsIgnoreCase(word),
                            p.getCh().getOrderItems(), searchField.getText()));
                }
                searchField.setText("");
            }
        });
        this.add(search);
    }
}

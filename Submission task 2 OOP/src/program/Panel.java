package program;

import members.Member;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    final public int tile = 48;

    final public int width = tile * 16;

    final public int height = tile * 12;

    final private Event event = new Event(this);

    private Label textLabel, message;
    private Button search, back;
    private JTextField searchText;
    private TextArea info;

    public Panel(){

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(238, 204, 204));
        this.setFocusable(true);
        this.setLayout(null);

        setUpContent();

        mainScreen();

    }

    private void setUpContent() {

        textLabel = new Label("Search by name or ID");
        textLabel.setFont(new Font("", Font.BOLD, 14));
        textLabel.setBounds(width / 2 - 80, tile * 3, 160, 25);
        textLabel.setVisible(true);
        this.add(textLabel);

        message = new Label("");
        message.setFont(new Font("", Font.BOLD, 14));
        message.setBounds(width / 2 - 100, tile * 7, 160, 25);
        message.setVisible(false);
        this.add(message);

        search = new Button("Search");
        search.setBounds(width / 2 - 100, tile * 5, 200, 50);
        search.setFont(new Font("",Font.PLAIN, 18));
        search.setVisible(true);
        search.addActionListener(event);
        this.add(search);

        back = new Button("Back");
        back.setBounds(width - tile / 2 - 75, height - tile, 75, 25);
        back.setVisible(false);
        back.addActionListener(event);
        this.add(back);

        searchText = new JTextField();
        searchText.setBounds(width / 2 - 100, tile * 4, 200, 30);
        searchText.setVisible(true);
        this.add(searchText);

        info = new TextArea();
        info.setFont(new Font("", Font.BOLD, 14));
        info.setBounds(tile * 4, tile * 2, tile * 6, tile * 7);
        info.setVisible(false);
        this.add(info);

    }
    public void mainScreen() {

        textLabel.setVisible(true);

        search.setVisible(true);

        back.setVisible(false);

        searchText.setVisible(true);

        info.setVisible(false);

        message.setVisible(false);
    }
    private void infoScreen(){

        textLabel.setVisible(false);

        search.setVisible(false);

        back.setVisible(true);

        searchText.setVisible(false);

        info.setVisible(true);

        message.setVisible(false);
    }
    public void systemErrorMessage(String text){
        message.setText(text);
        message.setVisible(true);
    }
    public void displayMember(Member member){
        infoScreen();
        info.setText(member.toString());
    }
    public void resetSearchText(){
        searchText.setText(null);
    }
    public Button getSearch() {
        return search;
    }
    public Button getBack() {
        return back;
    }
    public String getSearchText() {
        return searchText.getText();
    }
}

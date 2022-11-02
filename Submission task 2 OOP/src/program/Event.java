package program;

import members.Member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Event implements ActionListener {

    final private Panel panel;
    final private Receptionist receptionist;

    public Event(Panel panel) {

        this.panel = panel;
        receptionist = new Receptionist("src/resources/members/members.txt", "src/resources/members/member_activities.txt");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(panel.getSearch().equals(e.getSource())){
            getMember();
        }
        else if(panel.getBack().equals(e.getSource())){
            panel.mainScreen();
            panel.resetSearchText();
        }
    }
    private void getMember() {
        String input = panel.getSearchText().trim();
        if(panel.getSearchText().equals("")){
            panel.systemErrorMessage("Empty value");
        }else{
            try {
                Member member = receptionist.searchMember(input);
                panel.displayMember(member);
                if(member.membershipDate().isAfter(LocalDate.now().minusYears(1))){
                    receptionist.activities(member);
                }
            }catch (IllegalArgumentException e){
                panel.systemErrorMessage(e.getMessage());
            }
        }
    }


}

package program;

import members.Member;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Receptionist {

    final private String activityFile;
    private final ArrayList<Member> memberList;

    public Receptionist(String fileToLoad, String activityFile){

        this.activityFile = activityFile;

        memberList = new ArrayList<>();

        loadMemberList(fileToLoad);
    }
    private void loadMemberList(String fileToLoad) {
        try(FileReader fr = new FileReader(Paths.get(fileToLoad).toFile());
            BufferedReader br = new BufferedReader(fr)) {
            int personCount = 0;
            String[] details = new String[2];
            String line;
            while ((line = br.readLine()) != null){
                switch (personCount){
                    case 0 ->
                        details = line.split(", ");
                    case 1 ->
                        memberList.add(new Member(details[0], details[1], LocalDate.parse(line)));
                }
                personCount++;
                if (personCount > 1){
                    personCount = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Member searchMember(String searchValue) {
        for (Member member:memberList) {
            if (member.name().equalsIgnoreCase(searchValue) || member.ID().equalsIgnoreCase(searchValue)){
                return member;
            }
        }
        throw new IllegalArgumentException("Invalid member");
    }
    public void activities(Member member) {
        try(FileWriter fw = new FileWriter(Paths.get(activityFile).toFile(), true);
            BufferedWriter bw = new BufferedWriter(fw)){

            bw.write("ID: " + member.ID() +
                    "\nName: " + member.name() +
                    "\nDate: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                    "\n\n");
            bw.flush();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

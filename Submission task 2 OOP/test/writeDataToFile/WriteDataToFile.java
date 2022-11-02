package writeDataToFile;

import members.Member;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteDataToFile {
    String memberActivityFile = "test/writeDataToFile/member_test.txt";

    Member dummy = new Member("1234567890", "Gustav Henriksson", LocalDate.now());

    @Test
    void testWriteToFile(){

        activities(dummy);

        try(FileReader fr = new FileReader(Paths.get(memberActivityFile).toFile());
            BufferedReader br = new BufferedReader(fr)) {
            int count = 0;
            String line;
            while ((line = br.readLine()) != null){
                switch (count){
                    case 0 -> {
                        assert (line.equals("ID: 1234567890"));
                    }
                    case 1 -> {
                        assert (line.equals("Name: Gustav Henriksson"));
                    }
                    case 2 -> {
                        assert (line.equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
                    }
                }
                count++;
            }
            assert (count == 3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void activities(Member member) {
        try(FileWriter fw = new FileWriter(Paths.get(memberActivityFile).toFile());
            BufferedWriter bw = new BufferedWriter(fw)){

            bw.write("ID: " + member.ID() +
                    "\nName: " + member.name() +
                    "\n" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            bw.flush();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

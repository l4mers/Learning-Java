package readDataToNewMember;

import members.Member;
import org.junit.jupiter.api.Test;
import program.Receptionist;

public class ReadDataToNewMember {

    Receptionist receptionist = new Receptionist("test/readDataToNewMember/member_test.txt", "test/readDataToNewMember/member_activities_test.txt");

    Member dummy;

    @Test
    void testSearchForMember(){
        dummy = receptionist.searchMember("Gustav Henriksson");
        assert (dummy != null);
        dummy = null;

        dummy = receptionist.searchMember("0123456789");
        assert (dummy != null);
        dummy = null;

        dummy = receptionist.searchMember("Christian Fudge");
        assert (dummy != null);
        dummy = null;

        dummy = receptionist.searchMember("9876543210");
        assert (dummy != null);
    }
}

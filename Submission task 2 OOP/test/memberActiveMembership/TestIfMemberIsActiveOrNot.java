package memberActiveMembership;

import members.Member;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestIfMemberIsActiveOrNot {

    LocalDate date = LocalDate.parse("2000-12-14");
    Member memberNotActive = new Member("0123456789", "dummy", date);

    LocalDate date2 = LocalDate.now();
    Member memberActive = new Member("9876543210", "ymmud", date2);


    @Test
    void testActiveMember(){
        assert (!memberNotActive.membershipDate().isAfter(LocalDate.now().minusYears(1)));
        assert (memberActive.membershipDate().isAfter(LocalDate.now().minusYears(1)));
    }
}

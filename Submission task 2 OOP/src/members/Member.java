package members;

import java.time.LocalDate;

public record Member(String ID, String name, LocalDate membershipDate) {

    @Override
    public String toString() {
        return "ID: " + ID +
                "\n" + "Name: " + name +
                "\n" + "Registered: " + membershipDate +
                "\n" + "Membership: " + activeMembership();
    }

    private String activeMembership() {
        if (membershipDate.isBefore(LocalDate.now().minusYears(1))) {
            return "Outdated ";
        } else {
            return "Active";
        }
    }
}

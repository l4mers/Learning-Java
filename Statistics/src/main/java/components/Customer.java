package components;

public record Customer(int id, String firstName, String lastName, String county) {

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + county;
    }
}

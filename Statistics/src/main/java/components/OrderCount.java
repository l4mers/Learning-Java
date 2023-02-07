package components;

public record OrderCount(int count, Customer customer) {

    @Override
    public String toString() {
        return customer.toString() + " " + count + " st ordrar";
    }
}

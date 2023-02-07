package components;

public record CustomerValue(int totalValue, Customer customer) {

    @Override
    public String toString() {
        return customer.firstName() + " " + customer.lastName() + " Total: " + totalValue;
    }
}

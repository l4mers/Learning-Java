package components;

public record Shoe(int id, String name, String brand, int price) {

    @Override
    public String toString() {
        return name + " (" + brand + ")   " + price + " :-";
    }
}

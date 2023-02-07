package components;

public record Shoe(int id, String name, String brand, String color, String size, int price) {

    @Override
    public String toString() {
        return name + " (" + brand + ")";
    }
}

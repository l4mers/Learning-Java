package components;

public record TopShoeList(Shoe shoe, int quantity) {

    @Override
    public String toString() {
        return shoe.toString() + " Antal: " + quantity;
    }
}

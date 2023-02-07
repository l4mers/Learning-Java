package components;

public record CountyValue(int total, String county) {

    @Override
    public String toString() {
        return county + " Total: " + total;
    }
}

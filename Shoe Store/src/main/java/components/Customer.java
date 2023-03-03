package components;

public class Customer {
    final private int id;
    final private String fName;
    final private String lName;
    final private String county;

    public Customer(int id, String fName, String lName, String county) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.county = county;
    }

    public String getName() {
        return this.fName;
    }

    public int getId() {
        return id;
    }
}

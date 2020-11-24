package persists;

public class Order {

    private int id;
    private String name;
    private int customerID;

    public Order(int id, String name, int customerID) {
        this.id = id;
        this.name = name;
        this.customerID = customerID;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}

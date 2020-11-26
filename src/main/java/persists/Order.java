package persists;

public class Order {

    private Integer id;
    private String description;
    private Integer customerID;
    private Boolean status;

    public Order(int id, String name, int customerID, boolean status) {
        this.id = id;
        this.description = name;
        this.customerID = customerID;
        this.status = status;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

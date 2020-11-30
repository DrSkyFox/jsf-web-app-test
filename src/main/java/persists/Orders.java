package persists;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "customer_id", nullable = false)
    private Integer customerID;

    @Column(name = "enabled", columnDefinition = "1")
    private Boolean status;

    public Orders(int id, String name, int customerID, boolean status) {
        this.id = id;
        this.description = name;
        this.customerID = customerID;
        this.status = status;
    }

    public Orders() {
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

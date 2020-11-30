package persists;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Range(min = 0)
    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled", columnDefinition = "1")
    private Boolean status;


    public Products(Integer id, String name, Float price, String description, Boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public Products() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

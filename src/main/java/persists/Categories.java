package persists;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @NotEmpty
    @Column(name = "nameCategory", nullable = false)
    private String nameCat;

    @Column(name = "enabled", nullable = false, columnDefinition = "tinyint DEFAULT 1")
    private Boolean status;

    public Categories(Integer id, String nameCat, Boolean status) {
        this.id = id;
        this.nameCat = nameCat;
        this.status = status;
    }

    public Categories() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", nameCat='" + nameCat + '\'' +
                ", status=" + status +
                '}';
    }
}

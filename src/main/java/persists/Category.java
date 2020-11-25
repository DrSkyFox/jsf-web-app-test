package persists;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Category {

    private Long id;


    @NotNull(message = "Field cant empty")
    @Size(min = 3, max = 32, message = "Fileds wolrd must in 3 to 32 symbols")
    private String nameCat;


    private Boolean status;

    public Category(Long id, String nameCat, Boolean status) {
        this.id = id;
        this.nameCat = nameCat;
        this.status = status;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}

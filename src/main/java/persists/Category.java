package persists;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Category {

    private Integer id;


    @NotNull(message = "Field cant empty")
    @Size(min = 3, max = 32, message = "Fileds wolrd must in 3 to 32 symbols")
    private String nameCat;


    private Boolean status;

    public Category(Integer id, String nameCat, Boolean status) {
        this.id = id;
        this.nameCat = nameCat;
        this.status = status;
    }

    public Category() {
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
}

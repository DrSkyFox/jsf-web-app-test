package persists;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Named
@RequestScoped
public class Category {

    private Long iD;


    @NotNull(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 32, message = "Поле должно содержать от 3 до 32 символов")
    private String nameCat;


    private Boolean status;

    public Category(Long iD, String nameCat, Boolean status) {
        this.iD = iD;
        this.nameCat = nameCat;
        this.status = status;
    }

    public Category() {
    }

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
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

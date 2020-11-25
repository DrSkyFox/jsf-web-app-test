package persists;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Named
@RequestScoped
public class Category {

    private int iD;


    @NotNull(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 32, message = "Поле должно содержать от 3 до 32 символов")
    private String nameCat;
    private int status;

    public Category(int iD, String name, int status) {
        this.iD = iD;
        this.nameCat = name;
        this.status = status;
    }

    public Category() {
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

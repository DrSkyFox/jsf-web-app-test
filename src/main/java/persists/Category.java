package persists;

public class Category {

    private int iD;
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

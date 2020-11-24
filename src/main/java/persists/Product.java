package persists;

public class Product {

    private int iD;
    private String name;
    private float price;
    private String description;


    public Product(int iD, String name, float price, String description) {
        this.iD = iD;
        this.name = name;
        this.price = price;
        this.description = description;
    }


    public Product() {
    }


    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package model;


public class Product {
    private int id;
    private String name;
    private String type;
    private String desc;
    private float unitprice;

    public Product(int id, String name, String type, String desc, float unitprice) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.unitprice = unitprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }
}

package model;

public class Flower {
    private int article;
    private String Name;
    private int price;
    private boolean available;

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean getAvailable() {
        return available;
    }

    public Flower(int article, String Name, int price) {
        this.article = article;
        this.Name = Name;
        this.price = price;
        available = true;
    }

    public Flower() {
    }
}

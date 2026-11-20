package boardGameLibrary.model;

public class Item {
    private int id;
    private String title;
    private String publisher;
    private double price;
    private boolean isExpansion;

    public Item(String title, String publisher, double price, boolean isExpansion) {
        this.id = 0;
        this.setTitle(title);
        this.setPublisher(publisher);
        this.setPrice(price);
        this.setExpansion(isExpansion);
    }
    
    public Item() {
        this.id = 0;
        this.setTitle("");
        this.setPublisher("");
        this.setPrice(0.0);
        this.setExpansion(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title must be a string.");
        }
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher != null) {
            this.publisher = publisher;
        } else {
            throw new IllegalArgumentException("Publisher must be a string.");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be a non-negative number.");
        }
    }

    public boolean isExpansion() {
        return isExpansion;
    }

    public void setExpansion(boolean expansion) {
        this.isExpansion = expansion;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", isExpansion=" + isExpansion +
                '}';
    }
}

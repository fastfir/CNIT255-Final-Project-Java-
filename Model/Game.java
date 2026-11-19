
public class Game {
    private int id;
    private String title;
    private String publisher;
    private double price;
    private boolean isExpansion;

    public Game(int id, String title, String publisher, double price, boolean isExpansion) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
        this.isExpansion = isExpansion;
    }
    
    public Game() {
        this.id = 0;
        this.title = "";
        this.publisher = "";
        this.price = 0.0;
        this.isExpansion = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id instanceof Integer) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be an integer.");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title instanceof String) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title must be a string.");
        }
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher instanceof String) {
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
        } else if (!(price instanceof Double)) {
            throw new IllegalArgumentException("Price must be a number.");
        } else {
            throw new IllegalArgumentException("Price must be a non-negative number.");
        }
    }

    public boolean isExpansion() {
        return isExpansion;
    }

    public void setExpansion(boolean expansion) {
        if (expansion instanceof Boolean) {
            this.isExpansion = expansion;
        } else {
            throw new IllegalArgumentException("isExpansion must be a boolean.");
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", isExpansion=" + isExpansion +
                '}';
    }

    @Override 
    public boolean equals(Game game) {
        if (game == null) throw new NullPointerException("Cannot compare to null.");
        return this.id == game.id &&
                Double.compare(game.price, this.price) == 0 &&
                this.isExpansion == game.isExpansion &&
                this.title.equals(game.title) &&
                this.publisher.equals(game.publisher);
    }

    @Override 
    public int compareTo(Game game) {
        if (game == null) throw new NullPointerExceptionException("Cannot compare to null.");
        return Integer.compare(this.id, game.id);
    }

}

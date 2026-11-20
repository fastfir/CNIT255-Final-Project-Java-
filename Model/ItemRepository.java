package boardGameLibrary.model;
import java.util.List;

public interface ItemRepository {
    
    void save(Item item);

    Item findById(int id);

    void deleteById(int id);

    List<Item> findAll();
}

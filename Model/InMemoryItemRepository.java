package boardGameLibrary.model;
import java.util.ArrayList;
import java.util.List;

public class InMemoryItemRepository implements ItemRepository {
    private final List<Item> items = new ArrayList<>();
    private int nextId = 1;

    private int indexOf(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void save(Item item) {
        int index = indexOf(item.getId());
        if (item.getId() == 0) {
            item.setId(nextId++);
            items.add(item);
        } else if (index != -1) {
            items.set(index, item);
        } else {
            throw new IllegalArgumentException("Item with id " + item.getId() + " does not exist.");
        }
    }

    @Override
    public Item findById(int id) {
        int index = indexOf(id);
        if (index != -1) {
            return items.get(index);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
        } else {
            throw new IllegalArgumentException("Item with id " + id + " does not exist.");
        }
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }
}
package boardGameLibrary;
import boardGameLibrary.model.Item;
import boardGameLibrary.model.ItemRepository;
import boardGameLibrary.model.InMemoryItemRepository;
import java.util.List;
public class Main {

    private static void section(String name) {
        System.out.println("\n=== " + name + " ===");
    }
    private static void printAll(ItemRepository itemRepository) {
        for (Item item : itemRepository.findAll()) {
            System.out.println("Item ID: " + item.getId());
            System.out.println("Item Title: " + item.getTitle());
            System.out.println("Item Publisher: " + item.getPublisher());
            System.out.println("Item Price: $" + item.getPrice());
            System.out.println("Is Expansion: " + (item.isExpansion() ? "Yes" : "No"));
            System.out.println("---------------------------");
        }
    }

    public static void main(String[] args) {
        ItemRepository repo = new InMemoryItemRepository();
        Item[] seed = {
            new Item("Catan", "Catan Studio", 44.99, false),
            new Item("Catan: Seafarers", "Catan Studio", 34.99, true),
            new Item("Ticket to Ride", "Days of Wonder", 54.99, false),
            new Item("Pandemic", "Z-Man Games", 39.99, false),
            new Item("Carcassonne", "Z-Man Games", 29.99, false),
            new Item("Carcassonne: Inns & Cathedrals", "Z-Man Games", 19.99, true),
            new Item("Azul", "Plan B Games", 39.99, false),
            new Item("Wingspan", "Stonemaier Games", 65.00, false),
            new Item("Wingspan: European Expansion", "Stonemaier Games", 29.99, true),
            new Item("Codenames", "Czech Games Edition", 19.99, false)
        };

        section("CREATE");
        for (Item item : seed) {
            repo.save(item);
            System.out.println("Saved with id " + item.getId() + ": " + item.getTitle());
        }

        section("READ");
        printAll(repo);
        System.out.println("findById(3): " + repo.findById(3));
        System.out.println("findById(999): " + repo.findById(999));

        section("UPDATE");
        Item toUpdate = repo.findById(2);
        toUpdate.setPrice(29.99);
        toUpdate.setPublisher("Kosmos");
        repo.save(toUpdate);
        printAll(repo);
        Item ghost = new Item("Ghost", "None", 1.00, false);
        ghost.setId(999);
        try {
            repo.save(ghost);
        } catch (IllegalArgumentException e) {
            System.out.println("Update rejected: " + e.getMessage());
        }

        section("DELETE");
        repo.deleteById(4);
        printAll(repo);
        try {
            repo.deleteById(4);
        } catch (IllegalArgumentException e) {
            System.out.println("Delete rejected: " + e.getMessage());
        }

        section("BAD INPUT");
        try {
            new Item("Broken", "Nobody", -10.0, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            repo.findById(2).setPrice(-1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("Price of id 2 is still $" + repo.findById(2).getPrice());

        section("DEFENSIVE COPY");
        List<Item> copy = repo.findAll();
        int before = copy.size();
        copy.clear();
        System.out.println("Cleared the copy (" + before + " items). Repository still has "
                + repo.findAll().size());
    }
}
package boardGameLibrary;
import boardGameLibrary.model.Game;
import boardGameLibrary.model.GameRepository;
import boardGameLibrary.model.InMemoryGameRepository;
import java.util.List;
public class Main {

    private static void section(String name) {
        System.out.println("\n=== " + name + " ===");
    }
    private static void printAll(GameRepository gameRepository) {
        for (Game game : gameRepository.findAll()) {
            System.out.println("Game ID: " + game.getId());
            System.out.println("Game Title: " + game.getTitle());
            System.out.println("Game Publisher: " + game.getPublisher());
            System.out.println("Game Price: $" + game.getPrice());
            System.out.println("Is Expansion: " + (game.isExpansion() ? "Yes" : "No"));
            System.out.println("---------------------------");
        }
    }

    public static void main(String[] args) {
        GameRepository repo = new InMemoryGameRepository();
        Game[] seed = {
            new Game("Catan", "Catan Studio", 44.99, false),
            new Game("Catan: Seafarers", "Catan Studio", 34.99, true),
            new Game("Ticket to Ride", "Days of Wonder", 54.99, false),
            new Game("Pandemic", "Z-Man Games", 39.99, false),
            new Game("Carcassonne", "Z-Man Games", 29.99, false),
            new Game("Carcassonne: Inns & Cathedrals", "Z-Man Games", 19.99, true),
            new Game("Azul", "Plan B Games", 39.99, false),
            new Game("Wingspan", "Stonemaier Games", 65.00, false),
            new Game("Wingspan: European Expansion", "Stonemaier Games", 29.99, true),
            new Game("Codenames", "Czech Games Edition", 19.99, false)
        };

        section("CREATE");
        for (Game game : seed) {
            repo.save(game);
            System.out.println("Saved with id " + game.getId() + ": " + game.getTitle());
        }

        section("READ");
        printAll(repo);
        System.out.println("findById(3): " + repo.findById(3));
        System.out.println("findById(999): " + repo.findById(999));

        section("UPDATE");
        Game toUpdate = repo.findById(2);
        toUpdate.setPrice(29.99);
        toUpdate.setPublisher("Kosmos");
        repo.save(toUpdate);
        printAll(repo);
        Game ghost = new Game("Ghost", "None", 1.00, false);
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
            new Game("Broken", "Nobody", -10.0, false);
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
        List<Game> copy = repo.findAll();
        int before = copy.size();
        copy.clear();
        System.out.println("Cleared the copy (" + before + " items). Repository still has "
                + repo.findAll().size());
    }
}
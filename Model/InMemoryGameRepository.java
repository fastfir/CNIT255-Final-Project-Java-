package boardGameLibrary.model;
import java.util.ArrayList;
import java.util.List;

public class InMemoryGameRepository implements GameRepository {
    private final List<Game> games = new ArrayList<>();
    private int nextId = 1;

    private int indexOf(int id) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override 
    public void save(Game game) {
        int index = indexOf(game.getId());
        if (game.getId() == 0) {
            game.setId(nextId++);
            games.add(game);
        } else if (index != -1) {
            games.set(index, game);
        }
        else {
            throw new IllegalArgumentException("Game with id " + game.getId() + " does not exist.");
        }
    }

    @Override
    public Game findById(int id) {
        int index = indexOf(id);
        if (index != -1) {
            return games.get(index);
        }
        return null;
    }
    
    @Override
    public void deleteById(int id) {
        int index = indexOf(id);
        if (index != -1) {
            games.remove(index);
        } else {
            throw new IllegalArgumentException("Game with id " + id + " does not exist.");
        }
    }

    @Override
    public List<Game> findAll() {
        return new ArrayList<>(games);
    }
}
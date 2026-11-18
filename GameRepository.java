import java.util.List;

public interface GameRepository {
    
    void save(Game game);

    Game findById(int id);

    void deleteById(int id);

    List<Game> findAll();
}

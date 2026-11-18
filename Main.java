public class Main {
    public static void main(String[] args) {
        GameRepository gameRepository = new InMemoryGameRepository();

        // Create a new game
        Game newGame = new Game();
        newGame.setName("Chess");
        newGame.setDescription("A strategy board game");
        gameRepository.save(newGame);

        // Find a game by ID
        Game foundGame = gameRepository.findById(newGame.getId());
        System.out.println("Found game: " + foundGame.getName());

        // Update the game
        foundGame.setDescription("A classic strategy board game");
        gameRepository.save(foundGame);

        // Delete the game
        gameRepository.deleteById(foundGame.getId());
    }
}
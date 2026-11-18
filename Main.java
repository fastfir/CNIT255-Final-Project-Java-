public class Main {

    private static void PrintAll(GameRepository gameRepository) {
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
        GameRepository gameRepository = new InMemoryGameRepository();

        Game game1 = new Game(1, "The Legend of Zelda: Breath of the Wild", "Nintendo", 59.99, false);
        gameRepository.save(game1);

        Game game2 = new Game(2, "Super Mario Odyssey", "Nintendo", 49.99, false);
        gameRepository.save(game2);

        Game game3 = new Game(3, "Minecraft", "Mojang", 29.99, true);
        gameRepository.save(game3);

        PrintAll(gameRepository);
    }
}
import java.util.ArrayList;
import java.util.List;

public class Lobby {

    private List<String> players;
    private int maxPlayers;
    private boolean gameStarted;

    public Lobby(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>();
        this.gameStarted = false;
    }

    public boolean addPlayer(String playerName) {
        if (gameStarted) {
            System.out.println("Game already started. Cannot join.");
            return false;
        }

        if (players.size() >= maxPlayers) {
            System.out.println("Lobby is full.");
            return false;
        }

        players.add(playerName);
        System.out.println(playerName + " joined the lobby.");
        return true;
    }

    public void removePlayer(String playerName) {
        if (players.remove(playerName)) {
            System.out.println(playerName + " left the lobby.");
        } else {
            System.out.println(playerName + " not found.");
        }
    }

    public void listPlayers() {
        System.out.println("Players in lobby:");
        for (String player : players) {
            System.out.println("- " + player);
        }
    }

    public boolean startGame() {
        if (players.size() < 2) {
            System.out.println("Need at least 2 players to start.");
            return false;
        }

        gameStarted = true;
        System.out.println("Game started with " + players.size() + " players!");
        return true;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public List<String> getPlayers() {
        return new ArrayList<>(players);
    }
}

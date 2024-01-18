package io.lrxxh.board;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Board {
    private final JavaPlugin plugin;
    private final BoardAdapter boardAdapter;
    private BoardManager scoreboardBoardManager;

    public Board(JavaPlugin plugin, BoardAdapter boardAdapter) {
        this.plugin = plugin;
        this.boardAdapter = boardAdapter;
        setup();
    }

    public void setup() {
        plugin.getServer().getPluginManager().registerEvents(new BoardListener(this), getPlugin());
        this.scoreboardBoardManager = new BoardManager(getPlugin(), getBoardAdapter());
    }
}

package io.lrxxh.board;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class BoardManager {
    private final JavaPlugin plugin;
    private final Map<UUID, BoardScoreboard> boards = new HashMap<>();
    private final BoardAdapter boardAdapter;

    public BoardManager(JavaPlugin plugin, BoardAdapter boardAdapter) {
        this.plugin = plugin;
        this.boardAdapter = boardAdapter;
        startScoreboard();
    }

    public void createScoreboard(Player player) {
        BoardScoreboard boardScoreboard = new BoardScoreboard(player, boardAdapter.getTitle(player), plugin);
        boards.put(player.getUniqueId(), boardScoreboard);
        boardScoreboard.display();
    }

    private void startScoreboard() {
        long delay = boardAdapter.getDelay();
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            Map<UUID, List<String>> updatedLines = Bukkit.getOnlinePlayers().stream()
                    .collect(Collectors.toMap(
                            Player::getUniqueId,
                            boardAdapter::getLines
                    ));

            Bukkit.getScheduler().runTask(plugin, () -> {
                boards.entrySet().removeIf(entry -> !updatedLines.containsKey(entry.getKey()));

                boards.forEach((uuid, boardScoreboard) -> {
                    List<String> lines = updatedLines.get(uuid);
                    if (lines != null) {
                        boardScoreboard.setLines(lines);
                    }
                });
            });
        }, delay, delay);
    }

    public void removeScoreboard(Player player) {
        boards.remove(player.getUniqueId());
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }
}

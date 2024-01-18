package io.lrxxh.board;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BoardListener implements Listener {
    private final Board lBoard;

    public BoardListener(Board lBoard) {
        this.lBoard = lBoard;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(lBoard.getPlugin(), () -> lBoard.getScoreboardBoardManager().createScoreboard(event.getPlayer()), 0);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        lBoard.getScoreboardBoardManager().removeScoreboard(event.getPlayer());
    }
}

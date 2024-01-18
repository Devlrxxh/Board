package io.lrxxh.board;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardScoreboard {
    private final Player player;
    private final List<String> lastLines = new ArrayList<>();
    private Scoreboard scoreboard;
    private Objective objective;
    private final JavaPlugin plugin;

    public BoardScoreboard(Player player, String title, JavaPlugin plugin) {
        this.player = player;
        this.plugin = plugin;
        runAsync(() -> createScoreboard(title));
    }

    private void createScoreboard(String title) {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective(player.getName(), "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(translateColorCodes(title));
    }

    public void setLines(List<String> lines) {
        if (isLinesDifferent(lines)) {
            lastLines.clear();
            lastLines.addAll(lines);
            runAsync(() -> updateScoreboardLines(lines));
        }
    }

    private void updateScoreboardLines(List<String> lines) {
        Set<String> usedEntries = new HashSet<>();
        int score = lines.size();

        for (String line : lines) {
            String processedLine = translateColorCodes(line);
            String uniqueLine = makeUnique(processedLine, usedEntries);
            objective.getScore(uniqueLine).setScore(score);
            score--;
        }

        cleanupOldEntries(usedEntries);
    }

    private String makeUnique(String line, Set<String> usedEntries) {
        String uniqueLine = line;
        while (usedEntries.contains(uniqueLine)) {
            uniqueLine += ChatColor.RESET;
        }
        usedEntries.add(uniqueLine);
        return uniqueLine;
    }


    private void cleanupOldEntries(Set<String> currentEntries) {
        scoreboard.getEntries().stream()
                .filter(entry -> !currentEntries.contains(entry))
                .forEach(scoreboard::resetScores);
    }

    private boolean isLinesDifferent(List<String> newLines) {
        return !newLines.equals(lastLines);
    }

    public void display() {
        runAsync(() -> {
            if (scoreboard != null) {
                player.setScoreboard(scoreboard);
            }
        });
    }

    private void runAsync(Runnable task) {
        if (Bukkit.isPrimaryThread()) {
            task.run();
        } else {
            Bukkit.getScheduler().runTask(plugin, task);
        }
    }

    private String translateColorCodes(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}

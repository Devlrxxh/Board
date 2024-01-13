package dev.acentra;


import co.aikar.commands.BukkitCommandCompletionContext;
import co.aikar.commands.CommandCompletions;
import co.aikar.commands.PaperCommandManager;
import dev.acentra.arena.ArenaManager;
import dev.acentra.config.ConfigManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Collectors;

@Getter
public final class lBridge extends JavaPlugin {
    private static lBridge instance;
    private PaperCommandManager paperCommandManager;
    private ArenaManager arenaManager;
    private ConfigManager configManager;

    public static lBridge getInstance() {
        if (instance == null) {
            instance = new lBridge();
        }

        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;
        loadManagers();
    }

    @Override
    public void onDisable() {
    }

    private void loadManagers() {
        arenaManager = new ArenaManager();
        loadCommandManager();

        configManager = new ConfigManager();
        getConfigManager().loadConfigs();
    }

    private void loadCommandManager() {
        paperCommandManager = new PaperCommandManager(getInstance());
        loadCommandCompletions();
    }


    private void loadCommandCompletions() {
        CommandCompletions<BukkitCommandCompletionContext> commandCompletions = getPaperCommandManager().getCommandCompletions();
        commandCompletions.registerCompletion("names", c -> Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
    }
}

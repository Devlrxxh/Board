package dev.acentra;


import co.aikar.commands.BukkitCommandCompletionContext;
import co.aikar.commands.CommandCompletions;
import co.aikar.commands.PaperCommandManager;
import dev.acentra.arena.Arena;
import dev.acentra.arena.ArenaManager;
import dev.acentra.arena.command.ArenaCommand;
import dev.acentra.config.ConfigManager;
import dev.acentra.manager.ThreadsManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.stream.Collectors;

@Getter
public final class lBridge extends JavaPlugin {
    private static lBridge instance;
    private PaperCommandManager paperCommandManager;
    private ArenaManager arenaManager;
    private ConfigManager configManager;
    private ThreadsManager threadsManager;

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
        getThreadsManager().setOffline(true);
    }

    private void loadManagers() {
        configManager = new ConfigManager();
        getConfigManager().loadConfigs();

        arenaManager = new ArenaManager();
        getArenaManager().loadArenas();

        this.threadsManager = new ThreadsManager();
        getThreadsManager().setOffline(false);

        loadCommandManager();
    }

    private void loadCommandManager() {
        paperCommandManager = new PaperCommandManager(getInstance());
        loadCommandCompletions();
        registerCommands();
    }

    private void registerCommands() {
        Collections.singletonList(
                new ArenaCommand()
        ).forEach(command -> paperCommandManager.registerCommand(command));
    }


    private void loadCommandCompletions() {
        CommandCompletions<BukkitCommandCompletionContext> commandCompletions = getPaperCommandManager().getCommandCompletions();
        commandCompletions.registerCompletion("names", c -> Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
        commandCompletions.registerCompletion("arenas", c -> getArenaManager().getArenas().stream().map(Arena::getName).collect(Collectors.toList()));
    }
}

package dev.acentra.config;

import dev.acentra.lBridge;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;


@Getter
@Setter
public class ConfigManager {
    private final lBridge plugin = lBridge.getInstance();
    public FileConfiguration arenaConfig;
    private File arena;

    public void loadConfigs() {
        Collections.singletonList(
                new Config(arena, arenaConfig, "saves/arenas.yml")
        ).forEach(config -> loadConfig(config));
    }


    private void loadConfig(Config config) {
        saveResourceIfNotExists(config.getFileLocation());
        File configFile = new File(plugin.getDataFolder(), config.getFileLocation());
        setArena(configFile);
        setArenaConfig(YamlConfiguration.loadConfiguration(configFile));
    }


    private void saveResourceIfNotExists(String resourcePath) {
        File file = new File(plugin.getDataFolder(), resourcePath);
        if (!file.exists()) {
            plugin.saveResource(resourcePath, false);
        }
    }

    public void saveConfig(File config, FileConfiguration fileConfiguration) throws IOException, InvalidConfigurationException {
        fileConfiguration.save(config);
        fileConfiguration.load(config);
    }
}

package dev.acentra.config;

import dev.acentra.lBridge;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;


public class ConfigManager {
    private final lBridge plugin = lBridge.getInstance();
    private File arena;
    private FileConfiguration arenaConfig;

    public void loadConfigs() {
        Arrays.asList(
                new Config(arena, arenaConfig, "saves/arenas.yml")
        ).forEach(config -> loadConfig(config));
    }


    private void loadConfig(Config config) {
        saveResourceIfNotExists(config.getFileLocation());
        File configFile = new File(plugin.getDataFolder(), config.getFileLocation());
        config.setFile(configFile);
        config.setFileConfiguration(YamlConfiguration.loadConfiguration(configFile));
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

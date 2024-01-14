package dev.acentra.config;

import dev.acentra.lBridge;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Collections;


@Getter
@Setter
public class ConfigManager {
    private final lBridge plugin = lBridge.getInstance();
    public FileConfiguration arenaConfig;
    private File arena;

    public void loadConfigs() {
        Collections.singletonList(
                new Config(getArena(), getArenaConfig(), "saves/arenas.yml")
        ).forEach(this::loadConfig);
    }


    private void loadConfig(Config config) {
        saveResourceIfNotExists(config.getFileLocation());
        File configFile = new File(getPlugin().getDataFolder(), config.getFileLocation());
        setArena(configFile);
        setArenaConfig(YamlConfiguration.loadConfiguration(configFile));
    }


    private void saveResourceIfNotExists(String resourcePath) {
        File file = new File(getPlugin().getDataFolder(), resourcePath);
        if (!file.exists()) {
            getPlugin().saveResource(resourcePath, false);
        }
    }

    public void saveConfig(File config, FileConfiguration fileConfiguration) throws IOException, InvalidConfigurationException {
        fileConfiguration.save(config);
        fileConfiguration.load(config);
    }
}

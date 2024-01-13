package dev.acentra.arena;

import dev.acentra.lBridge;
import dev.acentra.utils.LocationUtil;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ArenaManager {
    private final List<Arena> arenas = new ArrayList<>();
    private final lBridge plugin;
    private final FileConfiguration arenaConfig;

    public ArenaManager() {
        this.plugin = lBridge.getInstance();
        this.arenaConfig = plugin.getConfigManager().getArenaConfig();
        loadArenas();
    }

    public void loadArenas() {
        if (arenaConfig.getConfigurationSection("arenas") == null || arenaConfig.get("arenas") == null)
            return;

        arenas.addAll(arenaConfig.getConfigurationSection("arenas").getKeys(false).stream()
                .map(name -> {
                    String path = "arenas." + name + ".";
                    String displayName = arenaConfig.getString(path + "displayName");
                    Location redSpawn = LocationUtil.toLoc(arenaConfig.getString(path + "redSpawn"));
                    Location blueSpawn = LocationUtil.toLoc(arenaConfig.getString(path + "blueSpawn"));
                    Location redSpawnMin = LocationUtil.toLoc(arenaConfig.getString(path + "redSpawnMin"));
                    Location redSpawnMax = LocationUtil.toLoc(arenaConfig.getString(path + "redSpawnMax"));
                    Location blueSpawnMin = LocationUtil.toLoc(arenaConfig.getString(path + "blueSpawnMin"));
                    Location blueSpawnMax = LocationUtil.toLoc(arenaConfig.getString(path + "blueSpawnMax"));
                    Location min = LocationUtil.toLoc(arenaConfig.getString(path + "min"));
                    Location max = LocationUtil.toLoc(arenaConfig.getString(path + "max"));
                    double buildLimit = arenaConfig.getDouble(path + "buildLimit");
                    double deathZone = arenaConfig.getDouble(path + "deathZone");

                    return new Arena(name, displayName, redSpawn, blueSpawn, redSpawnMin, redSpawnMax, blueSpawnMin, blueSpawnMax, min, max, buildLimit, deathZone, false);
                })
                .collect(Collectors.toList()));
    }
}

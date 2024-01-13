package dev.acentra.arena;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;

@Getter
@AllArgsConstructor
public class Arena {
    private String name;
    private String displayName;
    private Location redSpawn;
    private Location blueSpawn;
    private Location redSpawnMin;
    private Location redSpawnMax;
    private Location blueSpawnMin;
    private Location blueSpawnMax;
    private Location min;
    private Location max;
    private double builtLimit;
    private double deathZone;
    private boolean active;
}

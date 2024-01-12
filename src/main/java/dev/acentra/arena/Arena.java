package dev.acentra.arena;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

/**
 * @author lrxh
 * @project MusikPlayer
 * @since 1/12/2024
 */
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
    private double builtLimit;
    private double deathZone;
    private boolean active;
}

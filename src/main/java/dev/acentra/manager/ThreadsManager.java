package dev.acentra.manager;

import dev.acentra.lBridge;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

@Getter
@Setter
public class ThreadsManager {

    private final BukkitScheduler scheduler = Bukkit.getScheduler();
    private final lBridge plugin = lBridge.getInstance();
    private boolean offline;


    public void sync(Runnable runnable, long delay, long period) {
        if (offline)
            return;
        getScheduler().runTaskTimer(getPlugin(), runnable, delay, period);
    }
}

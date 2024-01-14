package dev.acentra.queue;

import dev.acentra.lBridge;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@Getter
public class QueueManager {
    private final Map<UUID, QueueEntry> queue = new ConcurrentHashMap<>();

    private final lBridge plugin = lBridge.getInstance();


    public QueueManager() {
        getPlugin().getThreadsManager().sync(() -> getQueue().forEach((uuid, queueEntry) -> findMatch(uuid)), 20L, 20L);
    }


    private void findMatch(UUID playerUUID) {
        if (playerUUID == null)
            return;
        if (getQueue().keySet().size() < 2)
            return;
        for (UUID queuePlayersUUID : getQueue().keySet()) {
            if (queuePlayersUUID != playerUUID && getQueue().get(queuePlayersUUID).equals(getQueue().get(playerUUID))) {
                getQueue().remove(playerUUID);
                getQueue().remove(queuePlayersUUID);
                System.out.println("Match started");
            }
        }
    }
}

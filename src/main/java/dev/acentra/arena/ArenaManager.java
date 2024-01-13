package dev.acentra.arena;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ArenaManager {
    private final List<Arena> arenas = new ArrayList<>();

    private void LoadArenas() {
    }
}

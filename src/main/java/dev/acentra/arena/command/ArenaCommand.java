package dev.acentra.arena.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.acentra.arena.Arena;
import dev.acentra.arena.ArenaManager;
import dev.acentra.lBridge;
import dev.acentra.utils.CC;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;


@Getter
@CommandAlias("arena")
@CommandPermission("lbridge.arena")
@Description("Command to manage and create new arenas.")
public class ArenaCommand extends BaseCommand {
    private final ArenaManager arenaManager = lBridge.getInstance().getArenaManager();

    @Default
    @Subcommand("help 1")
    public void help(Player player) {
        player.sendMessage(CC.translate("&7&m-----------------------------------------"));
        player.sendMessage(CC.translate("&9Arena Management &7[&e1/3&7] &8- &e/arena help <page>"));
        player.sendMessage(" ");
        player.sendMessage(CC.translate("&eArena Setup"));
        player.sendMessage(CC.translate("&7- &9/arena create &8<&7arena&8> &8| &7Create arena"));
        player.sendMessage(CC.translate("&7- &9/arena remove &8<&7arena&8> &8| &7Remove arena"));
        player.sendMessage(CC.translate("&7- &9/arena save &8| &7Save arenas to file."));
        player.sendMessage(" ");
        player.sendMessage(CC.translate("&eArena Customization"));
        player.sendMessage(CC.translate("&7- &9/arena setDisplay &8<&7name&8> &8<&7value&8>  &8| &7Set display name"));
        player.sendMessage(CC.translate("&7- &9/arena setRedSpawn &8<&7name&8>  &8| &7Set red spawn"));
        player.sendMessage(CC.translate("&7&m-----------------------------------------"));
    }

    @Subcommand("help 2")
    public void help2(Player player) {
        player.sendMessage(CC.translate("&7&m-----------------------------------------"));
        player.sendMessage(CC.translate("&9Arena Management &7[&e2/3&7] &8- &e/arena help <page>"));
        player.sendMessage(" ");
        player.sendMessage(CC.translate("&eArena Customization"));
        player.sendMessage(CC.translate("&7- &9/arena setBlueSpawn &8<&7name&8>  &8| &7Set red spawn"));
        player.sendMessage(CC.translate("&7- &9/arena setRedSpawnMin &8<&7name&8>  &8| &7Set red spawn min"));
        player.sendMessage(CC.translate("&7- &9/arena setRedSpawnMax &8<&7name&8>  &8| &7Set red spawn max"));
        player.sendMessage(CC.translate("&7- &9/arena setBlueSpawnMin &8<&7name&8>  &8| &7Set blue spawn min"));
        player.sendMessage(CC.translate("&7- &9/arena setBlueSpawnMax &8<&7name&8>  &8| &7Set blue spawn max"));
        player.sendMessage(CC.translate("&7&m-----------------------------------------"));
    }

    @Subcommand("help 3")
    public void help3(Player player) {
        player.sendMessage(CC.translate("&7&m-----------------------------------------"));
        player.sendMessage(CC.translate("&9Arena Management &7[&e3/3&7] &8- &e/arena help <page>"));
        player.sendMessage(" ");
        player.sendMessage(CC.translate("&eArena Customization"));
        player.sendMessage(CC.translate("&7- &9/arena setMin &8<&7name&8>  &8| &7Set arena min"));
        player.sendMessage(CC.translate("&7- &9/arena setMax &8<&7name&8>  &8| &7Set arena max"));
        player.sendMessage(CC.translate("&7- &9/arena setBuiltLimit &8<&7name&8>  &8| &7Set arena built limit"));
        player.sendMessage(CC.translate("&7- &9/arena setDeathZone &8<&7name&8>  &8| &7Set arena death zone"));
        player.sendMessage(CC.translate("&7&m-----------------------------------------"));
    }

    @Subcommand("create")
    @Syntax("<arena>")
    private void create(Player player, String arena) {
        if (checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena already exists!"));
            return;
        }
        getArenaManager().getArenas().add(Arena.builder()
                .name(arena)
                .displayName("&9" + arena)
                .active(false).build());
        player.sendMessage(CC.translate("&aSuccessfully created arena!"));
    }

    @Subcommand("remove")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void remove(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArenas().remove(getArenaManager().getArena(arena));
        player.sendMessage(CC.translate("&aSuccessfully removed arena!"));
    }

    @Subcommand("save")
    private void remove(Player player) throws IOException, InvalidConfigurationException {
        lBridge.getInstance().getConfigManager().saveConfig(lBridge.getInstance().getConfigManager().getArena(), lBridge.getInstance().getConfigManager().getArenaConfig());
        player.sendMessage(CC.translate("&aSuccessfully saved arenas!"));
    }

    @Subcommand("setDisplay")
    @CommandCompletion("@arenas")
    @Syntax("<arena> <value>")
    private void setDisplay(Player player, String arena, String value) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setDisplayName(value);
        player.sendMessage(CC.translate("&aSuccessfully set display name!"));
    }

    @Subcommand("setRedSpawn")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void setRedSpawn(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setRedSpawn(player.getLocation());
        player.sendMessage(CC.translate("&aSuccessfully set red spawn!"));
    }

    @Subcommand("setBlueSpawn")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void setBlueSpawn(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setBlueSpawn(player.getLocation());
        player.sendMessage(CC.translate("&aSuccessfully set blue spawn!"));
    }

    @Subcommand("setRedSpawnMin")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void setRedSpawnMin(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setRedSpawnMin(player.getLocation());
        player.sendMessage(CC.translate("&aSuccessfully set red spawn min!"));
    }

    @Subcommand("setRedSpawnMax")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void setRedSpawnMax(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setRedSpawnMax(player.getLocation());
        player.sendMessage(CC.translate("&aSuccessfully set red spawn max!"));
    }

    @Subcommand("setBlueSpawnMin")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void setBlueSpawnMin(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setBlueSpawnMin(player.getLocation());
        player.sendMessage(CC.translate("&aSuccessfully set blue spawn min!"));
    }

    @Subcommand("setBlueSpawnMax")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void setBlueSpawnMax(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setBlueSpawnMax(player.getLocation());
        player.sendMessage(CC.translate("&aSuccessfully set blue spawn max!"));
    }

    @Subcommand("setMin")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void setMin(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setMin(player.getLocation());
        player.sendMessage(CC.translate("&aSuccessfully set arena min!"));
    }

    @Subcommand("setMax")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void setMax(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setMax(player.getLocation());
        player.sendMessage(CC.translate("&aSuccessfully set arena max!"));
    }

    @Subcommand("setBuiltLimit")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void setBuiltLimit(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setBuiltLimit(player.getLocation().getY());
        player.sendMessage(CC.translate("&aSuccessfully set arena built limit!"));
    }

    @Subcommand("setDeathZone")
    @CommandCompletion("@arenas")
    @Syntax("<arena>")
    private void setDeathZone(Player player, String arena) {
        if (!checkArena(arena)) {
            player.sendMessage(CC.translate("&4ERROR &8| &cArena doesn't exist!"));
            return;
        }
        getArenaManager().getArena(arena).setDeathZone(player.getLocation().getY());
        player.sendMessage(CC.translate("&aSuccessfully set arena death zone!"));
    }


    private boolean checkArena(String arena) {
        return getArenaManager().getArenas().contains(getArenaManager().getArena(arena));
    }
}

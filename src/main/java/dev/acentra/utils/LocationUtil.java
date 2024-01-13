package dev.acentra.utils;

import dev.acentra.lBridge;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Arrays;

public class LocationUtil {

    public static Location toLoc(String location) {
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Invalid location string");
        }

        String[] data = location.split(":");
        if (data.length != 6) {
            throw new IllegalArgumentException("Invalid location format");
        }

        World world = lBridge.getInstance().getServer().getWorld(data[0]);
        if (world == null) {
            throw new IllegalArgumentException("Invalid world name in location string");
        }

        try {
            double[] values = Arrays.stream(data, 1, data.length)
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            return new Location(world, values[0], values[1] + 1, values[2], (float) values[3], (float) values[4]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid values in location string");
        }
    }

    public static String toString(Location loc) {
        if (loc == null || loc.getWorld() == null) {
            throw new IllegalArgumentException("Invalid location object");
        }

        double[] values = {loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()};
        String valuesString = String.join(":", Arrays.stream(values).mapToObj(String::valueOf).toArray(String[]::new));

        return String.format("%s:%s", loc.getWorld().getName(), valuesString);
    }
}

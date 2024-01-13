package dev.acentra.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;


@Getter
@Setter
@AllArgsConstructor
public class Config {
    private File file;
    private FileConfiguration fileConfiguration;
    private String fileLocation;
}

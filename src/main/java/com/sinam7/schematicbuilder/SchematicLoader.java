package com.sinam7.schematicbuilder;

import com.convallyria.schematics.extended.Schematic;
import org.bukkit.entity.Player;

import java.io.File;

public class SchematicLoader {

    private final SchematicPlugin plugin;

    public SchematicLoader(SchematicPlugin plugin) {
        this.plugin = plugin;
    }

    // download a schematic file from plugin's folder
    public String listSchematics() {
        // get all files in plugin's folder
        File folder = new File(plugin.getDataFolder() + File.separator + "schematics");
        File[] listOfFiles = folder.listFiles();
        StringBuilder sb = new StringBuilder().append("Schematics: ").append("\n");
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.getName().endsWith(".schem")) {
                    sb.append(file.getName()).append("\n");
                }
            }
        }
        return sb.toString();
    }

    public String loadSchematic(String fileName, Player player) {
        File folder = new File(plugin.getDataFolder() + File.separator + "schematics");
        // load schematic file
        File file = new File(folder, fileName + ".schem");
        // if file not exist
        if (!file.exists()) {
            return "Schematic not found: " + file.getName();
        }

        Schematic schematic = new Schematic(plugin, file);
        plugin.getPlayerManagement().setBuilding(player.getUniqueId(), schematic);
        return "Schematic loaded: " + file.getName();
    }


}

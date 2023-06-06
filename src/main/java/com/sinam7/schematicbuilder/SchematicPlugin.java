package com.sinam7.schematicbuilder;

import com.convallyria.schematics.extended.example.PlayerManagement;
import com.convallyria.schematics.extended.example.SchematicListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

@Getter
public class SchematicPlugin extends JavaPlugin {

    private SchematicLoader schematicLoader;
    private CommandManager commandManager;
    private PlayerManagement playerManagement;
    private SchematicListener schematicListener;
    // onEnable

    @Override
    public void onEnable() {
        // log bukkit
        this.schematicLoader = new SchematicLoader(this);
        this.commandManager = new CommandManager(this, schematicLoader);
        this.playerManagement = new PlayerManagement(this);
        this.schematicListener = new SchematicListener(this);

        // register command to plugin
        Objects.requireNonNull(getCommand("sb")).setExecutor(commandManager);
        Objects.requireNonNull(getCommand("sb")).setTabCompleter(commandManager);

        Bukkit.getPluginManager().registerEvents(schematicListener, this);

        saveDefaultConfig();
        schematicLoader.listSchematics();
    }

    public static void log(String message) {
        Bukkit.getLogger().log(Level.INFO, message);
    }
}
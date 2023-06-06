package com.convallyria.schematics.extended.example;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.convallyria.schematics.extended.Schematic;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Utility class to handle player's previews
 * @author SamB440
 */
@SuppressWarnings("FieldCanBeLocal")
public class PlayerManagement {

    private final JavaPlugin plugin;

    private final Map<UUID, Schematic> building = new HashMap<>();
    private final Map<UUID, Integer> viewDistance = new HashMap<>();

    private final Map<UUID, BuildTask> buildTask = new HashMap<>();

    public PlayerManagement(final JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void setBuildTask(final UUID uuid, final BuildTask task) {
        if (!buildTask.containsKey(uuid)) {
            buildTask.put(uuid, task);
        } else {
            buildTask.replace(uuid, task);
        }
    }

    public void removeBuildTask(final UUID uuid) {
        buildTask.remove(uuid);
    }

    public BuildTask getBuildTask(final UUID uuid) {
        return buildTask.get(uuid);
    }

    public void setBuilding(final UUID uuid, final Schematic schematic) {
        if (!building.containsKey(uuid)) {
            building.put(uuid, schematic);
        } else {
            building.replace(uuid, schematic);
        }
    }

    public void setViewDistance(final UUID uuid, final Integer viewDistance) {
        if (!this.viewDistance.containsKey(uuid)) {
            this.viewDistance.put(uuid, viewDistance);
        } else {
            this.viewDistance.replace(uuid, viewDistance);
        }
    }

    public boolean isBuilding(final UUID uuid) {
        return building.containsKey(uuid);
    }

    public Schematic getBuilding(final UUID uuid) {
        return building.get(uuid);
    }

    public int getViewDistance(final UUID uuid) {
        if (viewDistance.get(uuid) == null) {
            setViewDistance(uuid, 10);
        }
        return viewDistance.get(uuid);
    }

    public void removeBuilding(final UUID uuid) {
        building.remove(uuid);
    }
}
package com.sinam7.schematicbuilder;

import com.convallyria.schematics.extended.example.BuildTask;
import com.convallyria.schematics.extended.example.PlayerManagement;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class CommandManager implements CommandExecutor, TabCompleter {

    private static SchematicPlugin plugin;
    private final SchematicLoader schematicLoader;

    public CommandManager(SchematicPlugin pl, SchematicLoader schematicLoader) {
        plugin = pl;
        this.schematicLoader = schematicLoader;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        boolean flag = true;
        // when execute command sb
        if (label.equalsIgnoreCase("sb")) {
            // if no args
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(schematicLoader.listSchematics());
            } else {
                PlayerManagement pm = plugin.getPlayerManagement();
                switch (args[0]) {
                    case "load" -> {
                        if (args.length == 2) {
                            player.sendMessage(schematicLoader.loadSchematic(args[1], player));
                        } else {
                            player.sendMessage("Usage: /sb load <schematic name>");
                        }
                    }
                    case "up" -> changeViewDistance(player, pm, 1);
                    case "down" -> changeViewDistance(player, pm, -1);
                    case "count" -> {
                        Map<Material, Integer> sMD = pm.getBuilding(player.getUniqueId()).getSchematicMaterialData();

                        StringBuilder missingMaterials = new StringBuilder().append("Missing Materials:\n");

                        Inventory inventory = Bukkit.createInventory(null, sMD.size() / 9 * 9 + 9, "Schematic Material Data");
                        for (Map.Entry<Material, Integer> entry : sMD.entrySet()) {
                            if (!entry.getKey().isItem()) {
                                missingMaterials.append(entry.getKey()).append("\n");
                                continue;
                            }
                            inventory.addItem(new ItemStack(entry.getKey()).asQuantity(entry.getValue()));
                        }
                        player.openInventory(inventory);
                        player.sendMessage(missingMaterials.toString());
                    }
                    case "start" -> plugin.getSchematicListener().runPreview(player);
                    default -> flag = false;
                }
            }

        } else flag = false;
        return flag;
    }

    private static void changeViewDistance(Player player, PlayerManagement pm, int value) {
        pm.setViewDistance(player.getUniqueId(), Math.min(Math.max(pm.getViewDistance(player.getUniqueId()) + value, 0), 40)); // min 0, max 40
        // if player's buildTask is already running, cancel it and rerun with current view distance
        if (pm.getBuildTask(player.getUniqueId()) != null) {
            pm.getBuildTask(player.getUniqueId()).getScheduler().cancel();
            pm.setBuildTask(player.getUniqueId(), new BuildTask(plugin, player).start(pm.getViewDistance(player.getUniqueId())));
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // return array of strings with "load" and "paste" when command sender types "sb"
        if (label.equalsIgnoreCase("sb")) {
            if (args.length == 1) {
                return List.of("up", "down", "count");
            }
        }
        return null;
    }
}

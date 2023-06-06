# SchematicBuilder

Build Schematic file to server world with a cost or corresponding materials.

## Description

This project is a plugin for Minecraft server. 
It allows players to build schematic files in the server world with a cost or corresponding materials. 
The schematic files are stored in the server and can be downloaded from the server. 
The plugin is written in Java and uses the Bukkit API.

## Getting Started

### Dependencies

* WorldEdit
* [helper](https://github.com/lucko/helper)

### Installing

* Simply put the jar file into the plugins folder of the server.

### Executing program

1. Install WorldEdit and helper plugin.
2. Put the schematic file into the schematics folder of the server.
3. Use the command `/sb load <schematic name>` to load the schematic file.
   - Use the command `/sb list` to see the list of all schematic files.
4. Use the command `/sb start` to start building the schematic file.
5. Right click to build the schematic file. Left click to cancel the building.
   - `/sb up` and `/sb down` to increase and decrease the distance of the schematic file's preview. 

* Use the command `/sb` to see the descriptions of the plugin.

```
/sb - descriptions of plugin
/sb list - list all schematics
/sb load <schematic name> - load schematic file
/sb start - start building loaded schematic

/sb up - Increase the distance of the schematic file's preview
/sb down - Decrease the distance of the schematic file's preview
```

## Permissions

###### TODO

```

```

## Authors

* [@sinam7](https://github.com/sinam7)

## Version History

###### TODO

## License

This project is licensed under the [GNU GPL v3 License](https://github.com/sinam7/SchematicBuilder/blob/master/LICENSE).

## Acknowledgments

Inspiration, code snippets, etc.
* [README template](https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc#file-readme-template-md)
* [Schematic preview & build](https://github.com/SamB440/Schematics-Extended/tree/worldedit-api)

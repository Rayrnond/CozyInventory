package com.reflexian.cozyinventory;

import com.reflexian.cozyinventory.commands.KeepInventory;
import com.reflexian.cozyinventory.events.PlayerDeath;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class CozyInventory extends JavaPlugin {

    private static CozyInventory instance;
    public static CozyInventory getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance=this;
        loadData();
        saveDefaultConfig();

        getCommand("keepinventory").setExecutor(new KeepInventory());
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);



    }

    @Override
    public void onDisable() {
        try {
            saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadData() {
        File file = new File(getDataFolder()+"/data.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.getStringList("uuids").forEach(e ->{
            System.out.println(e.toString());
            KeepInventory.INVENTORY_SAVERS.add(UUID.fromString(e));
        });
    }

    private void saveData() throws IOException {
        File file = new File(getDataFolder()+"/data.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        final List<String> uuids = new ArrayList<>();
        KeepInventory.INVENTORY_SAVERS.forEach( e->{
            uuids.add(e.toString());
        });
        config.set("uuids", uuids);

        config.save(file);
        if (!file.exists()) {
            file.createNewFile();
        }

        getLogger().info("Saved player data for " + KeepInventory.INVENTORY_SAVERS.size() + " players!");

    }
}

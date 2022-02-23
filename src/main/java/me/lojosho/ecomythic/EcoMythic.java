package me.lojosho.ecomythic;

import me.lojosho.ecomythic.mythicmobs.MythicListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class EcoMythic extends JavaPlugin {
    private static EcoMythic instance;
    private static Logger log = Logger.getLogger("EcoMythic");
    private static Boolean ecoItems = false;
    private static Boolean ecoArmor = false;
    private static Boolean statTrackers = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new MythicListener(), this);
        if (Bukkit.getPluginManager().getPlugin("EcoItems") != null) {
            log.info("Found EcoItems... Linking...");
            ecoItems = true;
        }
        if (Bukkit.getPluginManager().getPlugin("EcoArmor") != null) {
            log.info("Found EcoArmor... Linking...");
            ecoArmor = true;
        }
        if (Bukkit.getPluginManager().getPlugin("StatTrackers") != null) {
            log.info("Found StatTrackers... Linking...");
            statTrackers = true;
        }
        if (!ecoItems && !ecoArmor && !statTrackers) {
            log.severe("Detecting no Eco plugins. Shutting Down...");
            getServer().getPluginManager().disablePlugin(instance);
        }
        // BStats
        Metrics metrics = new Metrics(this, 14450);
    }

    @Override
    public void onDisable() {
        // do nothing
        // Plugin shutdown logic
    }

    public static EcoMythic getInstance() {
        return instance;
    }
    public static Boolean hasEcoItems() {
        return ecoItems;
    }
    public static Boolean hasEcoArmor() {
        return ecoArmor;
    }
    public static Boolean hasstatTrackers() {
        return statTrackers;
    }
}

package me.lojosho.ecomythic;

import me.lojosho.ecomythic.MythicMobs.MythicListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class EcoMythic extends JavaPlugin {
    private static EcoMythic instance;
    private static Logger log = Logger.getLogger("EcoMythic");

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new MythicListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static EcoMythic getInstance() {
        return instance;
    }
}

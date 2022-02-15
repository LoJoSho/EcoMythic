package me.lojosho.ecomythic.mythicmobs;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicDropLoadEvent;
import io.lumine.xikage.mythicmobs.drops.Drop;
import me.lojosho.ecomythic.EcoArmorDropManager;
import me.lojosho.ecomythic.EcoItemsDropManager;
import me.lojosho.ecomythic.EcoMythic;
import me.lojosho.ecomythic.StatTrackerDropManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MythicListener implements Listener {

    /*
     * Registers all of the custom drops when MythicDropLoadEvent is called.
     * If a Drop contains "EcoItems", it will attempt to load it in.
     */
    @EventHandler
    public void onMythicDropLoad(MythicDropLoadEvent event)	{
        String dropName = event.getDropName();
        if (EcoMythic.hasEcoItems() && dropName.equals("EcoItems")) {
            Drop drop = new EcoItemsDropManager(event.getConfig());
            event.register(drop);
        }
        if (EcoMythic.hasEcoArmor() && dropName.equals("EcoArmor")) {
            Drop drop = new EcoArmorDropManager(event.getConfig());
            event.register(drop);
        }
        if (EcoMythic.hasstatTrackers() && dropName.equals("StatTrackers")) {
            Drop drop = new StatTrackerDropManager(event.getConfig());
            event.register(drop);
        }
    }
}

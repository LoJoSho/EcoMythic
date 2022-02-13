package me.lojosho.ecomythic.MythicMobs;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicDropLoadEvent;
import io.lumine.xikage.mythicmobs.drops.Drop;
import me.lojosho.ecomythic.DropManager;
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
        if (dropName.equals("EcoItems")) {
            Drop drop = new DropManager(event.getConfig());
            event.register(drop);
        }
    }
}

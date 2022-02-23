package me.lojosho.ecomythic;

import com.willfp.stattrackers.StatTrackersPlugin;
import com.willfp.stattrackers.stats.Stat;
import com.willfp.stattrackers.stats.Stats;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;
import io.lumine.xikage.mythicmobs.drops.IMultiDrop;
import io.lumine.xikage.mythicmobs.drops.LootBag;
import io.lumine.xikage.mythicmobs.drops.droppables.ItemDrop;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class StatTrackerDropManager extends Drop implements IMultiDrop {


    private String statid;
    /*
     * Gets the String, attempts to get its Type. If there is an Tracker that matches the value, then set statid to an
     * internal string of an StatTracker for "get" method to get the item.
     */
    public StatTrackerDropManager(MythicLineConfig config) {
        super(config.getLine(), config);

        statid = config.getString(new String[] {"type", "t", "stat", "s"}, dropVar);

    }
    @Override
    public LootBag get(DropMetadata dropMetadata) {
        LootBag loot = new LootBag(dropMetadata);
        Stat stat = Stats.getByKey(NamespacedKey.fromString(statid, (Plugin) StatTrackersPlugin.getInstance()));

        /*
        StatTrackers will return a null if a tracker doesn't exist. Therefore, we check if it's null to see if it's a valid
        tracker we can use. If we don't do this, then we end up with a NPE. Instead, the plugin will safely handle an
        invalid tracker by putting air in it's place, as well as displaying a warning to the server owner about this issue.
         */
        if (stat == null) {
            EcoMythic.getInstance().getLogger().severe("Could not find '" + statid + "' as a valid Tracker. Putting air in its place.");
            return loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(new ItemStack(Material.AIR))));
        }
        return loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(stat.getTracker().getItemStack())));
    }
}

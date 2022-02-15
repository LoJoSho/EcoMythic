package me.lojosho.ecomythic;

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

public class StatTrackerDropManager extends Drop implements IMultiDrop {


    private String statid;
    /*
     * Gets the String, attempts to get its Type. If there is an EcoItem that matches the value, then set ecoid to a
     * internal string of an EcoItem for "get" method to get the item.
     */
    public StatTrackerDropManager(MythicLineConfig config) {
        super(config.getLine(), config);

        statid = config.getString(new String[] {"type", "t", "stat", "s"}, dropVar);

    }
    @Override
    public LootBag get(DropMetadata dropMetadata) {
        LootBag loot = new LootBag(dropMetadata);
        Stat stat = Stats.getByKey(NamespacedKey.fromString(statid));
        if (stat == null) {
            EcoMythic.getInstance().getLogger().severe("Could not find '" + statid + "' as a valid Tracker. Putting air in its place.");
            return loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(new ItemStack(Material.AIR))));
        }
        return loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(stat.getTracker().getItemStack())));
    }
}

package me.lojosho.ecomythic;

import com.willfp.ecoitems.items.EcoItems;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;
import io.lumine.xikage.mythicmobs.drops.IMultiDrop;
import io.lumine.xikage.mythicmobs.drops.LootBag;
import io.lumine.xikage.mythicmobs.drops.droppables.ItemDrop;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EcoItemsDropManager extends Drop implements IMultiDrop {

    private String ecoid;
    /*
     * Gets the String, attempts to get its Type. If there is an EcoItem that matches the value, then set ecoid to a
     * internal string of an EcoItem for "get" method to get the item.
     */
    public EcoItemsDropManager(MythicLineConfig config) {
        super(config.getLine(), config);

        String str = config.getString(new String[] {"type", "t", "item", "i"}, dropVar);
        ecoid = str;
    }
    /*
     * Attempts to get an EcoItem and adds it to the droptable,
     * however, if its null, then it just returns air.
     *
     * EcoItems returns a null if the item that is gotten is an invalid item. Therefore, we check if it got a EcoItem,
     * if it got a null, then we check if it's null. We then safely handle the null by replacing the dropped item with
     * air/nothing.
     */
    @Override
    public LootBag get(DropMetadata dropMetadata) {
        LootBag loot = new LootBag(dropMetadata);
        if (EcoItems.getByID(ecoid) == null) {
            EcoMythic.getInstance().getLogger().severe("Could not find '" + ecoid + "' as a valid EcoItem. Putting air in its place. Consider Fixing This!!!!!");
            return loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(new ItemStack(Material.AIR))));
        }
        return loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(EcoItems.getByID(ecoid).getItemStack())));
    }
}

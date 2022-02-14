package me.lojosho.ecomythic;

import com.willfp.ecoarmor.sets.ArmorSets;
import com.willfp.ecoarmor.sets.ArmorSlot;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;
import io.lumine.xikage.mythicmobs.drops.IMultiDrop;
import io.lumine.xikage.mythicmobs.drops.LootBag;
import io.lumine.xikage.mythicmobs.drops.droppables.ItemDrop;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class EcoArmorDropManager extends Drop implements IMultiDrop {

    private String ecoid;
    private ArmorSlot slot;
    /*
     * Gets the String, attempts to get its Type. If there is an EcoItem that matches the value, then set ecoid to a
     * internal string of an EcoItem for "get" method to get the item.
     */
    public EcoArmorDropManager(MythicLineConfig config) {
        super(config.getLine(), config);

        String str = config.getString(new String[] {"type", "t", "armor", "a"}, dropVar);
        String str2 = config.getString(new String[] {"slot", "s"}, dropVar);
        ecoid = str;
        if (ArmorSlot.getSlot(str2.toUpperCase(Locale.ROOT)) == null) {
            slot = null;
        } else {
            slot = ArmorSlot.getSlot(str2.toUpperCase(Locale.ROOT));
        }
    }


    @Override
    public LootBag get(DropMetadata dropMetadata) {
        LootBag loot = new LootBag(dropMetadata);
        if (ArmorSets.getByID(ecoid) == null) {
            if (slot == null) {
                EcoMythic.getInstance().getLogger().severe("Invalid Armor Slot in '" + ecoid + "', inserting air instead.");
                loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(new ItemStack(Material.AIR))));
                return loot;
            }
            EcoMythic.getInstance().getLogger().severe("Could not find '" + ecoid + "' as a valid EcoArmor. Putting air in its place. Consider Fixing This!!!!!");
            loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(new ItemStack(Material.AIR))));
            return loot;
        }
        loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(ArmorSets.getByID(ecoid).getItemStack(slot))));
        return loot;
    }
}

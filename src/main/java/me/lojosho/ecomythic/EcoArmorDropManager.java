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

public class EcoArmorDropManager extends Drop implements IMultiDrop {

    private String ecoid;
    private ArmorSlot slot;
    /*
     * Gets the String, attempts to get its ArmorSet. If there is an EcoArmor set that matches it & a proper armor slot is provided
     * sets ecoid to internal id of the ArmorSet & slot to the ArmorSets ArmorSlot (Helmet, Chestplate, etc.)
     */
    public EcoArmorDropManager(MythicLineConfig config) {
        super(config.getLine(), config);

        String str = config.getString(new String[] {"type", "t", "armor", "a"}, dropVar);
        String str2 = config.getString(new String[] {"slot", "s"}, dropVar);
        ecoid = str;
        slot = ArmorSlot.getSlot(str2);
    }

    /*
     * Attempts to get an EcoArmor and adds it to the droptable
     * however, if its null, then it just returns air.
     *
     */
    @Override
    public LootBag get(DropMetadata dropMetadata) {
        LootBag loot = new LootBag(dropMetadata);
        if (ArmorSets.getByID(ecoid) == null) {
            EcoMythic.getInstance().getLogger().severe("Could not find '" + ecoid + "' as a valid EcoArmor set. Putting air in its place.");
            loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(new ItemStack(Material.AIR))));
            return loot;
        }
        if (slot == null) {
            EcoMythic.getInstance().getLogger().severe("Invalid Armor Slot in '" + ecoid + "', inserting air instead.");
            loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(new ItemStack(Material.AIR))));
            return loot;
        }
        loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(ArmorSets.getByID(ecoid).getItemStack(slot))));
        return loot;
    }
}

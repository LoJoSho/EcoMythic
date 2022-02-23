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

    /*
    TODO:
    - Clean the messy code Shards created.
     */

    private String ecoid;
    private ArmorSlot slot;
    private Boolean shard;
    /*
     * Gets the String, attempts to get its ArmorSet. If there is an EcoArmor set that matches it & a proper armor slot is provided
     * sets ecoid to internal id of the ArmorSet & sets slot to the ArmorSets ArmorSlot (Helmet, Chestplate, etc.)
     * Furthermore, if there is a shard, it will set it to true,
     */
    public EcoArmorDropManager(MythicLineConfig config) {
        super(config.getLine(), config);

        ecoid = config.getString(new String[] {"type", "t", "armor", "a"}, dropVar);
        /*
        Checks if the "slot" is actually there
        Resolves an NPE that happens if isn't checked.
         */
        if (config.getString(new String[] {"slot", "s"}, dropVar) != null) {
            slot = ArmorSlot.getSlot(config.getString(new String[] {"slot", "s"}, dropVar));
        }
        /*
        Checks to the "shard" is actually there before adding it to the variable.
         */
        if (config.getString(new String[] {"shard"}, dropVar) != null) {
            shard = config.getBoolean(new String[] {"shard"}, Boolean.parseBoolean(dropVar));
        }
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
            return loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(new ItemStack(Material.AIR))));
        }
        // Weird NPE fix? Need to check if its null, can't do shard != null.
        if (shard == null) {
            // do nothing
        } else {
            if (shard) {
                return loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(ArmorSets.getByID(ecoid).getAdvancementShardItem())));
            }
        }
        if (slot == null) {
            EcoMythic.getInstance().getLogger().severe("Invalid Armor Slot in '" + ecoid + "', inserting air instead.");
            return loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(new ItemStack(Material.AIR))));
        }
        return loot.add(new ItemDrop(this.getLine(), (MythicLineConfig) this.getConfig(), new BukkitItemStack(ArmorSets.getByID(ecoid).getItemStack(slot))));
    }
}

# EcoMythic

[PaperMC Forum Post](https://forums.papermc.io/threads/ecomythic-bring-ecoitems-to-mythicmobs.149/)

**Features:**
- No Extra Config Files (Put it right into your MythicMob configs!)
- Easy implementation

**Dependencies:**
- [MythicMobs](https://mythiccraft.io/index.php?ewr-porta/) (Recommend 4.14.2+)

**Soft Dependencies**
- [EcoItems](https://github.com/Auxilor/EcoItems) (Recommend 3.27.3+)
- [EcoArmor](https://github.com/Auxilor/EcoArmor) (Recommend 7.32.2+)

###Examples:

####EcoItems
```yaml
zzom:
  Type: ZOMBIE
  Drops:
  - EcoItems{type=talisman_core_1} 1 1 #type can either be "type", "item", "t", or "i"
  - EcoItems{item=talisman_core_2} 1 1
  - EcoItems{i=enchanted_ender_pearl} 4 1 
  - EcoItems{type=talisman_orccc_2} 1 1 #This is an invalid item. It will warn you if you put an item that isn't a valid EcoItem.
```

####EcoArmor

```yaml
zArmorDrop:
  Type: ZOMBIE
  Drops:
  - EcoArmor{armor=reaper;shard=true} 1 1 #armor can be either "armor", "a", "t", "type"
  - EcoArmor{armor=slayer;shard=true;slot=helmet} 1 1 #shards always take priority over armor pieces. This drop will drop the shard.
  - EcoArmor{armor=reaper;slot=chestplate} 1 1 #slot can either be "slot" or "s"
  - EcoArmor{armor=reaper;slot=helmet} 1 1 #You need to specify the armor set, then either add that it's a shard or an armor piece/slot
```

![Example Gif2](https://i.imgur.com/2csR4IF.gif)

![EcoArmor Shard Gif](https://i.imgur.com/uNMzSzB.gif)

![EcoArmor Gif2](https://i.imgur.com/PIMqA67.gif)
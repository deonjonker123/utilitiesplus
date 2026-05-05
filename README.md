# Utilities Plus
Attempts to hit some vanilla misses in (hopefully) interesting ways.

---

## Blocks

### Kiln
Apparently Mojang thought you'd never smelt enormous quantities of sand, clay, or cobblestone. The Kiln fixes the arbitrary smelting speed gap by processing the materials vanilla forgot at the same 2x speed as the Blast Furnace and Smoker:
- Clay, Clay Balls, Terracotta
- Stone, Cobblestone
- Sand, Red Sand

---

### Storage Barrels *(not the vanilla kind)*
A storage block that holds up to 4,096 *(configurable)* of a single item type. Because sometimes you just need somewhere to dump 3,000 cobblestone without thinking about it. Upgradeable, hopper-compatible, and available in all 12 wood types because your base's aesthetic matters, apparently.

Breaking a barrel preserves its contents and any installed upgrade.

#### Storage Barrel Upgrades
One upgrade at a time. Upgrades can't be removed if the barrel is holding more items than the lower tier supports — don't stuff 12,321 items into an Iron-tier barrel and then act surprised when you can't downgrade to Copper. Upgrading is always unrestricted.

| Upgrade | Capacity |
|---|---|
| *(Base)* | Configurable, default 4,096 |
| Copper | Base × 2 |
| Iron | Copper × 2 |
| Gold | Iron × 2 |
| Diamond | Gold × 2 |

---

### Harvester Block
Tired of running back and forth harvesting and replanting crops like some kind of medieval peasant? The Harvester Block does it for you. Mostly. It needs fuel to run, and it needs a hoe slotted inside to actually do any harvesting. That hoe still loses durability, too — so if you threw in a wooden hoe and your Harvester mysteriously stopped working, that's on you. Auto-replants after every harvest, though. Small mercies.

*(Configurable — see Config section)*

---

## Items

### Villager Catcher
Long minecart rail systems snaking across your entire world just to move one villager into your trading hall? We've all been there. We've all hated it. Right-click a villager with the Villager Catcher to imprison the little guy until you're ready to release them.

**⚠ Fair warning:** Your precious Mending villager will not survive the trip with their trades intact. Upon release, every imprisoned villager resets to an unemployed novice. You have been warned. Don't come crying.

*(Configurable — see Config section)*

---

### Storage Barrel Upgrades
*(See Storage Barrels above)*
- Copper Barrel Upgrade
- Iron Barrel Upgrade
- Gold Barrel Upgrade
- Diamond Barrel Upgrade

---

### Obsidian Boat
A boat. For lava. Crafted from obsidian. Navigate lava lakes like a reasonable person instead of bridge-pillaring across them like a noob.

Don't try it on water. It sinks. Immediately. Don't ask why. It's obsidian.

---

## Config
| Option | Min | Max | Default |
|---|---|---|---|
| Storage Barrel base capacity | 512 | 16,384 | 4,096 |
| Harvester work area | 3×3 | 15×15 | 9×9 |
| Hoe loses durability in Harvester | — | — | `true` |
| Villager resets to unemployed on release | — | — | `true` |
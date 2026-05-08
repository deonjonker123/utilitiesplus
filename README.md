# Utilities Plus
Attempts to hit some vanilla misses in (hopefully) interesting ways.

---

## Blocks

### Kiln
The Kiln fixes the arbitrary smelting speed gap by processing the materials vanilla forgot at the same 2x speed as the Blast Furnace and Smoker:
- Clay, Clay Balls, Terracotta
- Stone, Cobblestone
- Sand, Red Sand

---

### Storage Barrels *(not the vanilla kind)*
A storage block that holds up to 4,096 *(configurable)* of a single item type. Sometimes you just need somewhere to dump 3,000 cobblestone without thinking about it. Upgradeable, hopper-compatible, and available in all 12 wood types.

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

### Feeding Trough
Animals are hungry. You have food. The Feeding Trough bridges that gap automatically — place it, fill it with anything an animal might reasonably want to eat, and it'll feed any animal in a 9×3×9 area around it that finds the contents appetising. That covers the usual suspects — crops, seeds, hay, flowers, and a few more exotic options for the less picky animals. If the local animal population exceeds the configured limit, the trough stops working until you do something about the overcrowding. Also, no hopper support. Refill it manually.

*(Configurable — see Config section)*

---

### Chicken Nest Box
Chickens are going to lay eggs whether you like it or not. The Nest Box at least makes sure those eggs end up somewhere useful. Place one down and the Nest Box collects eggs in a 3×3 area in front of it and stores them in its 9-slot inventory. Hoppers can pull eggs out from the bottom of the nest block.

*(Configurable — see Config section)*

---

### Saw Bench
A whole log's worth of planks, stairs, slabs, fences, doors, and whatever else you need — without the crafting grid busywork. Strips logs too. It's a saw. It cuts wood. You get it.

---

### Vertical Slabs
Slabs, but vertical. Available in all wood types.

---

### Wooden Beams
A decorative structural beam. Doesn't connect to anything. Doesn't do anything clever. Just looks good holding your ceiling up. Available in all wood types.

---

### Filtered Hopper
A hopper with a single filter slot. Only items matching the filter pass through. Everything else gets ignored. That's the whole thing.

---

### Fast Hopper
Moves 8 items per tick instead of the vanilla 1.

---

### Redstone Clock
A redstone clock in a single block. Right-click to increase the pulse interval by 10 ticks, shift-right-click to decrease it. No more spaghetti repeater loops taking up half your base.

Reset it to factory settings (10 ticks) by right-clicking a redstone torch on the clock block.

*(Configurable — see Config section)*

---

### Fan
A redstone-powered fan that pushes items, mobs, and players in the direction it's facing. Shift-right-click to reverse the flow and pull things toward it instead. Redstone signal strength controls the range. Useful for sorting systems, mob farms, or just annoying your friends.

---

### Charcoal Block
9 charcoal in a crafting grid, one block out. Same burn properties as a coal block. 

---

## Items

### Villager Catcher
Long minecart rail systems snaking across your entire world just to move one villager into your trading hall? We've all been there. We've all hated it. Right-click a villager with the Villager Catcher to imprison the little guy until you're ready to release them.

**⚠ Fair warning:** Your precious Mending villager will not survive the trip with their trades intact. Upon release, every imprisoned villager resets to an unemployed novice. You have been warned.

*(Configurable — see Config section)*

---

### Storage Barrel Upgrades
*(See Storage Barrels above)*
- Copper Barrel Upgrade
- Iron Barrel Upgrade
- Gold Barrel Upgrade
- Diamond Barrel Upgrade

---

### Coal and Charcoal bits
When you need to smelt one thing and don't want to burn through an entire piece of coal.

---

### Obsidian Boat & Chest Boat 
A boat. For lava. Crafted from obsidian. Navigate lava lakes like a reasonable person instead of bridge-pillaring across them.

Don't try it on water. It sinks. Immediately. It's obsidian.

---

### Trowel
Building with varied textures means constantly swapping between block types in your hotbar. The Trowel does the swapping for you — right-click to place a random block from your hotbar. Stacks with more items are more likely to be picked, so your materials deplete evenly. Good for breaking up repetitive surfaces without the busywork.

---

## Config
| Option                                      | Min | Max    | Default |
|---------------------------------------------|-----|--------|---------|
| Storage Barrel base capacity                | 512 | 16,384 | 4,096   |
| Redstone clock pulse interval (in ticks)    | 2   | 300    | 10      |
| Redstone clock plays click sound on pulse   | —   | —      | `true`  |
| Feeding trough animal limit                 | 8   | 128    | 32      |
| Feeding trough feeding radius               | 3×3 | 15×15  | 9×9     |
| Feeding trough feeding interval             | 20  | 1200   | 200     |
| Nest Box collection radius                  | 3×3 | 9×9    | 3×3     |
| Harvester harvest check interval (in ticks) | 20  | 1200   | 200     |
| Harvester work area                         | 3×3 | 15×15  | 9×9     |
| Hoe loses durability in Harvester           | —   | —      | `true`  |
| Villager resets to unemployed on release    | —   | —      | `true`  |

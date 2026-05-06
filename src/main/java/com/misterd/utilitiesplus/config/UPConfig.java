package com.misterd.utilitiesplus.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = "utilitiesplus")
public class UPConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 512, max = 16384)
    public int storageBarrelBaseCapacity = 4096;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 3, max = 15)
    public int harvesterWorkArea = 9;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 20, max = 1200)
    public int harvesterCheckInterval = 200;

    @ConfigEntry.Gui.Tooltip
    public boolean harvesterHoeTakesDamage = true;

    @ConfigEntry.Gui.Tooltip
    public boolean villagerResetOnRelease = true;

    public static UPConfig get() {
        return AutoConfig.getConfigHolder(UPConfig.class).getConfig();
    }

    public static void register() {
        AutoConfig.register(UPConfig.class, GsonConfigSerializer::new);
    }
}
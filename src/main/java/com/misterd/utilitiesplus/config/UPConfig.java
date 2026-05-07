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
    @ConfigEntry.BoundedDiscrete(min = 3, max = 9)
    public int nestBoxCollectionArea = 3;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 8, max = 128)
    public int feedingTroughAnimalLimit = 32;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 20, max = 1200)
    public int feedingTroughFeedInterval = 100;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 3, max = 15)
    public int feedingTroughRadius = 9;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 2, max = 300)
    public int redstoneClockInterval = 10;

    @ConfigEntry.Gui.Tooltip
    public boolean redstoneClockSound = true;

    @ConfigEntry.Gui.Tooltip
    public boolean fanParticles = true;

    @ConfigEntry.Gui.Tooltip
    public boolean villagerResetOnRelease = true;

    public static UPConfig get() {
        return AutoConfig.getConfigHolder(UPConfig.class).getConfig();
    }

    public static void register() {
        AutoConfig.register(UPConfig.class, GsonConfigSerializer::new);
    }
}
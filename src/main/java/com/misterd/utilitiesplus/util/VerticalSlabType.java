package com.misterd.utilitiesplus.util;

import net.minecraft.util.StringRepresentable;

public enum VerticalSlabType implements StringRepresentable {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west"),
    DOUBLE("double");

    private final String name;

    VerticalSlabType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
package com.misterd.utilitiesplus.util;

import net.minecraft.world.entity.animal.Animal;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class FeedingTroughBreedingFlag {
    private static final Set<Animal> troughFed = Collections.newSetFromMap(new WeakHashMap<>());

    public static void mark(Animal animal) {
        troughFed.add(animal);
    }

    public static boolean isMarked(Animal animal) {
        return troughFed.contains(animal);
    }

    public static void clear(Animal animal) {
        troughFed.remove(animal);
    }
}
package com.misterd.utilitiesplus;

import com.misterd.utilitiesplus.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class UtilitiesPlusDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(UPModelProvider::new);
		pack.addProvider(UPBlockTagsProvider::new);
		pack.addProvider(UPItemTagsProvider::new);
		pack.addProvider(UPLootTableProvider::new);
		pack.addProvider(UPRecipeProvider::new);

	}
}

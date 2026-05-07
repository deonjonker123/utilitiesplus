package com.misterd.utilitiesplus;

import com.misterd.utilitiesplus.block.UPBlocks;
import com.misterd.utilitiesplus.blockentity.UPBlockEntities;
import com.misterd.utilitiesplus.config.UPConfig;
import com.misterd.utilitiesplus.entity.UPEntities;
import com.misterd.utilitiesplus.gui.UPMenuTypes;
import com.misterd.utilitiesplus.item.UPCreativeTabs;
import com.misterd.utilitiesplus.item.UPItems;
import com.misterd.utilitiesplus.util.UPFuels;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilitiesPlus implements ModInitializer {
	public static final String MODID = "utilitiesplus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		UPConfig.register();
		UPBlockEntities.register();
		UPMenuTypes.register();
		UPBlocks.register();
		UPItems.register();
		UPCreativeTabs.register();
		UPEntities.register();
		UPFuels.register();
	}
}
package net.pm.fair_deal;

import net.fabricmc.api.ModInitializer;

import net.pm.fair_deal.item.FairDealItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FairDeal implements ModInitializer {
	public static final String MOD_ID = "fair_deal";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		FairDealItems.registerModItems();
	}
}
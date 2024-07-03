package net.pm.fair_deal.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pm.fair_deal.FairDeal;

public class FairDealItems {

    public static final Item EMERALD_SHARD = Registry.register(Registries.ITEM, Identifier.of(FairDeal.MOD_ID, "emerald_shard"), new Item(new Item.Settings()));

    public static void  registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(FairDealItems::addItemsToIngredientTabItemGroup);
    }

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(EMERALD_SHARD);
    }
}

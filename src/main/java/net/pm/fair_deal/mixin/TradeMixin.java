package net.pm.fair_deal.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapDecorationTypes;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.pm.fair_deal.FairProviders;
import net.pm.fair_deal.village.FairFactories;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(TradeOffers.class)
public class TradeMixin {

    @Shadow
    public static Map<VillagerProfession, Int2ObjectMap<TradeOffers.Factory[]>>  PROFESSION_TO_LEVELED_TRADE = Util.make(Maps.newHashMap(), (map) -> {
        map.put(VillagerProfession.FARMER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.WHEAT, 3, 15, 10, 4),
                        new FairFactories.SellItemShardFactory(Items.POTATO, 4, 18, 10, 4),
                        new FairFactories.SellItemShardFactory(Items.CARROT, 3, 13, 10, 4),
                        new FairFactories.SellItemShardFactory(Items.BEETROOT, 3, 18, 10, 4),
                        new FairFactories.SellItemShardFactory(Items.BREAD, 2, 5, 4, 3)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Blocks.PUMPKIN.asItem(), 1, 4, 20, 5),
                        new FairFactories.SellItemShardFactory(Items.PUMPKIN_PIE, 5, 8, 8, 15),
                        new FairFactories.SellItemShardFactory(Items.APPLE, 7, 10, 16, 15)},
                3, new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Items.COOKIE, 1, 32, 7, 20),
                        new FairFactories.SellItemShardFactory(Blocks.MELON.asItem(), 4, 7, 14, 20)},
                4, new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Blocks.CAKE, 2, 1, 12, 50),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.NIGHT_VISION, 2400, 50),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.JUMP_BOOST, 1600, 50),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.GLOWING, 12000, 50),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.WEAKNESS, 1400, 50),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.DARKNESS, 600, 50),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.BLINDNESS, 500, 50),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.POISON, 300, 50),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.SATURATION, 80, 50)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.ProcessItemShardFactory(Items.GOLD_INGOT, 4, 25, Items.GOLDEN_CARROT, 16, 4, 50, 0.1f),
                        new FairFactories.SellItemShardFactory(Items.GLISTERING_MELON_SLICE, 13, 3, 60)})));
        map.put(VillagerProfession.FISHERMAN, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.STRING, 32, 16, 4),
                        new FairFactories.SellItemShardFactory(Items.COAL, 5, 16, 5),
                        new FairFactories.ProcessItemShardFactory(Items.COD, 6, 1, Items.COOKED_COD, 7, 16, 10, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.COD_BUCKET, 5, 1, 16, 15)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.COD, 6, 16, 25),
                        new FairFactories.ProcessItemShardFactory(Items.SALMON, 6, 7, Items.COOKED_SALMON, 8, 16, 30, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.CAMPFIRE, 4, 1, 20)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.SALMON, 7, 16, 30),
                        new TradeOffers.SellEnchantedToolFactory(Items.FISHING_ROD, 2, 3, 70, 0.2F)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.TROPICAL_FISH, 6, 12, 30)},
                5, new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Items.PUFFERFISH, 1, 4, 50),
                        new TradeOffers.TypeAwareBuyForOneEmeraldFactory(1, 12, 60, ImmutableMap.of(
                                VillagerType.PLAINS, Items.OAK_BOAT,
                                VillagerType.TAIGA, Items.SPRUCE_BOAT,
                                VillagerType.SNOW, Items.SPRUCE_BOAT,
                                VillagerType.DESERT, Items.JUNGLE_BOAT,
                                VillagerType.JUNGLE, Items.JUNGLE_BOAT,
                                VillagerType.SAVANNA, Items.ACACIA_BOAT,
                                VillagerType.SWAMP, Items.DARK_OAK_BOAT))
                })));
        map.put(VillagerProfession.SHEPHERD, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Blocks.WHITE_WOOL.asItem(), 4, 16, 5),
                        new FairFactories.SellItemShardFactory(Blocks.BROWN_WOOL.asItem(), 4, 16, 5),
                        new FairFactories.SellItemShardFactory(Blocks.BLACK_WOOL.asItem(), 4, 16, 5),
                        new FairFactories.SellItemShardFactory(Blocks.GRAY_WOOL.asItem(), 4, 16, 5),
                        new TradeOffers.SellItemFactory(Items.SHEARS, 1, 1, 10)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellTagShardFactory(ItemTags.WOOL, 1, 1, 16, 5),
                        new FairFactories.SellTagShardFactory(ItemTags.WOOL_CARPETS, 1, 4, 16, 5)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellTagShardFactory(ItemTags.WOOL, 1, 1, 16, 5),
                        new FairFactories.SellTagShardFactory(ItemTags.BEDS, 5, 1, 12, 10)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellTagShardFactory(ItemTags.WOOL, 1, 1, 16, 5),
                        new FairFactories.SellTagShardFactory(ItemTags.BANNERS, 3, 1, 12, 15)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.BuyTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("c","dyes")), 22, 16, 30),
                        new TradeOffers.SellItemFactory(Items.PAINTING, 2, 3, 30)})));
        map.put(VillagerProfession.FLETCHER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.FEATHER, 32, 16, 10),
                        new FairFactories.SellItemShardFactory(Items.ARROW, 2, 16, 4),
                        new FairFactories.ProcessItemShardFactory(Blocks.GRAVEL, 10, 1, Items.FLINT, 12, 12, 3, 0.05F)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.FLINT, 26, 12, 10),
                        new FairFactories.SellItemShardFactory(Items.BOW, 3, 4, 7)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.STRING, 32, 16, 4),
                        new FairFactories.SellItemShardFactory(Items.CROSSBOW, 7, 2, 20)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.FEATHER, 24, 16, 30),
                        new TradeOffers.SellEnchantedToolFactory(Items.BOW, 1, 3, 15)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.TRIPWIRE_HOOK, 32, 12, 30),
                        new TradeOffers.SellEnchantedToolFactory(Items.CROSSBOW, 3, 3, 15),
                        new TradeOffers.SellPotionHoldingItemFactory(Items.ARROW, 8, Items.TIPPED_ARROW, 8, 1, 12, 30)})));
        map.put(VillagerProfession.LIBRARIAN, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.LEATHER, 24, 16, 5),
                        new FairFactories.SellItemShardFactory(Items.BOOK, 4, 3, 15, 15)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.LANTERN, 3, 1, 32, 10),
                        TradeOffers.createLibrarianTradeFactory(5)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.INK_SAC, 40, 12, 20),
                        TradeOffers.createLibrarianTradeFactory(10),
                        new FairFactories.SellItemShardFactory(Items.GLASS, 6, 16, 10, 10)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.WRITABLE_BOOK, 7, 12,8, 30),
                        new FairFactories.SellItemShardFactory(Items.CLOCK, 9, 1, 15),
                        new FairFactories.SellItemShardFactory(Items.COMPASS, 7, 1, 15)},
                5, new TradeOffers.Factory[]{
                        TradeOffers.createMasterLibrarianTradeFactory(),
                        new FairFactories.SellItemShardFactory(Items.NAME_TAG, 20, 1, 30)})));
        map.put(VillagerProfession.CARTOGRAPHER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.PAPER, 64, 3, 10),
                        new FairFactories.SellItemShardFactory(Items.MAP, 5, 1, 30)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.GLASS_PANE, 7, 32, 10),
                        new TradeOffers.TypedWrapperFactory(ImmutableMap.of(
                                VillagerType.DESERT, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SAVANNA_VILLAGE_MAPS, "filled_map.village_savanna", MapDecorationTypes.VILLAGE_SAVANNA, 12, 15),
                                VillagerType.SAVANNA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_PLAINS_VILLAGE_MAPS, "filled_map.village_plains", MapDecorationTypes.VILLAGE_PLAINS, 12, 15),
                                VillagerType.PLAINS, new FairFactories.SellMapShardFactory(8, StructureTags.ON_TAIGA_VILLAGE_MAPS, "filled_map.village_taiga", MapDecorationTypes.VILLAGE_TAIGA, 12, 15),
                                VillagerType.TAIGA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SNOWY_VILLAGE_MAPS, "filled_map.village_snowy", MapDecorationTypes.VILLAGE_SNOWY, 12, 15),
                                VillagerType.SNOW, new FairFactories.SellMapShardFactory(8, StructureTags.ON_PLAINS_VILLAGE_MAPS, "filled_map.village_plains", MapDecorationTypes.VILLAGE_PLAINS, 12, 15),
                                VillagerType.JUNGLE, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SAVANNA_VILLAGE_MAPS, "filled_map.village_savanna", MapDecorationTypes.VILLAGE_SAVANNA, 12, 15),
                                VillagerType.SWAMP, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SNOWY_VILLAGE_MAPS, "filled_map.village_snowy", MapDecorationTypes.VILLAGE_SNOWY, 12, 15))),
                        new TradeOffers.TypedWrapperFactory(ImmutableMap.of(
                                VillagerType.DESERT, new FairFactories.SellMapShardFactory(8, StructureTags.ON_PLAINS_VILLAGE_MAPS, "filled_map.village_plains", MapDecorationTypes.VILLAGE_PLAINS, 12, 15),
                                VillagerType.SAVANNA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_DESERT_VILLAGE_MAPS, "filled_map.village_desert", MapDecorationTypes.VILLAGE_DESERT, 12, 15),
                                VillagerType.PLAINS, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SAVANNA_VILLAGE_MAPS, "filled_map.village_savanna", MapDecorationTypes.VILLAGE_SAVANNA, 12, 15),
                                VillagerType.TAIGA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_PLAINS_VILLAGE_MAPS, "filled_map.village_plains", MapDecorationTypes.VILLAGE_PLAINS, 12, 15),
                                VillagerType.SNOW, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SNOWY_VILLAGE_MAPS, "filled_map.village_snowy", MapDecorationTypes.VILLAGE_SNOWY, 12, 15),
                                VillagerType.JUNGLE, new FairFactories.SellMapShardFactory(8, StructureTags.ON_DESERT_VILLAGE_MAPS, "filled_map.village_desert", MapDecorationTypes.VILLAGE_DESERT, 12, 15),
                                VillagerType.SWAMP, new FairFactories.SellMapShardFactory(8, StructureTags.ON_TAIGA_VILLAGE_MAPS, "filled_map.village_taiga", MapDecorationTypes.VILLAGE_TAIGA, 12, 15))),
                        new TradeOffers.TypedWrapperFactory(ImmutableMap.of(
                                VillagerType.DESERT, new FairFactories.SellMapShardFactory(8, StructureTags.ON_JUNGLE_EXPLORER_MAPS, "filled_map.explorer_jungle", MapDecorationTypes.JUNGLE_TEMPLE, 12, 15),
                                VillagerType.SAVANNA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_JUNGLE_EXPLORER_MAPS, "filled_map.explorer_jungle", MapDecorationTypes.JUNGLE_TEMPLE, 12, 15),
                                VillagerType.PLAINS, new FairFactories.SellMapShardFactory(10, TagKey.of(RegistryKeys.STRUCTURE, Identifier.of("fair_deal", "on_ruin_maps")), "fair_deal.filled_map.ruins", MapDecorationTypes.RED_X, 12, 20),
                                VillagerType.TAIGA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SWAMP_EXPLORER_MAPS, "filled_map.explorer_swamp", MapDecorationTypes.SWAMP_HUT, 12, 15),
                                VillagerType.SNOW, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SWAMP_EXPLORER_MAPS, "filled_map.explorer_swamp", MapDecorationTypes.SWAMP_HUT, 12, 15),
                                VillagerType.JUNGLE, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SWAMP_EXPLORER_MAPS, "filled_map.explorer_swamp", MapDecorationTypes.SWAMP_HUT, 12, 15),
                                VillagerType.SWAMP, new FairFactories.SellMapShardFactory(8, StructureTags.ON_JUNGLE_EXPLORER_MAPS, "filled_map.explorer_jungle", MapDecorationTypes.JUNGLE_TEMPLE, 12, 15)))},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.COMPASS, 1, 9, 20,3),
                        new FairFactories.SellMapShardFactory(17, StructureTags.ON_OCEAN_EXPLORER_MAPS, "filled_map.monument", MapDecorationTypes.MONUMENT, 12, 50),
                        new FairFactories.SellMapShardFactory(19, StructureTags.ON_TRIAL_CHAMBERS_MAPS, "filled_map.trial_chambers", MapDecorationTypes.TRIAL_CHAMBERS, 12, 50)},
                4, new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Items.ITEM_FRAME, 7, 1, 15),
                        new FairFactories.SellTagShardFactory(ItemTags.BANNERS, 3, 1, 12, 15)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.GLOBE_BANNER_PATTERN, 59, 1, 50),
                        new FairFactories.SellMapShardFactory(64, StructureTags.ON_WOODLAND_EXPLORER_MAPS, "filled_map.mansion", MapDecorationTypes.MANSION, 1, 70)})));
        map.put(VillagerProfession.CLERIC, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.ROTTEN_FLESH, 4, 16, 5),
                        new FairFactories.SellItemShardFactory(Items.REDSTONE, 1, 2, 16,1)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.GOLD_INGOT, 16, 12, 10),
                        new FairFactories.SellItemShardFactory(Items.LAPIS_LAZULI, 10, 13, 20)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.RABBIT_FOOT, 7, 12, 20),
                        new FairFactories.SellItemShardFactory(Blocks.GLOWSTONE, 4, 1, 12, 10)},
                4, new TradeOffers.Factory[]{
                        new TradeOffers.BuyItemFactory(Items.TURTLE_SCUTE, 13, 12, 30),
                        new FairFactories.BuyItemShardFactory(Items.GLASS_BOTTLE, 60, 12, 30),
                        new FairFactories.SellItemShardFactory(Items.ENDER_PEARL, 36, 1, 45)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.LONG_INVISIBILITY), 11, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.REGENERATION), 13, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.HEALING), 11, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.STRENGTH), 19, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.WEAKNESS), 21, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.LEAPING), 11, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(Items.EXPERIENCE_BOTTLE, 3, 1, 30)})));
        map.put(VillagerProfession.ARMORER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.COAL, 64, 12, 4),
                        new FairFactories.BuyItemShardFactory(Items.IRON_INGOT, 32, 12, 4)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 4, 1, 12, 10, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 5, 1, 12, 10, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.CHAINMAIL_LEGGINGS, 7, 1, 12, 15, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.CHAINMAIL_CHESTPLATE, 8, 1, 12, 20, 0.05F)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.LAVA_BUCKET, 1, 4, 20),
                        new FairFactories.SellItemShardFactory(Items.SHIELD, 5, 1, 12, 10, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.BELL, 36, 1, 12, 10, 0.2F)},
                4, new TradeOffers.Factory[]{
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 8, 1, 3, 15, 0.05F, FairProviders.DESERT_ARMORER), VillagerType.DESERT),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 9, 1, 3, 15, 0.05F, FairProviders.DESERT_ARMORER), VillagerType.DESERT),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_LEGGINGS, 11, 1, 3, 15, 0.05F, FairProviders.DESERT_ARMORER), VillagerType.DESERT),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_CHESTPLATE, 13, 1, 3, 15, 0.05F, FairProviders.DESERT_ARMORER), VillagerType.DESERT),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 8, 1, 3, 15, 0.05F, FairProviders.PLAINS_ARMORER), VillagerType.PLAINS),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 9, 1, 3, 15, 0.05F, FairProviders.PLAINS_ARMORER), VillagerType.PLAINS),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_LEGGINGS, 11, 1, 3, 15, 0.05F, FairProviders.PLAINS_ARMORER), VillagerType.PLAINS),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_CHESTPLATE, 13, 1, 3, 15, 0.05F, FairProviders.PLAINS_ARMORER), VillagerType.PLAINS),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 2, 1, 3, 15, 0.05F, FairProviders.SAVANNA_ARMORER), VillagerType.SAVANNA),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 3, 1, 3, 15, 0.05F, FairProviders.SAVANNA_ARMORER), VillagerType.SAVANNA),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_LEGGINGS, 5, 1, 3, 15, 0.05F, FairProviders.SAVANNA_ARMORER), VillagerType.SAVANNA),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_CHESTPLATE, 7, 1, 3, 15, 0.05F, FairProviders.SAVANNA_ARMORER), VillagerType.SAVANNA),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 8, 1, 3, 15, 0.05F, FairProviders.SNOW_ARMORER), VillagerType.SNOW),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 9, 1, 3, 15, 0.05F, FairProviders.SNOW_ARMORER), VillagerType.SNOW),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 8, 1, 3, 15, 0.05F, FairProviders.JUNGLE_ARMORER), VillagerType.JUNGLE),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 9, 1, 3, 15, 0.05F, FairProviders.JUNGLE_ARMORER), VillagerType.JUNGLE),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_LEGGINGS, 11, 1, 3, 15, 0.05F, FairProviders.JUNGLE_ARMORER), VillagerType.JUNGLE),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_CHESTPLATE, 13, 1, 3, 15, 0.05F, FairProviders.JUNGLE_ARMORER), VillagerType.JUNGLE),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.IRON_BOOTS, 8, 1, 3, 15, 0.05F, FairProviders.SWAMP_ARMORER), VillagerType.SWAMP),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.IRON_HELMET, 9, 1, 3, 15, 0.05F, FairProviders.SWAMP_ARMORER), VillagerType.SWAMP),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.IRON_LEGGINGS, 11, 1, 3, 15, 0.05F, FairProviders.SWAMP_ARMORER), VillagerType.SWAMP),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.IRON_CHESTPLATE, 13, 1, 3, 15, 0.05F, FairProviders.SWAMP_ARMORER), VillagerType.SWAMP),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 2, 4, Items.DIAMOND_BOOTS, 1, 3, 15, 0.05F), VillagerType.TAIGA),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 3, 4, Items.DIAMOND_HELMET, 1, 3, 15, 0.05F), VillagerType.TAIGA),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 4, 4, Items.DIAMOND_LEGGINGS, 1, 3, 15, 0.05F), VillagerType.TAIGA),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 5, 2, Items.DIAMOND_CHESTPLATE, 1, 3, 15, 0.05F), VillagerType.TAIGA)},
                5, new TradeOffers.Factory[]{
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 4, 16, Items.DIAMOND_CHESTPLATE, 1, 3, 30, 0.05F, FairProviders.DESERT_ARMORER), VillagerType.DESERT),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 3, 16, Items.DIAMOND_LEGGINGS, 1, 3, 30, 0.05F, FairProviders.DESERT_ARMORER), VillagerType.DESERT),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 3, 16, Items.DIAMOND_LEGGINGS, 1, 3, 30, 0.05F, FairProviders.PLAINS_ARMORER), VillagerType.PLAINS),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 2, 12, Items.DIAMOND_BOOTS, 1, 3, 30, 0.05F, FairProviders.PLAINS_ARMORER), VillagerType.PLAINS),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 2, 6, Items.DIAMOND_HELMET, 1, 3, 30, 0.05F, FairProviders.SAVANNA_ARMORER), VillagerType.SAVANNA),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 3, 8, Items.DIAMOND_CHESTPLATE, 1, 3, 30, 0.05F, FairProviders.SAVANNA_ARMORER), VillagerType.SAVANNA),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 2, 12, Items.DIAMOND_BOOTS, 1, 3, 30, 0.05F, FairProviders.SNOW_ARMORER), VillagerType.SNOW),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 3, 12, Items.DIAMOND_HELMET, 1, 3, 30, 0.05F, FairProviders.SNOW_ARMORER), VillagerType.SNOW),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 3, 12, Items.DIAMOND_HELMET, 1, 3, 30, 0.05F, FairProviders.JUNGLE_ARMORER), VillagerType.JUNGLE),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 2, 12, Items.DIAMOND_BOOTS, 1, 3, 30, 0.05F, FairProviders.JUNGLE_ARMORER), VillagerType.JUNGLE),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 3, 12, Items.DIAMOND_HELMET, 1, 3, 30, 0.05F, FairProviders.SWAMP_ARMORER), VillagerType.SWAMP),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 2, 12, Items.DIAMOND_BOOTS, 1, 3, 30, 0.05F, FairProviders.SWAMP_ARMORER), VillagerType.SWAMP),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 4, 18, Items.DIAMOND_CHESTPLATE, 1, 3, 30, 0.05F, FairProviders.TAIGA_ARMORER), VillagerType.TAIGA),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 3, 18, Items.DIAMOND_LEGGINGS, 1, 3, 30, 0.05F, FairProviders.TAIGA_ARMORER), VillagerType.TAIGA),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.BuyItemFactory(Items.DIAMOND_BLOCK, 1, 12, 30, 4), VillagerType.TAIGA),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.BuyItemShardFactory(Items.IRON_BLOCK, 6, 12, 30, 4), VillagerType.DESERT, VillagerType.JUNGLE, VillagerType.PLAINS, VillagerType.SAVANNA, VillagerType.SNOW, VillagerType.SWAMP)})));
        map.put(VillagerProfession.WEAPONSMITH, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.COAL, 64, 12, 4),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.STONE_AXE), 3, 1, 12, 6, 0.2F),
                        new FairFactories.SellItemShardFactory(Items.STONE_SWORD, 2, 3, 1, 6)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.IRON_INGOT, 32, 12, 4),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.FLINT, 24, 12, 20)},
                4, new TradeOffers.Factory[]{
                        new TradeOffers.BuyItemFactory(Items.DIAMOND, 4, 12, 30),
                        new FairFactories.ProcessItemShardFactory(Items.DIAMOND, 2, 14, Items.DIAMOND_AXE, 1, 3, 25, 0.2F)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.ProcessItemShardFactory(Items.DIAMOND, 1, 14, Items.DIAMOND_SWORD, 1, 3, 25, 0.2F)})));
        map.put(VillagerProfession.TOOLSMITH, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.COAL, 64, 12, 4),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.STONE_AXE), 1, 1, 12, 3, 0.2F),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.STONE_SHOVEL), 1, 1, 12, 3, 0.2F),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.STONE_PICKAXE), 1, 1, 12, 3, 0.2F),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.STONE_HOE), 1, 1, 12, 3, 0.2F)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.IRON_INGOT, 32, 12, 4),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.FLINT, 24, 12, 20),
                        new FairFactories.SellEnchantedShardFactory(Items.IRON_AXE, 1, 3, 10, 0.2F),
                        new FairFactories.SellEnchantedShardFactory(Items.IRON_SHOVEL, 2, 3, 10, 0.2F),
                        new FairFactories.SellEnchantedShardFactory(Items.IRON_PICKAXE, 3, 3, 10, 0.2F)},
                4, new TradeOffers.Factory[]{
                        new TradeOffers.BuyItemFactory(Items.DIAMOND, 4, 12, 30),
                        new FairFactories.ProcessItemShardFactory(Items.DIAMOND, 2, 14, Items.DIAMOND_AXE, 1, 3, 25, 0.2F),
                        new FairFactories.ProcessItemShardFactory(Items.DIAMOND, 1, 4, Items.DIAMOND_SHOVEL, 1, 3, 25, 0.2F)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.ProcessItemShardFactory(Items.DIAMOND, 2, 19, Items.DIAMOND_PICKAXE, 1, 3, 25, 0.2F)})));
        map.put(VillagerProfession.BUTCHER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.CHICKEN, 3, 2, 2),
                        new FairFactories.SellItemShardFactory(Items.PORKCHOP, 4, 3, 3),
                        new FairFactories.SellItemShardFactory(Items.RABBIT, 2, 2, 3),
                        new FairFactories.SellItemShardFactory(Items.RABBIT_STEW, 3, 1, 3)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.COOKED_PORKCHOP, 7, 5, 3, 5),
                        new FairFactories.SellItemShardFactory(Items.COOKED_CHICKEN, 5, 8, 4, 5)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.MUTTON, 7, 16, 20),
                        new FairFactories.SellItemShardFactory(Items.BEEF, 10, 16, 20)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.DRIED_KELP_BLOCK, 5, 12, 30)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.SWEET_BERRIES, 64, 9, 30)})));
        map.put(VillagerProfession.LEATHERWORKER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.LEATHER, 6, 16, 2),
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_LEGGINGS, 3),
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_CHESTPLATE, 7)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_HELMET, 5, 12, 5),
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_BOOTS, 4, 12, 5)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.RABBIT_HIDE, 46, 12, 20),
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_CHESTPLATE, 7)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.TURTLE_SCUTE, 8, 12, 30),
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_HORSE_ARMOR, 6, 12, 15)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.SADDLE), 9, 1, 12, 30, 0.2F),
                        new FairFactories.SellItemShardFactory(Items.BUNDLE, 12, 1, 30)})));
        map.put(VillagerProfession.MASON, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.CLAY_BALL, 1, 2, 3),
                        new FairFactories.SellItemShardFactory(Items.BRICK, 2, 5, 16, 5)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Blocks.STONE.asItem(), 1, 16, 10),
                        new FairFactories.SellItemShardFactory(Blocks.CHISELED_STONE_BRICKS, 1, 8, 16, 5)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Blocks.GRANITE.asItem(), 2, 16, 20),
                        new FairFactories.SellItemShardFactory(Blocks.ANDESITE.asItem(), 2, 16, 20),
                        new FairFactories.SellItemShardFactory(Blocks.DIORITE.asItem(), 2, 16, 20),
                        new FairFactories.SellItemShardFactory(Blocks.DRIPSTONE_BLOCK, 3, 4, 16, 10),
                        new FairFactories.SellItemShardFactory(Blocks.POLISHED_ANDESITE, 1, 4, 16, 10),
                        new FairFactories.SellItemShardFactory(Blocks.POLISHED_DIORITE, 1, 4, 16, 10),
                        new FairFactories.SellItemShardFactory(Blocks.POLISHED_GRANITE, 1, 4, 16, 10)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellTagShardFactory(ItemTags.TERRACOTTA, 3, 4, 12, 15),
                        new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("c","glazed_terracottas")), 1, 4, 30)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Blocks.QUARTZ_PILLAR, 3, 1, 12, 30),
                        new FairFactories.SellItemShardFactory(Blocks.QUARTZ_BLOCK, 4, 1, 12, 30)})));
    });

    @Shadow
    public static final Int2ObjectMap<TradeOffers.Factory[]> WANDERING_TRADER_TRADES = TradeOffers.copyToFastUtilMap(
            ImmutableMap.of(1, new TradeOffers.Factory[]{
                    new FairFactories.SellItemShardFactory(Items.SLIME_BALL, 4, 1, 5, 1),
                    new FairFactories.SellItemShardFactory(Items.GLOWSTONE, 2, 1, 5, 1),
                    new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("fair_deal","wandering_trades/flowers")), 3, 6, 12, 1),
                    new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("fair_deal","wandering_trades/seeds")), 1, 1, 12, 1),
                    new FairFactories.SellTagShardFactory(ItemTags.SAPLINGS, 5, 1, 8, 1),
                    new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("c","dyes")), 2, 8, 12, 1),
                    new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("c","corals")), 4, 2, 8, 1),
                    new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("fair_deal","wandering_trades/vegetation")), 3, 1, 5, 1),
                    new FairFactories.SellItemShardFactory(Items.SAND, 1, 8, 8, 1),
                    new FairFactories.SellItemShardFactory(Items.RED_SAND, 1, 4, 8, 1),
                    new FairFactories.SellItemShardFactory(Items.POINTED_DRIPSTONE, 3, 2, 5, 1),
                    new FairFactories.SellItemShardFactory(Items.ROOTED_DIRT, 1, 2, 5, 1),
                    new FairFactories.BuyItemShardFactory(Items.MILK_BUCKET, 1, 1, 1, 2),
                    new FairFactories.BuyItemShardFactory(Items.FERMENTED_SPIDER_EYE, 3, 4, 1, 3),
                    new FairFactories.BuyItemShardFactory(Items.BAKED_POTATO, 8, 8, 1),
                    new FairFactories.BuyItemShardFactory(Items.HAY_BLOCK, 16, 8, 1)
            }, 2, new TradeOffers.Factory[]{
                    new FairFactories.SellItemShardFactory(Items.TROPICAL_FISH_BUCKET, 5, 1, 4, 1),
                    new FairFactories.SellItemShardFactory(Items.PUFFERFISH_BUCKET, 5, 1, 4, 1),
                    new FairFactories.SellItemShardFactory(Items.BLUE_ICE, 4, 2, 6, 1),
                    new FairFactories.SellItemShardFactory(Items.NAUTILUS_SHELL, 5, 1, 5, 1),
                    new FairFactories.SellItemShardFactory(Items.GUNPOWDER, 1, 8, 8, 1),
                    new FairFactories.SellItemShardFactory(Items.LAVA_BUCKET, 6, 1, 1, 2),
                    new FairFactories.SellEnchantedShardFactory(Items.IRON_PICKAXE, 1, 1, 1, 0.2F),
                    new FairFactories.SellEnchantedShardFactory(Items.FISHING_ROD, 1, 1, 1, 0.2F),
                    new FairFactories.SellEnchantedShardFactory(Items.IRON_SWORD, 1, 1, 1, 0.2F),
                    new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.LONG_INVISIBILITY), 11, 1, 1, 1)
            }));
}

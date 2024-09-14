package net.pm.fair_deal.mixin;

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
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.pm.fair_deal.FairDeal;
import net.pm.fair_deal.FairProviders;
import net.pm.fair_deal.village.FairFactories;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(TradeOffers.class)
public class TradeMixin {

    @Shadow
    public static Map<VillagerProfession, Int2ObjectMap<TradeOffers.Factory[]>>  PROFESSION_TO_LEVELED_TRADE = Util.make(Maps.newHashMap(), (map) -> {
        map.put(VillagerProfession.FARMER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of(FairDeal.MOD_ID,"farmer/crop_sell_init")), 2, 15, 10, 1),
                        new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of(FairDeal.MOD_ID,"farmer/crop_sell_init")), 3, 18, 10, 1),
                        new FairFactories.SellItemShardFactory(Items.BREAD, 4, 5, 4, 2)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of(FairDeal.MOD_ID,"farmer/crop_sell_adv")), 3, 8, 10, 5),
                        new FairFactories.SellItemShardFactory(Items.PUMPKIN_PIE, 10, 8, 8, 10),
                        new FairFactories.BuyItemShardFactory(Items.BONE_MEAL, 24, 10, 5, 10)},
                3, new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Items.COOKIE, 2, 32, 7, 10),
                        new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of(FairDeal.MOD_ID,"farmer/crop_sell_adv")), 3, 10, 10, 5),
                        new FairFactories.BuyItemShardFactory(Items.BONE_MEAL, 24, 10, 5, 10)},
                4, new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Blocks.CAKE, 4, 1, 12, 15),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.NIGHT_VISION, 2400, 20),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.JUMP_BOOST, 1600, 20),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.GLOWING, 12000, 20),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.WEAKNESS, 1400, 20),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.DARKNESS, 600, 20),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.BLINDNESS, 500, 20),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.POISON, 300, 20),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.SATURATION, 80, 20)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.ProcessItemShardFactory(Items.GOLD_INGOT, 4, 25, Items.GOLDEN_CARROT, 16, 4, 30, 0.1f),
                        new FairFactories.ProcessItemShardFactory(Items.GOLD_INGOT, 4, 25, Items.GLISTERING_MELON_SLICE, 16, 4, 30, 0.1f)})));
        map.put(VillagerProfession.FISHERMAN, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.STRING, 32, 16, 1),
                        new FairFactories.ProcessItemShardFactory(Items.COD, 6, 2, Items.COOKED_COD, 7, 16, 2, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.COD_BUCKET, 5, 1, 16, 3)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.COD, 3, 4, 5),
                        new FairFactories.ProcessItemShardFactory(Items.SALMON, 6, 6, Items.COOKED_SALMON, 8, 16, 5, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.CAMPFIRE, 8, 1, 5)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.SALMON, 4, 5, 10),
                        new TradeOffers.SellEnchantedToolFactory(Items.FISHING_ROD, 2, 3, 10, 0.2F)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.TROPICAL_FISH, 12, 12, 20)},
                5, new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Items.PUFFERFISH, 8, 2, 20),
                        new TradeOffers.TypeAwareBuyForOneEmeraldFactory(1, 3, 20, ImmutableMap.of(
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
                        new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of(FairDeal.MOD_ID,"shepherd/natural_wool")), 2, 5, 10, 1),
                        new FairFactories.SellItemShardFactory(Items.SHEARS, 4, 1, 2)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellTagShardFactory(ItemTags.WOOL, 2, 1, 16, 5),
                        new FairFactories.SellTagShardFactory(ItemTags.WOOL_CARPETS, 2, 4, 16, 5)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellTagShardFactory(ItemTags.WOOL, 2, 1, 16, 5),
                        new FairFactories.SellTagShardFactory(ItemTags.BANNERS, 7, 1, 12, 10)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellTagShardFactory(ItemTags.WOOL, 3, 2, 16, 10),
                        new FairFactories.SellTagShardFactory(ItemTags.BEDS, 10, 1, 12, 10)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.BuyTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("c","dyes")), 22, 16, 30),
                        new TradeOffers.SellItemFactory(Items.PAINTING, 4, 3, 30)})));
        map.put(VillagerProfession.FLETCHER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.FEATHER, 20, 16, 1),
                        new FairFactories.SellItemShardFactory(Items.ARROW, 4, 16, 2),
                        new FairFactories.ProcessItemShardFactory(Blocks.GRAVEL, 10, 1, Items.FLINT, 12, 12, 3, 0.05F)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.FLINT, 26, 12, 5),
                        new FairFactories.SellItemShardFactory(Items.BOW, 6, 1, 10)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.STRING, 32, 16, 10),
                        new FairFactories.SellItemShardFactory(Items.CROSSBOW, 14, 1, 15)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.FEATHER, 16, 16, 15),
                        new TradeOffers.SellEnchantedToolFactory(Items.BOW, 1, 3, 25)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.TRIPWIRE_HOOK, 32, 12, 30),
                        new TradeOffers.SellEnchantedToolFactory(Items.CROSSBOW, 3, 3, 30),
                        new TradeOffers.SellPotionHoldingItemFactory(Items.ARROW, 8, Items.TIPPED_ARROW, 8, 2, 12, 30)})));
        map.put(VillagerProfession.LIBRARIAN, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.LEATHER, 24, 16, 1),
                        new FairFactories.SellItemShardFactory(Items.BOOK, 5, 3, 15, 2),
                        new FairFactories.BuyItemShardFactory(Items.INK_SAC, 40, 12, 1)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.LANTERN, 4, 1, 32, 3),
                        new FairFactories.SellItemShardFactory(Items.CHISELED_BOOKSHELF, 4, 2, 10, 5),
                        TradeOffers.createLibrarianTradeFactory(5),
                        new FairFactories.ProcessItemShardFactory(Items.LEATHER, 2, 2, Items.BOOK, 3, 5, 10, 0.1f)},
                3, new TradeOffers.Factory[]{
                        TradeOffers.createLibrarianTradeFactory(15),
                        new FairFactories.SellItemShardFactory(Items.CLOCK, 18, 1, 15),
                        new FairFactories.SellItemShardFactory(Items.COMPASS, 14, 1, 15)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.WRITABLE_BOOK, 14, 12,8, 20),
                        FairFactories.createMasterLibrarianTradeFactoryF()},
                5, new TradeOffers.Factory[]{
                        new TradeOffers.EnchantBookFactory(30, TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(FairDeal.MOD_ID, "trades/librarian_master"))),
                        new FairFactories.SellItemShardFactory(Items.NAME_TAG, 20, 3, 30)})));
        map.put(VillagerProfession.CARTOGRAPHER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.PAPER, 64, 3, 1),
                        new FairFactories.SellItemShardFactory(Items.MAP, 3, 1, 2),
                        new FairFactories.SellItemShardFactory(Items.GLASS_PANE, 3, 7, 2),},
                2, new TradeOffers.Factory[]{
                        new TradeOffers.TypedWrapperFactory(ImmutableMap.of(
                                VillagerType.DESERT, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SAVANNA_VILLAGE_MAPS, "filled_map.village_savanna", MapDecorationTypes.VILLAGE_SAVANNA, 12, 10),
                                VillagerType.SAVANNA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_PLAINS_VILLAGE_MAPS, "filled_map.village_plains", MapDecorationTypes.VILLAGE_PLAINS, 12, 10),
                                VillagerType.PLAINS, new FairFactories.SellMapShardFactory(8, StructureTags.ON_TAIGA_VILLAGE_MAPS, "filled_map.village_taiga", MapDecorationTypes.VILLAGE_TAIGA, 12, 10),
                                VillagerType.TAIGA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SNOWY_VILLAGE_MAPS, "filled_map.village_snowy", MapDecorationTypes.VILLAGE_SNOWY, 12, 10),
                                VillagerType.SNOW, new FairFactories.SellMapShardFactory(8, StructureTags.ON_PLAINS_VILLAGE_MAPS, "filled_map.village_plains", MapDecorationTypes.VILLAGE_PLAINS, 12, 10),
                                VillagerType.JUNGLE, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SAVANNA_VILLAGE_MAPS, "filled_map.village_savanna", MapDecorationTypes.VILLAGE_SAVANNA, 12, 10),
                                VillagerType.SWAMP, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SNOWY_VILLAGE_MAPS, "filled_map.village_snowy", MapDecorationTypes.VILLAGE_SNOWY, 12, 10))),
                        new TradeOffers.TypedWrapperFactory(ImmutableMap.of(
                                VillagerType.DESERT, new FairFactories.SellMapShardFactory(8, StructureTags.ON_PLAINS_VILLAGE_MAPS, "filled_map.village_plains", MapDecorationTypes.VILLAGE_PLAINS, 12, 10),
                                VillagerType.SAVANNA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_DESERT_VILLAGE_MAPS, "filled_map.village_desert", MapDecorationTypes.VILLAGE_DESERT, 12, 10),
                                VillagerType.PLAINS, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SAVANNA_VILLAGE_MAPS, "filled_map.village_savanna", MapDecorationTypes.VILLAGE_SAVANNA, 12, 10),
                                VillagerType.TAIGA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_PLAINS_VILLAGE_MAPS, "filled_map.village_plains", MapDecorationTypes.VILLAGE_PLAINS, 12, 10),
                                VillagerType.SNOW, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SNOWY_VILLAGE_MAPS, "filled_map.village_snowy", MapDecorationTypes.VILLAGE_SNOWY, 12, 10),
                                VillagerType.JUNGLE, new FairFactories.SellMapShardFactory(8, StructureTags.ON_DESERT_VILLAGE_MAPS, "filled_map.village_desert", MapDecorationTypes.VILLAGE_DESERT, 12, 10),
                                VillagerType.SWAMP, new FairFactories.SellMapShardFactory(8, StructureTags.ON_TAIGA_VILLAGE_MAPS, "filled_map.village_taiga", MapDecorationTypes.VILLAGE_TAIGA, 12, 10))),
                        new TradeOffers.TypedWrapperFactory(ImmutableMap.of(
                                VillagerType.DESERT, new FairFactories.SellMapShardFactory(8, StructureTags.ON_JUNGLE_EXPLORER_MAPS, "filled_map.explorer_jungle", MapDecorationTypes.JUNGLE_TEMPLE, 12, 15),
                                VillagerType.SAVANNA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_JUNGLE_EXPLORER_MAPS, "filled_map.explorer_jungle", MapDecorationTypes.JUNGLE_TEMPLE, 12, 15),
                                VillagerType.PLAINS, new FairFactories.SellMapShardFactory(10, TagKey.of(RegistryKeys.STRUCTURE, Identifier.of("fair_deal", "on_ruin_maps")), "fair_deal.filled_map.ruins", MapDecorationTypes.RED_X, 12, 20),
                                VillagerType.TAIGA, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SWAMP_EXPLORER_MAPS, "filled_map.explorer_swamp", MapDecorationTypes.SWAMP_HUT, 12, 15),
                                VillagerType.SNOW, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SWAMP_EXPLORER_MAPS, "filled_map.explorer_swamp", MapDecorationTypes.SWAMP_HUT, 12, 15),
                                VillagerType.JUNGLE, new FairFactories.SellMapShardFactory(8, StructureTags.ON_SWAMP_EXPLORER_MAPS, "filled_map.explorer_swamp", MapDecorationTypes.SWAMP_HUT, 12, 15),
                                VillagerType.SWAMP, new FairFactories.SellMapShardFactory(8, StructureTags.ON_JUNGLE_EXPLORER_MAPS, "filled_map.explorer_jungle", MapDecorationTypes.JUNGLE_TEMPLE, 12, 15)))},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.COMPASS, 1, 9, 15,2),
                        new FairFactories.SellMapShardFactory(27, StructureTags.ON_OCEAN_EXPLORER_MAPS, "filled_map.monument", MapDecorationTypes.MONUMENT, 12, 15),
                        new FairFactories.SellMapShardFactory(32, StructureTags.ON_TRIAL_CHAMBERS_MAPS, "filled_map.trial_chambers", MapDecorationTypes.TRIAL_CHAMBERS, 12, 15)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.ITEM_FRAME, 3, 1, 15),
                        new FairFactories.SellTagShardFactory(ItemTags.BANNERS, 7, 2, 12, 15)},
                5, new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Items.GLOBE_BANNER_PATTERN, 11, 1, 30),
                        new TradeOffers.SellMapFactory(16, StructureTags.ON_WOODLAND_EXPLORER_MAPS, "filled_map.mansion", MapDecorationTypes.MANSION, 1, 30)})));
        map.put(VillagerProfession.CLERIC, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.ROTTEN_FLESH, 8, 16, 2),
                        new FairFactories.SellItemShardFactory(Items.REDSTONE, 4, 2, 16,1)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.GOLD_INGOT, 16, 12, 5),
                        new FairFactories.SellItemShardFactory(Items.LAPIS_LAZULI, 10, 13, 10)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.RABBIT_FOOT, 7, 12, 10),
                        new FairFactories.SellItemShardFactory(Blocks.GLOWSTONE, 16, 1, 12, 20)},
                4, new TradeOffers.Factory[]{
                        new TradeOffers.BuyItemFactory(Items.TURTLE_SCUTE, 5, 12, 15),
                        new FairFactories.BuyItemShardFactory(Items.GLASS_BOTTLE, 60, 12, 15),
                        new FairFactories.SellItemShardFactory(Items.ENDER_PEARL, 36, 1, 30)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.LONG_INVISIBILITY), 22, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.REGENERATION), 26, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.HEALING), 22, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.STRENGTH), 38, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.WEAKNESS), 42, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.LEAPING), 22, 1, 1, 30),
                        new FairFactories.SellItemShardFactory(Items.EXPERIENCE_BOTTLE, 6, 1, 30)})));
        map.put(VillagerProfession.ARMORER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.COAL, 12, 12, 1),
                        new FairFactories.BuyItemShardFactory(Items.IRON_INGOT, 10, 12, 1)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 8, 1, 12, 5, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 9, 1, 12, 5, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.CHAINMAIL_LEGGINGS, 14, 1, 12, 5, 0.05F),
                        new FairFactories.SellItemShardFactory(Items.CHAINMAIL_CHESTPLATE, 16, 1, 12, 5, 0.05F)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.LAVA_BUCKET, 1, 4, 10),
                        new FairFactories.SellItemShardFactory(Items.SHIELD, 10, 1, 12, 10, 0.05F),
                        new TradeOffers.SellItemFactory(Items.BELL, 20, 1, 12, 30, 0.2F)},
                4, new TradeOffers.Factory[]{
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 16, 1, 3, 15, 0.05F, FairProviders.DESERT_ARMORER), VillagerType.DESERT),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 18, 1, 3, 15, 0.05F, FairProviders.DESERT_ARMORER), VillagerType.DESERT),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_LEGGINGS, 22, 1, 3, 15, 0.05F, FairProviders.DESERT_ARMORER), VillagerType.DESERT),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_CHESTPLATE, 26, 1, 3, 15, 0.05F, FairProviders.DESERT_ARMORER), VillagerType.DESERT),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 16, 1, 3, 15, 0.05F, FairProviders.PLAINS_ARMORER), VillagerType.PLAINS),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 18, 1, 3, 15, 0.05F, FairProviders.PLAINS_ARMORER), VillagerType.PLAINS),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_LEGGINGS, 22, 1, 3, 15, 0.05F, FairProviders.PLAINS_ARMORER), VillagerType.PLAINS),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_CHESTPLATE, 26, 1, 3, 15, 0.05F, FairProviders.PLAINS_ARMORER), VillagerType.PLAINS),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 5, 1, 3, 15, 0.05F, FairProviders.SAVANNA_ARMORER), VillagerType.SAVANNA),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 6, 1, 3, 15, 0.05F, FairProviders.SAVANNA_ARMORER), VillagerType.SAVANNA),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_LEGGINGS, 10, 1, 3, 15, 0.05F, FairProviders.SAVANNA_ARMORER), VillagerType.SAVANNA),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_CHESTPLATE, 14, 1, 3, 15, 0.05F, FairProviders.SAVANNA_ARMORER), VillagerType.SAVANNA),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 16, 1, 3, 15, 0.05F, FairProviders.SNOW_ARMORER), VillagerType.SNOW),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 18, 1, 3, 15, 0.05F, FairProviders.SNOW_ARMORER), VillagerType.SNOW),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_BOOTS, 16, 1, 3, 15, 0.05F, FairProviders.JUNGLE_ARMORER), VillagerType.JUNGLE),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_HELMET, 18, 1, 3, 15, 0.05F, FairProviders.JUNGLE_ARMORER), VillagerType.JUNGLE),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_LEGGINGS, 22, 1, 3, 15, 0.05F, FairProviders.JUNGLE_ARMORER), VillagerType.JUNGLE),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.CHAINMAIL_CHESTPLATE, 26, 1, 3, 15, 0.05F, FairProviders.JUNGLE_ARMORER), VillagerType.JUNGLE),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.IRON_BOOTS, 16, 1, 3, 15, 0.05F, FairProviders.SWAMP_ARMORER), VillagerType.SWAMP),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.IRON_HELMET, 18, 1, 3, 15, 0.05F, FairProviders.SWAMP_ARMORER), VillagerType.SWAMP),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.IRON_LEGGINGS, 22, 1, 3, 15, 0.05F, FairProviders.SWAMP_ARMORER), VillagerType.SWAMP),
                        TradeOffers.TypedWrapperFactory.of(
                                new FairFactories.SellItemShardFactory(Items.IRON_CHESTPLATE, 26, 1, 3, 15, 0.05F, FairProviders.SWAMP_ARMORER), VillagerType.SWAMP),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 2, 9, Items.DIAMOND_BOOTS, 1, 3, 15, 0.05F), VillagerType.TAIGA),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 3, 11, Items.DIAMOND_HELMET, 1, 3, 15, 0.05F), VillagerType.TAIGA),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 4, 12, Items.DIAMOND_LEGGINGS, 1, 3, 15, 0.05F), VillagerType.TAIGA),
                        TradeOffers.TypedWrapperFactory.of(
                                new TradeOffers.ProcessItemFactory(Items.DIAMOND, 5, 14, Items.DIAMOND_CHESTPLATE, 1, 3, 15, 0.05F), VillagerType.TAIGA)},
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
                        new FairFactories.BuyItemShardFactory(Items.COAL, 12, 12, 1),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.STONE_AXE), 6, 1, 12, 2, 0.2F),
                        new FairFactories.SellItemShardFactory(Items.STONE_SWORD, 4, 3, 1, 2)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.IRON_INGOT, 32, 12, 4),
                        new TradeOffers.SellItemFactory(Items.BELL, 20, 1, 12, 30, 0.2F)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.FLINT, 36, 12, 10)},
                4, new TradeOffers.Factory[]{
                        new TradeOffers.BuyItemFactory(Items.DIAMOND, 2, 12, 30),
                        new FairFactories.ProcessItemShardFactory(Items.DIAMOND, 2, 16, Items.DIAMOND_AXE, 1, 3, 25, 0.2F)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.ProcessItemShardFactory(Items.DIAMOND, 1, 20, Items.DIAMOND_SWORD, 1, 3, 25, 0.2F)})));
        map.put(VillagerProfession.TOOLSMITH, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.COAL, 12, 12, 1),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.STONE_AXE), 2, 1, 12, 3, 0.2F),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.STONE_SHOVEL), 2, 1, 12, 3, 0.2F),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.STONE_PICKAXE), 2, 1, 12, 3, 0.2F),
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.STONE_HOE), 2, 1, 12, 3, 0.2F)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.IRON_INGOT, 32, 12, 4),
                        new TradeOffers.SellItemFactory(Items.BELL, 20, 1, 12, 100, 0.2F)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.FLINT, 24, 12, 20),
                        new FairFactories.SellEnchantedShardFactory(Items.IRON_AXE, 8, 3, 10, 0.2F),
                        new FairFactories.SellEnchantedShardFactory(Items.IRON_SHOVEL, 8, 3, 10, 0.2F),
                        new FairFactories.SellEnchantedShardFactory(Items.IRON_PICKAXE, 10, 3, 10, 0.2F)},
                4, new TradeOffers.Factory[]{
                        new TradeOffers.BuyItemFactory(Items.DIAMOND, 2, 12, 30),
                        new FairFactories.ProcessItemShardFactory(Items.DIAMOND, 2, 14, Items.DIAMOND_AXE, 1, 3, 25, 0.2F),
                        new FairFactories.ProcessItemShardFactory(Items.DIAMOND, 1, 10, Items.DIAMOND_SHOVEL, 1, 3, 25, 0.2F)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.ProcessItemShardFactory(Items.DIAMOND, 2, 29, Items.DIAMOND_PICKAXE, 1, 3, 25, 0.2F)})));
        map.put(VillagerProfession.BUTCHER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.CHICKEN, 6, 2, 1),
                        new FairFactories.SellItemShardFactory(Items.PORKCHOP, 8, 3, 2),
                        new FairFactories.SellItemShardFactory(Items.RABBIT, 4, 2, 2),
                        new FairFactories.SellItemShardFactory(Items.RABBIT_STEW, 6, 1, 2)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.COOKED_PORKCHOP, 14, 5, 3, 5),
                        new FairFactories.SellItemShardFactory(Items.COOKED_CHICKEN, 10, 8, 4, 5)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.MUTTON, 14, 16, 10),
                        new FairFactories.SellItemShardFactory(Items.BEEF, 20, 16, 10)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.DRIED_KELP_BLOCK, 10, 12, 20)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.SWEET_BERRIES, 64, 9, 30)})));
        map.put(VillagerProfession.LEATHERWORKER, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.LEATHER, 12, 16, 2),
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_LEGGINGS, 4),
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_CHESTPLATE, 11)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_HELMET, 7, 12, 5),
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_BOOTS, 6, 12, 5)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.RABBIT_HIDE, 46, 12, 20),
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_CHESTPLATE, 11)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.BuyItemShardFactory(Items.TURTLE_SCUTE, 8, 12, 30),
                        new FairFactories.SellDyedArmorShardFactory(Items.LEATHER_HORSE_ARMOR, 9, 12, 15)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(new ItemStack(Items.SADDLE), 15, 1, 12, 30, 0.2F),
                        new FairFactories.SellItemShardFactory(Items.BUNDLE, 22, 1, 30)})));
        map.put(VillagerProfession.MASON, TradeOffers.copyToFastUtilMap(ImmutableMap.of(
                1, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Items.CLAY_BALL, 3, 2, 3),
                        new FairFactories.SellItemShardFactory(Items.BRICK, 3, 5, 16, 5)},
                2, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Blocks.STONE.asItem(), 1, 8, 10),
                        new FairFactories.SellItemShardFactory(Blocks.CHISELED_STONE_BRICKS, 1, 8, 16, 5)},
                3, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Blocks.GRANITE.asItem(), 2, 8, 20),
                        new FairFactories.SellItemShardFactory(Blocks.ANDESITE.asItem(), 2, 8, 20),
                        new FairFactories.SellItemShardFactory(Blocks.DIORITE.asItem(), 2, 8, 20),
                        new FairFactories.SellItemShardFactory(Blocks.DRIPSTONE_BLOCK, 6, 4, 16, 10),
                        new FairFactories.SellItemShardFactory(Blocks.POLISHED_ANDESITE, 2, 4, 16, 10),
                        new FairFactories.SellItemShardFactory(Blocks.POLISHED_DIORITE, 2, 4, 16, 10),
                        new FairFactories.SellItemShardFactory(Blocks.POLISHED_GRANITE, 2, 4, 16, 10)},
                4, new TradeOffers.Factory[]{
                        new FairFactories.SellTagShardFactory(ItemTags.TERRACOTTA, 6, 4, 12, 15),
                        new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("c","glazed_terracottas")), 2, 4, 30)},
                5, new TradeOffers.Factory[]{
                        new FairFactories.SellItemShardFactory(Blocks.QUARTZ_PILLAR, 6, 1, 12, 30),
                        new FairFactories.SellItemShardFactory(Blocks.QUARTZ_BLOCK, 8, 1, 12, 30)})));
    });

    @Shadow
    public static final Int2ObjectMap<TradeOffers.Factory[]> WANDERING_TRADER_TRADES = TradeOffers.copyToFastUtilMap(
            ImmutableMap.of(1, new TradeOffers.Factory[]{
                    new FairFactories.SellItemShardFactory(Items.SLIME_BALL, 2, 1, 5, 1),
                    new FairFactories.SellItemShardFactory(Items.GLOWSTONE, 4, 1, 5, 1),
                    new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of(FairDeal.MOD_ID,"wandering_trades/flowers")), 3, 6, 12, 1),
                    new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of(FairDeal.MOD_ID,"wandering_trades/seeds")), 1, 1, 12, 1),
                    new FairFactories.SellTagShardFactory(ItemTags.SAPLINGS, 5, 1, 8, 1),
                    new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("c","dyes")), 2, 8, 12, 1),
                    new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of("c","corals")), 4, 2, 8, 1),
                    new FairFactories.SellTagShardFactory(TagKey.of(RegistryKeys.ITEM, Identifier.of(FairDeal.MOD_ID,"wandering_trades/vegetation")), 3, 1, 5, 1),
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
                    new FairFactories.SellItemShardFactory(TradeOffers.createPotionStack(Potions.LONG_INVISIBILITY), 22, 1, 1, 1)
            }));


}

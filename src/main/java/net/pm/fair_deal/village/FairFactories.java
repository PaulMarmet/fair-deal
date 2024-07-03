package net.pm.fair_deal.village;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.provider.EnchantmentProvider;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.item.map.MapState;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;
import net.pm.fair_deal.item.FairDealItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class FairFactories {
    public static class BuyItemShardFactory implements TradeOffers.Factory {
        private final TradedItem stack;
        private final int maxUses;
        private final int experience;
        private final int price;
        private final float multiplier;

        public BuyItemShardFactory(ItemConvertible item, int count, int maxUses, int experience) {
            this(item, count, maxUses, experience, 1);
        }

        public BuyItemShardFactory(ItemConvertible item, int count, int maxUses, int experience, int price) {
            this(new TradedItem(item.asItem(), count), maxUses, experience, price);
        }

        public BuyItemShardFactory(TradedItem stack, int maxUses, int experience, int price) {
            this.stack = stack;
            this.maxUses = maxUses;
            this.experience = experience;
            this.price = price;
            this.multiplier = 0.05F;
        }

        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(this.stack, new ItemStack(FairDealItems.EMERALD_SHARD, this.price), this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class SellItemShardFactory implements TradeOffers.Factory {
        private final ItemStack sell;
        private final int price;
        private final int maxUses;
        private final int experience;
        private final float multiplier;
        private final Optional<RegistryKey<EnchantmentProvider>> enchantmentProviderKey;

        public SellItemShardFactory(Block block, int price, int count, int maxUses, int experience) {
            this(new ItemStack(block), price, count, maxUses, experience);
        }

        public SellItemShardFactory(Item item, int price, int count, int experience) {
            this((ItemStack)(new ItemStack(item)), price, count, 12, experience);
        }

        public SellItemShardFactory(Item item, int price, int count, int maxUses, int experience) {
            this(new ItemStack(item), price, count, maxUses, experience);
        }

        public SellItemShardFactory(ItemStack stack, int price, int count, int maxUses, int experience) {
            this(stack, price, count, maxUses, experience, 0.05F);
        }

        public SellItemShardFactory(Item item, int price, int count, int maxUses, int experience, float multiplier) {
            this(new ItemStack(item), price, count, maxUses, experience, multiplier);
        }

        public SellItemShardFactory(Item item, int price, int count, int maxUses, int experience, float multiplier, RegistryKey<EnchantmentProvider> enchantmentProviderKey) {
            this(new ItemStack(item), price, count, maxUses, experience, multiplier, Optional.of(enchantmentProviderKey));
        }

        public SellItemShardFactory(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
            this(stack, price, count, maxUses, experience, multiplier, Optional.empty());
        }

        public SellItemShardFactory(ItemStack sell, int price, int count, int maxUses, int experience, float multiplier, Optional<RegistryKey<EnchantmentProvider>> enchantmentProviderKey) {
            this.sell = sell;
            this.price = price;
            this.sell.setCount(count);
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
            this.enchantmentProviderKey = enchantmentProviderKey;
        }

        public TradeOffer create(Entity entity, Random random) {
            ItemStack itemStack = this.sell.copy();
            World world = entity.getWorld();
            this.enchantmentProviderKey.ifPresent((key) -> {
                EnchantmentHelper.applyEnchantmentProvider(itemStack, world.getRegistryManager(), key, world.getLocalDifficulty(entity.getBlockPos()), random);
            });
            return new TradeOffer(new TradedItem(FairDealItems.EMERALD_SHARD, this.price), itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class ProcessItemShardFactory implements TradeOffers.Factory {
        private final TradedItem toBeProcessed;
        private final int price;
        private final ItemStack processed;
        private final int maxUses;
        private final int experience;
        private final float multiplier;
        private final Optional<RegistryKey<EnchantmentProvider>> enchantmentProviderKey;

        public ProcessItemShardFactory(ItemConvertible item, int count, int price, Item processed, int processedCount, int maxUses, int experience, float multiplier) {
            this(item, count, price, new ItemStack(processed), processedCount, maxUses, experience, multiplier);
        }

        private ProcessItemShardFactory(ItemConvertible item, int count, int price, ItemStack processed, int processedCount, int maxUses, int experience, float multiplier) {
            this(new TradedItem(item, count), price, processed.copyWithCount(processedCount), maxUses, experience, multiplier, Optional.empty());
        }

        ProcessItemShardFactory(ItemConvertible item, int count, int price, ItemConvertible processed, int processedCount, int maxUses, int experience, float multiplier, RegistryKey<EnchantmentProvider> enchantmentProviderKey) {
            this(new TradedItem(item, count), price, new ItemStack(processed, processedCount), maxUses, experience, multiplier, Optional.of(enchantmentProviderKey));
        }

        public ProcessItemShardFactory(TradedItem toBeProcessed, int count, ItemStack processed, int maxUses, int processedCount, float multiplier, Optional<RegistryKey<EnchantmentProvider>> enchantmentProviderKey) {
            this.toBeProcessed = toBeProcessed;
            this.price = count;
            this.processed = processed;
            this.maxUses = maxUses;
            this.experience = processedCount;
            this.multiplier = multiplier;
            this.enchantmentProviderKey = enchantmentProviderKey;
        }

        @Nullable
        public TradeOffer create(Entity entity, Random random) {
            ItemStack itemStack = this.processed.copy();
            World world = entity.getWorld();
            this.enchantmentProviderKey.ifPresent((key) -> {
                EnchantmentHelper.applyEnchantmentProvider(itemStack, world.getRegistryManager(), key, world.getLocalDifficulty(entity.getBlockPos()), random);
            });
            return new TradeOffer(new TradedItem(FairDealItems.EMERALD_SHARD, this.price), Optional.of(this.toBeProcessed), itemStack, 0, this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class SellMapShardFactory implements TradeOffers.Factory {
        private final int price;
        private final TagKey<Structure> structure;
        private final String nameKey;
        private final RegistryEntry<MapDecorationType> decoration;
        private final int maxUses;
        private final int experience;

        public SellMapShardFactory(int price, TagKey<Structure> structure, String nameKey, RegistryEntry<MapDecorationType> decoration, int maxUses, int experience) {
            this.price = price;
            this.structure = structure;
            this.nameKey = nameKey;
            this.decoration = decoration;
            this.maxUses = maxUses;
            this.experience = experience;
        }

        @Nullable
        public TradeOffer create(Entity entity, Random random) {
            if (!(entity.getWorld() instanceof ServerWorld)) {
                return null;
            } else {
                ServerWorld serverWorld = (ServerWorld)entity.getWorld();
                BlockPos blockPos = serverWorld.locateStructure(this.structure, entity.getBlockPos(), 100, true);
                if (blockPos != null) {
                    ItemStack itemStack = FilledMapItem.createMap(serverWorld, blockPos.getX(), blockPos.getZ(), (byte)2, true, true);
                    FilledMapItem.fillExplorationMap(serverWorld, itemStack);
                    MapState.addDecorationsNbt(itemStack, blockPos, "+", this.decoration);
                    itemStack.set(DataComponentTypes.ITEM_NAME, Text.translatable(this.nameKey));
                    return new TradeOffer(new TradedItem(FairDealItems.EMERALD_SHARD, this.price), Optional.of(new TradedItem(Items.COMPASS)), itemStack, this.maxUses, this.experience, 0.2F);
                } else {
                    return null;
                }
            }
        }
    }

    public static class SellDyedArmorShardFactory implements TradeOffers.Factory {
        private final Item sell;
        private final int price;
        private final int maxUses;
        private final int experience;

        public SellDyedArmorShardFactory(Item item, int price) {
            this(item, price, 12, 1);
        }

        public SellDyedArmorShardFactory(Item item, int price, int maxUses, int experience) {
            this.sell = item;
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
        }

        public TradeOffer create(Entity entity, Random random) {
            TradedItem tradedItem = new TradedItem(Items.EMERALD, this.price);
            ItemStack itemStack = new ItemStack(this.sell);
            if (itemStack.isIn(ItemTags.DYEABLE)) {
                List<DyeItem> list = Lists.newArrayList();
                list.add(getDye(random));
                if (random.nextFloat() > 0.7F) {
                    list.add(getDye(random));
                }

                if (random.nextFloat() > 0.8F) {
                    list.add(getDye(random));
                }

                itemStack = DyedColorComponent.setColor(itemStack, list);
            }

            return new TradeOffer(tradedItem, itemStack, this.maxUses, this.experience, 0.2F);
        }

        private static DyeItem getDye(Random random) {
            return DyeItem.byColor(DyeColor.byId(random.nextInt(16)));
        }
    }

    public static class SellEnchantedShardFactory implements TradeOffers.Factory {
        private final ItemStack tool;
        private final int basePrice;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellEnchantedShardFactory(Item item, int basePrice, int maxUses, int experience) {
            this(item, basePrice, maxUses, experience, 0.05F);
        }

        public SellEnchantedShardFactory(Item item, int basePrice, int maxUses, int experience, float multiplier) {
            this.tool = new ItemStack(item);
            this.basePrice = basePrice;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        public TradeOffer create(Entity entity, Random random) {
            int i = 5 + random.nextInt(15);
            DynamicRegistryManager dynamicRegistryManager = entity.getWorld().getRegistryManager();
            Optional<RegistryEntryList.Named<Enchantment>> optional = dynamicRegistryManager.get(RegistryKeys.ENCHANTMENT).getEntryList(EnchantmentTags.ON_TRADED_EQUIPMENT);
            ItemStack itemStack = EnchantmentHelper.enchant(random, new ItemStack(this.tool.getItem()), i, dynamicRegistryManager, optional);
            int j = Math.min(this.basePrice + i, 64);
            TradedItem tradedItem = new TradedItem(FairDealItems.EMERALD_SHARD, j);
            return new TradeOffer(tradedItem, itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class BuyTagShardFactory implements TradeOffers.Factory {
        private final TagKey<Item> tag;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final int price;
        private final float multiplier;

        public BuyTagShardFactory(TagKey<Item> tag, int count, int maxUses, int experience) {
            this(tag, count, maxUses, experience, 1);
        }

        public BuyTagShardFactory(TagKey<Item> tag, int count, int maxUses, int experience, int price) {
            this.tag = tag;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.price = price;
            this.multiplier = 0.05F;
        }

        public TradeOffer create(Entity entity, Random random) {
            List<Item> list = Registries.ITEM.stream().filter(item -> item.getDefaultStack().isIn(tag)).toList();
            Item item = list.get(random.nextInt(list.size()));
            return new TradeOffer(new TradedItem(item, count), new ItemStack(FairDealItems.EMERALD_SHARD, this.price), this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class SellTagShardFactory implements TradeOffers.Factory {
        private final TagKey<Item> tag;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final float multiplier;
        private final Optional<RegistryKey<EnchantmentProvider>> enchantmentProviderKey;

        public SellTagShardFactory(TagKey<Item> tag, int price, int count, int maxUses, int experience) {
            this(tag, price, count, maxUses, experience, 0.05F);
        }

        public SellTagShardFactory(TagKey<Item> tag, int price, int count, int experience) {
            this(tag, price, count, 12, experience);
        }

        public SellTagShardFactory(TagKey<Item> tag, int price, int count, int maxUses, int experience, float multiplier) {
            this(tag, price, count, maxUses, experience, multiplier, Optional.empty());
        }

        public SellTagShardFactory(TagKey<Item> tag, int price, int count, int maxUses, int experience, float multiplier, RegistryKey<EnchantmentProvider> enchantmentProviderKey) {
            this(tag, price, count, maxUses, experience, multiplier, Optional.of(enchantmentProviderKey));
        }

        public SellTagShardFactory(TagKey<Item> tag, int price, int count, int maxUses, int experience, float multiplier, Optional<RegistryKey<EnchantmentProvider>> enchantmentProviderKey) {
            this.tag = tag;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
            this.enchantmentProviderKey = enchantmentProviderKey;
        }

        public TradeOffer create(Entity entity, Random random) {
            List<Item> list = Registries.ITEM.stream().filter(item -> item.getDefaultStack().isIn(tag)).toList();
            ItemStack itemStack = list.get(random.nextInt(list.size())).getDefaultStack();
            itemStack.setCount(this.count);
            World world = entity.getWorld();
            this.enchantmentProviderKey.ifPresent((key) -> {
                EnchantmentHelper.applyEnchantmentProvider(itemStack, world.getRegistryManager(), key, world.getLocalDifficulty(entity.getBlockPos()), random);
            });
            return new TradeOffer(new TradedItem(FairDealItems.EMERALD_SHARD, this.price), itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }
}

package net.pm.fair_deal;

import net.minecraft.enchantment.provider.EnchantmentProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public interface FairProviders {
    RegistryKey<EnchantmentProvider> DESERT_ARMORER = RegistryKey.of(RegistryKeys.ENCHANTMENT_PROVIDER, Identifier.of("fair_deal", "trades/desert_armorer"));
    RegistryKey<EnchantmentProvider> JUNGLE_ARMORER = RegistryKey.of(RegistryKeys.ENCHANTMENT_PROVIDER, Identifier.of("fair_deal", "trades/jungle_armorer"));
    RegistryKey<EnchantmentProvider> PLAINS_ARMORER = RegistryKey.of(RegistryKeys.ENCHANTMENT_PROVIDER, Identifier.of("fair_deal", "trades/plains_armorer"));
    RegistryKey<EnchantmentProvider> SAVANNA_ARMORER = RegistryKey.of(RegistryKeys.ENCHANTMENT_PROVIDER, Identifier.of("fair_deal", "trades/savanna_armorer"));
    RegistryKey<EnchantmentProvider> SNOW_ARMORER = RegistryKey.of(RegistryKeys.ENCHANTMENT_PROVIDER, Identifier.of("fair_deal", "trades/snow_armorer"));
    RegistryKey<EnchantmentProvider> SWAMP_ARMORER = RegistryKey.of(RegistryKeys.ENCHANTMENT_PROVIDER, Identifier.of("fair_deal", "trades/swamp_armorer"));
    RegistryKey<EnchantmentProvider> TAIGA_ARMORER = RegistryKey.of(RegistryKeys.ENCHANTMENT_PROVIDER, Identifier.of("fair_deal", "trades/taiga_armorer"));
}

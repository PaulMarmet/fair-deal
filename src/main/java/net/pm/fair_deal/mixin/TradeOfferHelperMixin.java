package net.pm.fair_deal.mixin;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.village.VillagerProfession;
import net.pm.fair_deal.FairDeal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TradeOfferHelper.class)
public class TradeOfferHelperMixin {
    @Redirect(method = "registerVillagerOffers(Lnet/minecraft/village/VillagerProfession;ILjava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/impl/object/builder/TradeOfferInternals;registerVillagerOffers(Lnet/minecraft/village/VillagerProfession;ILnet/fabricmc/fabric/api/object/builder/v1/trade/TradeOfferHelper$VillagerOffersAdder;)V"))
    private static void logRemovals(VillagerProfession profession, int level, TradeOfferHelper.VillagerOffersAdder factory) {
        FairDeal.LOGGER.info("Did not add trade for: "+profession.toString()+" level "+level);
    }
}

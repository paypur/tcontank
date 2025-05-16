package paypur.tcontank.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import paypur.tcontank.TConTank;
import slimeknights.tconstruct.common.TinkerTags;

@Mod.EventBusSubscriber(modid = TConTank.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ItemTooltipHandler {
    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        if (event.getItemStack().is(TinkerTags.Items.SEARED_TANKS)) {
            event.getToolTip().add(Component.translatable("tcontank.seared_tank.tooltip").withStyle(ChatFormatting.GRAY));
        }
    }
}

package paypur.tcontank.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.smeltery.item.TankItemFluidHandler;

@Mixin(TankItemFluidHandler.class)
public abstract class TankItemFluidHandlerMixin {

    @Shadow @Final private ItemStack container;

    @Inject(method = "fill(Lnet/minecraftforge/fluids/FluidStack;Lnet/minecraftforge/fluids/capability/IFluidHandler$FluidAction;)I", at = @At("HEAD"), cancellable = true, remap = false)
    public void fill(FluidStack resource, IFluidHandler.FluidAction action, CallbackInfoReturnable<Integer> cir) {
        if (this.container.getTags().anyMatch(t -> t.equals(TinkerTags.Items.SEARED_TANKS)) &&
                resource.isFluidEqual(new FluidStack(TinkerFluids.blazingBlood.get().getSource(), 1))) {
            if (Minecraft.getInstance().player != null) Minecraft.getInstance().player.sendSystemMessage(Component.literal("Seared tanks can't hold liquids greater than 1000°C"));
            cir.setReturnValue(0);
            cir.cancel();
        }
    }

}

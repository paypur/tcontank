package paypur.tcontanknerf.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.recipe.fuel.MeltingFuel;
import slimeknights.tconstruct.library.recipe.fuel.MeltingFuelLookup;
import slimeknights.tconstruct.smeltery.item.TankItemFluidHandler;

@Mixin(TankItemFluidHandler.class)
public abstract class TankItemFluidHandlerMixin {
    @Shadow(remap = false)
    public abstract ItemStack getContainer();

    @Inject(method = "fill(Lnet/minecraftforge/fluids/FluidStack;Lnet/minecraftforge/fluids/capability/IFluidHandler$FluidAction;)I", at = @At("HEAD"), cancellable = true, remap = false)
    private void fill(FluidStack resource, IFluidHandler.FluidAction action, CallbackInfoReturnable<Integer> cir) {
        if (this.getContainer().is(TinkerTags.Items.SEARED_TANKS)) {
            MeltingFuel fuel = MeltingFuelLookup.findFuel(resource.getFluid());
            if (fuel != null && fuel.getTemperature() > 1000) {
                // also used for stuff like JEI, so we can't really send messages to the player without a lot of spam on login
                cir.setReturnValue(0);
                cir.cancel();
            }
        }
    }
}

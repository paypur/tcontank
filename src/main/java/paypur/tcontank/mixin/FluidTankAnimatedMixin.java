package paypur.tcontank.mixin;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.fluid.FluidTankAnimated;
import slimeknights.tconstruct.library.fluid.FluidTankBase;
import slimeknights.tconstruct.library.recipe.fuel.MeltingFuel;
import slimeknights.tconstruct.library.recipe.fuel.MeltingFuelLookup;

@Mixin(FluidTankAnimated.class)
public abstract class FluidTankAnimatedMixin extends FluidTankBase<MantleBlockEntity> {
    public FluidTankAnimatedMixin(int capacity, MantleBlockEntity parent) {
        super(capacity, parent);
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        if (parent.getBlockState().is(TinkerTags.Blocks.SEARED_TANKS)) {
            MeltingFuel fuel = MeltingFuelLookup.findFuel(resource.getFluid());
            if (fuel != null && fuel.getTemperature() > 1000) {
                return 0;
            }
        }
        return super.fill(resource, action);
    }
}

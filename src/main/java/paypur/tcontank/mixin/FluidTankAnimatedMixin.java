package paypur.tcontank.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.fluid.FluidTankAnimated;
import slimeknights.tconstruct.library.fluid.FluidTankBase;

@Mixin(FluidTankAnimated.class)
public abstract class FluidTankAnimatedMixin extends FluidTankBase<MantleBlockEntity> {

    public FluidTankAnimatedMixin(int capacity, MantleBlockEntity parent) {
        super(capacity, parent);
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        if (parent.getBlockState().getTags().anyMatch(t -> t.equals(TinkerTags.Blocks.SEARED_TANKS)) &&
                resource.isFluidEqual(new FluidStack(TinkerFluids.blazingBlood.get().getSource(), 1))) {
            if (Minecraft.getInstance().player != null) Minecraft.getInstance().player.sendSystemMessage(Component.literal("Seared tanks can't hold liquids greater than 1000°C"));
            return 0;
        }

        return super.fill(resource, action);
    }


}

package io.github.bennyboy1695.skymachinatweaks.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FlowingFluid.class)
public abstract class MixinFlowingFluid extends Fluid {


    @Shadow protected abstract FluidState getNewLiquid(LevelReader p_76036_, BlockPos p_76037_, BlockState p_76038_);

    @Shadow protected abstract boolean canSpreadTo(BlockGetter p_75978_, BlockPos p_75979_, BlockState p_75980_, Direction p_75981_, BlockPos p_75982_, BlockState p_75983_, FluidState p_75984_, Fluid p_75985_);

    @Shadow protected abstract void spreadTo(LevelAccessor p_76005_, BlockPos p_76006_, BlockState p_76007_, Direction p_76008_, FluidState p_76009_);

    @Shadow protected abstract int sourceNeighborCount(LevelReader p_76020_, BlockPos p_76021_);

    @Shadow protected abstract void spreadToSides(LevelAccessor p_76015_, BlockPos p_76016_, FluidState p_76017_, BlockState p_76018_);

    @Shadow protected abstract boolean isWaterHole(BlockGetter p_75957_, Fluid p_75958_, BlockPos p_75959_, BlockState p_75960_, BlockPos p_75961_, BlockState p_75962_);

    /**
     * @author Bennyboy1695
     * @reason stop spread into void causing lag ?!
     */
    @Overwrite
    protected void spread(LevelAccessor p_76011_, BlockPos p_76012_, FluidState p_76013_) {
        if (!p_76013_.isEmpty()) {
            BlockState blockstate = p_76011_.getBlockState(p_76012_);
            BlockPos blockpos = p_76012_.below();
            if (blockpos.getY() > p_76011_.getMinBuildHeight()) {
                BlockState blockstate1 = p_76011_.getBlockState(blockpos);
                FluidState fluidstate = this.getNewLiquid(p_76011_, blockpos, blockstate1);
                if (this.canSpreadTo(p_76011_, p_76012_, blockstate, Direction.DOWN, blockpos, blockstate1, p_76011_.getFluidState(blockpos), fluidstate.getType())) {
                    this.spreadTo(p_76011_, blockpos, blockstate1, Direction.DOWN, fluidstate);
                    if (this.sourceNeighborCount(p_76011_, p_76012_) >= 3) {
                        this.spreadToSides(p_76011_, p_76012_, p_76013_, blockstate);
                    }
                } else if (p_76013_.isSource() || !this.isWaterHole(p_76011_, fluidstate.getType(), p_76012_, blockstate, blockpos, blockstate1)) {
                    this.spreadToSides(p_76011_, p_76012_, p_76013_, blockstate);
                }
            }
        }
    }
}

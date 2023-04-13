package io.github.bennyboy1695.skymachinatweaks.block.tile;

import com.simibubi.create.foundation.item.SmartInventory;
import com.simibubi.create.foundation.tileEntity.SmartTileEntity;
import com.simibubi.create.foundation.tileEntity.TileEntityBehaviour;
import com.simibubi.create.foundation.tileEntity.behaviour.fluid.SmartFluidTankBehaviour;
import io.github.bennyboy1695.skymachinatweaks.SkyMachinaTweaks;
import io.github.bennyboy1695.skymachinatweaks.data.recipe.CrucibleRecipe;
import io.github.bennyboy1695.skymachinatweaks.register.MachinaRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

/*
 * Crucible code is largely copied from ExNihiloSequentia. Because only the crucible was needed and nothing else.
 * You can find the mod here: https://github.com/NovaMachina-Mods/ExNihiloSequentia
 */
public class CrucibleTileEntity extends SmartTileEntity {

    private SmartFluidTankBehaviour outputTank;
    private SmartInventory inputInv;
    protected LazyOptional<IFluidHandler> fluidCapability;
    private LazyOptional<IItemHandlerModifiable> itemCapability;

    private ItemStack currentItem;
    protected int solidAmount;

    private boolean contentsChanged;

    public CrucibleTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inputInv = new SmartInventory(1, this, 4, false);
        inputInv.whenContentsChanged($ -> contentsChanged = true);
        inputInv.forbidExtraction();
        itemCapability = LazyOptional.of(() -> new CombinedInvWrapper(inputInv));
        fluidCapability = outputTank.getCapability().cast();
        contentsChanged = true;
        currentItem = ItemStack.EMPTY;
    }

    public int getHeat() {
        if (level == null) {
            return 0;
        }
        @Nonnull final BlockState source = level.getBlockState(worldPosition.below());
        final int blockHeat = SkyMachinaTweaks.getInstance().recipeCache().getHeatAmount(source);
        if (source.getBlock() instanceof LiquidBlock) {
            final int level = 8 - source.getValue(BlockStateProperties.LEVEL);
            final double partial = (double) blockHeat / 8;
            return (int) Math.ceil(partial * level);
        }
        return blockHeat;
    }

    public ItemStack currentItem() {
        return currentItem;
    }

    @Nullable
    public CrucibleRecipe getMeltable() {
        return SkyMachinaTweaks.getInstance().recipeCache().findRecipeByItemStack(currentItem).orElse(null);
    }

    @Override
    public void tick() {
        super.tick();
        int heat = getHeat();
        if (heat <= 0) {
            return;
        }
        if (solidAmount <= 0) {
            if (!inputInv.getStackInSlot(0).isEmpty()) {
                consumeNewSolid();
            } else {
                return;
            }
        }

        if (!inputInv.getStackInSlot(0).isEmpty()
                && inputInv.getStackInSlot(0).sameItem(currentItem)) {
            addFluid(heat);
        }

        if (heat > solidAmount) {
            heat = solidAmount;
        }

        if (heat > 0
                && SkyMachinaTweaks.getInstance().recipeCache().isMeltableByItemStack(
                currentItem)) {
            processSolid(heat);
        }
    }

    private void addFluid(int heat) {
        while (heat > solidAmount && !inputInv.getStackInSlot(0).isEmpty()) {
            final Optional<CrucibleRecipe> recipe =
                    SkyMachinaTweaks.getInstance().recipeCache().findRecipeByItemStack(currentItem);
            if (recipe.isPresent()) {
                solidAmount += recipe.get().getAmount();
                inputInv.getStackInSlot(0).shrink(1);

                if (inputInv.getStackInSlot(0).isEmpty()) {
                    inputInv.setStackInSlot(0, ItemStack.EMPTY);
                }
            }
        }
    }

    protected void consumeNewSolid() {
        currentItem = inputInv.getStackInSlot(0).copy();
        inputInv.getStackInSlot(0).shrink(1);

        if (inputInv.getStackInSlot(0).isEmpty()) {
            inputInv.setStackInSlot(0, ItemStack.EMPTY);
        }

        SkyMachinaTweaks.getInstance().recipeCache()
                .findRecipeByItemStack(currentItem)
                .ifPresent(recipe -> solidAmount = recipe.getAmount());
    }

    protected void processSolid(int heat) {
        final Optional<CrucibleRecipe> recipe = SkyMachinaTweaks.getInstance().recipeCache().findRecipeByItemStack(currentItem);
        if (recipe.isPresent()) {
            FluidStack fluidStack = new FluidStack(recipe.get().getResultFluid(), heat);
            int filled = outputTank.getPrimaryHandler().fill(fluidStack, IFluidHandler.FluidAction.EXECUTE);
            solidAmount -= filled;
        }
    }

    @Override
    public void addBehaviours(List<TileEntityBehaviour> behaviours) {
        behaviours.add(outputTank = SmartFluidTankBehaviour.single(this, 4000)
                .forbidInsertion()
                .allowExtraction());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (isItemHandlerCap(cap)) {
            return itemCapability.cast();
        }
        if (isFluidHandlerCap(cap)) {
            return outputTank.getCapability().cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        itemCapability.invalidate();
        fluidCapability.invalidate();
    }

    public SmartFluidTankBehaviour getOutputTank() {
        return outputTank;
    }

    public SmartInventory inputInv() {
        return inputInv;
    }
}
